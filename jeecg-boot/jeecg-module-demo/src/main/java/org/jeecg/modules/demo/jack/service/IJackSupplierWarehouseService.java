package org.jeecg.modules.demo.jack.service;

import org.jeecg.modules.demo.jack.entity.JackSupplierWarehouse;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: jack_supplier_warehouse
 * @Author: jeecg-boot
 * @Date:   2025-04-02
 * @Version: V1.0
 */
public interface IJackSupplierWarehouseService extends IService<JackSupplierWarehouse> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<JackSupplierWarehouse>
	 */
	public List<JackSupplierWarehouse> selectByMainId(String mainId);
}
