package org.jeecg.modules.demo.jack.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.query.QueryRuleEnum;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.config.shiro.IgnoreAuth;
import org.jeecg.modules.demo.jack.entity.JackContact;
import org.jeecg.modules.demo.jack.service.IJackContactService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.system.model.TreeSelectModel;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: jack contact
 * @Author: jeecg-boot
 * @Date:   2025-03-20
 * @Version: V1.0
 */
@Api(tags="jack contact")
@RestController
@RequestMapping("/jack/jackContact")
@Slf4j
public class JackContactController extends JeecgController<JackContact, IJackContactService> {
	@Autowired
	private IJackContactService jackContactService;

	 @PostMapping("/testhttpapi")
	 public Result enhanceJavaFormHttp(@RequestBody JSONObject params) {
		 log.info(" =========================================================== ");
		 log.info("params: " + params.toJSONString());
		 log.info("params.tableName: " + params.getString("tableName"));
		 log.info("params.record: " + params.getJSONObject("record").toJSONString());
		 log.info(" =========================================================== ");
		 return Result.OK(params.getJSONObject("record").toJSONString());
	 }

	 @IgnoreAuth
	 @GetMapping("/loadTreeData")
	 @AutoLog(value = "添加测试DEMO")
	 public Result<List<TreeSelectModel>> loadTreeData(HttpServletRequest request) {
		 Result<List<TreeSelectModel>> result = new Result<List<TreeSelectModel>>();

		 // 此处模拟json，见下方test.json， 实际根据参数自行查询
		 String jsonData = oConvertUtils.readStatic("classpath:static/test/test.json");
		 JSONObject json = JSONObject.parseObject(jsonData);
		 JSONArray array = json.getJSONArray("data");
		 List<TreeSelectModel> list = array.toJavaList(TreeSelectModel.class);

		 result.setSuccess(true);
		 result.setResult(list);
		 return result;
	 }

	/**
	 * 分页列表查询
	 *
	 * @param jackContact
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "jack contact-分页列表查询")
	@ApiOperation(value="jack contact-分页列表查询", notes="jack contact-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<JackContact>> queryPageList(JackContact jackContact,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<JackContact> queryWrapper = QueryGenerator.initQueryWrapper(jackContact, req.getParameterMap());
		Page<JackContact> page = new Page<JackContact>(pageNo, pageSize);
		IPage<JackContact> pageList = jackContactService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param jackContact
	 * @return
	 */
	@AutoLog(value = "jack contact-添加")
	@ApiOperation(value="jack contact-添加", notes="jack contact-添加")
	@RequiresPermissions("jack:jack_contact:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody JackContact jackContact) {
		jackContactService.save(jackContact);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param jackContact
	 * @return
	 */
	@AutoLog(value = "jack contact-编辑")
	@ApiOperation(value="jack contact-编辑", notes="jack contact-编辑")
	@RequiresPermissions("jack:jack_contact:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody JackContact jackContact) {
		jackContactService.updateById(jackContact);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "jack contact-通过id删除")
	@ApiOperation(value="jack contact-通过id删除", notes="jack contact-通过id删除")
	@RequiresPermissions("jack:jack_contact:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		jackContactService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "jack contact-批量删除")
	@ApiOperation(value="jack contact-批量删除", notes="jack contact-批量删除")
	@RequiresPermissions("jack:jack_contact:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.jackContactService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "jack contact-通过id查询")
	@ApiOperation(value="jack contact-通过id查询", notes="jack contact-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<JackContact> queryById(@RequestParam(name="id",required=true) String id) {
		JackContact jackContact = jackContactService.getById(id);
		if(jackContact==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(jackContact);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param jackContact
    */
    @RequiresPermissions("jack:jack_contact:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JackContact jackContact) {
        return super.exportXls(request, jackContact, JackContact.class, "jack contact");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("jack:jack_contact:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, JackContact.class);
    }

}
