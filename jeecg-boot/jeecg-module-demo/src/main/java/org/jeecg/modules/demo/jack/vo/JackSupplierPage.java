package org.jeecg.modules.demo.jack.vo;

import java.util.List;
import org.jeecg.modules.demo.jack.entity.JackSupplier;
import org.jeecg.modules.demo.jack.entity.JackSupplierWarehouse;
import org.jeecg.modules.demo.jack.entity.JackSupplierGoods;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecg.common.constant.ProvinceCityArea;
import org.jeecg.common.util.SpringContextUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: jack supplier
 * @Author: jeecg-boot
 * @Date:   2025-04-02
 * @Version: V1.0
 */
@Data
@ApiModel(value="jack_supplierPage对象", description="jack supplier")
public class JackSupplierPage {

	/**主键*/
	@ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**supplier name*/
	@Excel(name = "supplier name", width = 15)
	@ApiModelProperty(value = "supplier name")
    private java.lang.String supplierName;
	/**supplier_dropdown*/
	@Excel(name = "supplier_dropdown", width = 15, dictTable = "sys_user where username like '%a%'", dicText = "email", dicCode = "email")
    @Dict(dictTable = "sys_user where username like '%a%'", dicText = "email", dicCode = "email")
	@ApiModelProperty(value = "supplier_dropdown")
    private java.lang.String supplierDropdown;

	@ExcelCollection(name="jack_supplier_warehouse")
	@ApiModelProperty(value = "jack_supplier_warehouse")
	private List<JackSupplierWarehouse> jackSupplierWarehouseList;
	@ExcelCollection(name="jack_supplier_goods")
	@ApiModelProperty(value = "jack_supplier_goods")
	private List<JackSupplierGoods> jackSupplierGoodsList;

}
