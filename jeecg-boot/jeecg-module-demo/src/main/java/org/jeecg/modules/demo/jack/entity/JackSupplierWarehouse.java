package org.jeecg.modules.demo.jack.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: jack_supplier_warehouse
 * @Author: jeecg-boot
 * @Date:   2025-04-02
 * @Version: V1.0
 */
@Schema(name="jack_supplier_warehouse对象", description="jack_supplier_warehouse")
@Data
@TableName("jack_supplier_warehouse")
public class JackSupplierWarehouse implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
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
	/**warehouse*/
	@Excel(name = "warehouse", width = 15)
    @Schema(description = "warehouse")
    private java.lang.String warehouse;
	/**supplier_name*/
    @Schema(description = "supplier_name")
    private java.lang.String supplierName;
	/**contact*/
	@Excel(name = "contact", width = 15, dictTable = "sys_user where username like '%a%'", dicText = "email", dicCode = "email")
    @Schema(description = "contact")
    private java.lang.String contact;
}
