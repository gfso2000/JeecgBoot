package org.jeecg.modules.demo.jack.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.system.query.QueryCondition;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.demo.jack.service.IShardingSysLogService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.query.QueryRuleEnum;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.jack.entity.JackSupplierWarehouse;
import org.jeecg.modules.demo.jack.entity.JackSupplierGoods;
import org.jeecg.modules.demo.jack.entity.JackSupplier;
import org.jeecg.modules.demo.jack.vo.JackSupplierPage;
import org.jeecg.modules.demo.jack.service.IJackSupplierService;
import org.jeecg.modules.demo.jack.service.IJackSupplierWarehouseService;
import org.jeecg.modules.demo.jack.service.IJackSupplierGoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;


 /**
 * @Description: jack supplier
 * @Author: jeecg-boot
 * @Date:   2025-04-02
 * @Version: V1.0
 */
@Tag(name="jack supplier")
@RestController
@RequestMapping("/jack/jackSupplier")
@Slf4j
public class JackSupplierController {
	@Autowired
	private IJackSupplierService jackSupplierService;
	@Autowired
	private IJackSupplierWarehouseService jackSupplierWarehouseService;
	@Autowired
	private IJackSupplierGoodsService jackSupplierGoodsService;

	 @Autowired
	 private IShardingSysLogService shardingSysLogService;
	/**
	 * 分页列表查询
	 *
	 * @param jackSupplier
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "jack supplier-分页列表查询")
	@Operation(summary="jack supplier-分页列表查询", description="jack supplier-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<JackSupplier>> queryPageList(JackSupplier jackSupplier,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
//        QueryWrapper<JackSupplier> queryWrapper = QueryGenerator.initQueryWrapper(jackSupplier, req.getParameterMap());
//		Page<JackSupplier> page = new Page<JackSupplier>(pageNo, pageSize);
//		IPage<JackSupplier> pageList = jackSupplierService.page(page, queryWrapper);
//		return Result.OK(pageList);

		//高级查询参数
		String superQueryParams = req.getParameter("superQueryParams");
		//类型 and 并且 or 或者 根据不同的类型拼接不同的sql
		String superQueryMatchType = req.getParameter("superQueryMatchType");
		//子表对应的主表外键id集合，根据自己的逻辑动态处理
		List<String> mainIds = new ArrayList<>();
		//最新组合的查询条件集合，将子表的查询条件删除
		List<QueryCondition> newConditions = new ArrayList<>();
		//自定义请求类，详情参考步骤二的代码
		CustomRequest customRequest = new CustomRequest(req);
		if(StringUtils.isNotEmpty(superQueryParams)){
			try {
				superQueryParams = URLDecoder.decode(superQueryParams, "UTF-8");
				//获取参数
				List<QueryCondition> conditions = JSON.parseArray(superQueryParams, QueryCondition.class);
				//子表查询条件封装,如本示例ws_main_sub,如有多个子表写多个查询条件
				QueryWrapper<JackSupplierWarehouse> wsMainSubQuery = new QueryWrapper<>();
				//循环查询参数
				for(QueryCondition condition : conditions){
					//判断当前的查询构造器的字段是否包含逗号，包含代表为子表的查询条件
					if(condition.getField().contains(",")){
						String[] split = condition.getField().split(",");
						// TODO 多个子表可以根据实体类名称做判断
						String entityName = split[0];
						//对应的实体类属性
						String entityFieldName = split[1];
						//值
						Object queryValue = condition.getVal();
						//数据类型
						String type = condition.getType();
						//根据不同的类型获取不同的值
						if("date".equals(type)){
							queryValue = DateUtils.str2Date(condition.getVal(),DateUtils.date_sdf.get());
						}else if("datetime".equals(type)){
							queryValue = DateUtils.str2Date(condition.getVal(), DateUtils.datetimeFormat.get());
						}
						String dbType = condition.getDbType();
						if (oConvertUtils.isNotEmpty(dbType)) {
							try {
								String valueStr = String.valueOf(queryValue);
								switch (dbType.toLowerCase().trim()) {
									case "int":
										queryValue = Integer.parseInt(valueStr);
										break;
									case "boolean":
										queryValue = Boolean.parseBoolean(valueStr);
										break;
									default:
								}
                              /*拼接查询规则,注意entityFieldName为数据库的字段名称，如果数据库的名称和实体类的属性不匹配，需要自行转换；
                                如实体类为orderNo，而数据库为order_no，则此处传的是order_no*/
								QueryGenerator.addEasyQuery(wsMainSubQuery, entityFieldName, QueryRuleEnum.getByValue(condition.getRule()), queryValue);
							} catch (Exception e) {
								log.error("高级查询值转换失败：", e);
							}
						}
					}else{
						newConditions.add(condition);
					}
				}
				List<JackSupplierWarehouse> list = jackSupplierWarehouseService.list(wsMainSubQuery);
				mainIds = list.stream().map(JackSupplierWarehouse::getSupplierName).collect(Collectors.toList());
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		try {
			if (!newConditions.isEmpty()) {
				CustomRequest.SUPER_QUERY_PARAMS = URLEncoder.encode(JSON.toJSONString(newConditions),"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		QueryWrapper<JackSupplier> queryWrapper = QueryGenerator.initQueryWrapper(jackSupplier, customRequest.getParameterMap());
		Page<JackSupplier> page = new Page<JackSupplier>(pageNo, pageSize);
		//拼接子表查询条件，子表为空代表没有查询到数据 直接返回空数据即可
		if (!mainIds.isEmpty()) {
			if(StringUtils.isNotEmpty(superQueryMatchType) || "and".equals(superQueryMatchType)){
				queryWrapper.in("supplier_name", mainIds);
			}else{
				queryWrapper.or().in("supplier_name", mainIds);
			}
			IPage<JackSupplier> pageList = jackSupplierService.page(page, queryWrapper);
			return Result.OK(pageList);
		}else{
			return Result.OK(new Page<JackSupplier>());
		}
	}
	
	/**
	 *   添加
	 *
	 * @param jackSupplierPage
	 * @return
	 */
	@AutoLog(value = "jack supplier-添加")
	@Operation(summary="jack supplier-添加", description="jack supplier-添加")
    @RequiresPermissions("jack:jack_supplier:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody JackSupplierPage jackSupplierPage) {
		JackSupplier jackSupplier = new JackSupplier();
		BeanUtils.copyProperties(jackSupplierPage, jackSupplier);
		jackSupplierService.saveMain(jackSupplier, jackSupplierPage.getJackSupplierWarehouseList(),jackSupplierPage.getJackSupplierGoodsList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param jackSupplierPage
	 * @return
	 */
	@AutoLog(value = "jack supplier-编辑")
	@Operation(summary="jack supplier-编辑", description="jack supplier-编辑")
    @RequiresPermissions("jack:jack_supplier:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody JackSupplierPage jackSupplierPage) {
		JackSupplier jackSupplier = new JackSupplier();
		BeanUtils.copyProperties(jackSupplierPage, jackSupplier);
		JackSupplier jackSupplierEntity = jackSupplierService.getById(jackSupplier.getId());
		if(jackSupplierEntity==null) {
			return Result.error("未找到对应数据");
		}
		jackSupplierService.updateMain(jackSupplier, jackSupplierPage.getJackSupplierWarehouseList(),jackSupplierPage.getJackSupplierGoodsList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "jack supplier-通过id删除")
	@Operation(summary="jack supplier-通过id删除", description="jack supplier-通过id删除")
    @RequiresPermissions("jack:jack_supplier:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		jackSupplierService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "jack supplier-批量删除")
	@Operation(summary="jack supplier-批量删除", description="jack supplier-批量删除")
    @RequiresPermissions("jack:jack_supplier:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.jackSupplierService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "jack supplier-通过id查询")
	@Operation(summary="jack supplier-通过id查询", description="jack supplier-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<JackSupplier> queryById(@RequestParam(name="id",required=true) String id) {
		JackSupplier jackSupplier = jackSupplierService.getById(id);
		if(jackSupplier==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(jackSupplier);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "jack_supplier_warehouse通过主表ID查询")
	@Operation(summary="jack_supplier_warehouse主表ID查询", description="jack_supplier_warehouse-通主表ID查询")
	@GetMapping(value = "/queryJackSupplierWarehouseByMainId")
	public Result<List<JackSupplierWarehouse>> queryJackSupplierWarehouseListByMainId(@RequestParam(name="id",required=true) String id) {
		String supplierName = jackSupplierService.getById(id).getSupplierName();
		List<JackSupplierWarehouse> jackSupplierWarehouseList = jackSupplierWarehouseService.selectByMainId(supplierName);
		return Result.OK(jackSupplierWarehouseList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "jack_supplier_goods通过主表ID查询")
	@Operation(summary="jack_supplier_goods主表ID查询", description="jack_supplier_goods-通主表ID查询")
	@GetMapping(value = "/queryJackSupplierGoodsByMainId")
	public Result<List<JackSupplierGoods>> queryJackSupplierGoodsListByMainId(@RequestParam(name="id",required=true) String id) {
		List<JackSupplierGoods> jackSupplierGoodsList = jackSupplierGoodsService.selectByMainId(id);
		return Result.OK(jackSupplierGoodsList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param jackSupplier
    */
    @RequiresPermissions("jack:jack_supplier:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JackSupplier jackSupplier) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<JackSupplier> queryWrapper = QueryGenerator.initQueryWrapper(jackSupplier, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //配置选中数据查询条件
      String selections = request.getParameter("selections");
      if(oConvertUtils.isNotEmpty(selections)) {
         List<String> selectionList = Arrays.asList(selections.split(","));
         queryWrapper.in("id",selectionList);
      }
      //Step.2 获取导出数据
      List<JackSupplier> jackSupplierList = jackSupplierService.list(queryWrapper);

      // Step.3 组装pageList
      List<JackSupplierPage> pageList = new ArrayList<JackSupplierPage>();
      for (JackSupplier main : jackSupplierList) {
          JackSupplierPage vo = new JackSupplierPage();
          BeanUtils.copyProperties(main, vo);
          List<JackSupplierWarehouse> jackSupplierWarehouseList = jackSupplierWarehouseService.selectByMainId(main.getId());
          vo.setJackSupplierWarehouseList(jackSupplierWarehouseList);
          List<JackSupplierGoods> jackSupplierGoodsList = jackSupplierGoodsService.selectByMainId(main.getId());
          vo.setJackSupplierGoodsList(jackSupplierGoodsList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "jack supplier列表");
      mv.addObject(NormalExcelConstants.CLASS, JackSupplierPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("jack supplier数据", "导出人:"+sysUser.getRealname(), "jack supplier"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("jack:jack_supplier:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          // 获取上传文件对象
          MultipartFile file = entity.getValue();
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<JackSupplierPage> list = ExcelImportUtil.importExcel(file.getInputStream(), JackSupplierPage.class, params);
              for (JackSupplierPage page : list) {
                  JackSupplier po = new JackSupplier();
                  BeanUtils.copyProperties(page, po);
                  jackSupplierService.saveMain(po, page.getJackSupplierWarehouseList(),page.getJackSupplierGoodsList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
