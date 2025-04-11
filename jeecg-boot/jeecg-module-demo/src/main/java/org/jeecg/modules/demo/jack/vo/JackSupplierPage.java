package org.jeecg.modules.demo.jack.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecg.modules.demo.jack.entity.JackSupplierGoods;
import org.jeecg.modules.demo.jack.entity.JackSupplierWarehouse;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

/**
 * @Description: jack supplier
 * @Author: jeecg-boot
 * @Date:   2025-04-02
 * @Version: V1.0
 */
@Data
@Schema(name="jack_supplierPage对象", description="jack supplier")
public class JackSupplierPage {

	/**主键*/
	@Schema(description = "主键")
    private java.lang.String id;
	/**创建人*/
	@Schema(description = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Schema(description = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
	@Schema(description = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Schema(description = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
	@Schema(description = "所属部门")
    private java.lang.String sysOrgCode;
	/**supplier name*/
	@Excel(name = "supplier name", width = 15)
	@Schema(description = "supplier name")
    private java.lang.String supplierName;
	/**supplier_dropdown*/
	@Excel(name = "supplier_dropdown", width = 15, dictTable = "sys_user where username like '%a%'", dicText = "email", dicCode = "email")
    @Dict(dictTable = "sys_user where username like '%a%'", dicText = "email", dicCode = "email")
	@Schema(description = "supplier_dropdown")
    private java.lang.String supplierDropdown;

	@ExcelCollection(name="jack_supplier_warehouse")
	@Schema(description = "jack_supplier_warehouse")
	private List<JackSupplierWarehouse> jackSupplierWarehouseList;
	@ExcelCollection(name="jack_supplier_goods")
	@Schema(description = "jack_supplier_goods")
	private List<JackSupplierGoods> jackSupplierGoodsList;

}
