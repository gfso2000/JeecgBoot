package org.jeecg.modules.demo.jack.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import org.jeecg.common.constant.ProvinceCityArea;
import org.jeecg.common.util.SpringContextUtils;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.UnsupportedEncodingException;

/**
 * @Description: jack_supplier_goods
 * @Author: jeecg-boot
 * @Date:   2025-04-02
 * @Version: V1.0
 */
@ApiModel(value="jack_supplier_goods对象", description="jack_supplier_goods")
@Data
@TableName("jack_supplier_goods")
public class JackSupplierGoods implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
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
	/**num*/
	@Excel(name = "num", width = 15)
    @ApiModelProperty(value = "num")
    private java.lang.Double num;
	/**goods*/
	@Excel(name = "goods", width = 15)
    @ApiModelProperty(value = "goods")
    private java.lang.String goods;
	/**supplier_name*/
    @ApiModelProperty(value = "supplier_name")
    private java.lang.String supplierName;
	/**contact*/
	@Excel(name = "contact", width = 15, dictTable = "sys_user where username like '%a%'", dicText = "email", dicCode = "email")
    @ApiModelProperty(value = "contact")
    private java.lang.String contact;
}
