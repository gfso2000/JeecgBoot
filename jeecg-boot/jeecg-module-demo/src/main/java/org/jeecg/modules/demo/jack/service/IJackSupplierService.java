package org.jeecg.modules.demo.jack.service;

import org.jeecg.modules.demo.jack.entity.JackSupplierWarehouse;
import org.jeecg.modules.demo.jack.entity.JackSupplierGoods;
import org.jeecg.modules.demo.jack.entity.JackSupplier;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: jack supplier
 * @Author: jeecg-boot
 * @Date:   2025-04-02
 * @Version: V1.0
 */
public interface IJackSupplierService extends IService<JackSupplier> {

	/**
	 * 添加一对多
	 *
	 * @param jackSupplier
	 * @param jackSupplierWarehouseList
	 * @param jackSupplierGoodsList
	 */
	public void saveMain(JackSupplier jackSupplier,List<JackSupplierWarehouse> jackSupplierWarehouseList,List<JackSupplierGoods> jackSupplierGoodsList) ;
	
	/**
	 * 修改一对多
	 *
   * @param jackSupplier
   * @param jackSupplierWarehouseList
   * @param jackSupplierGoodsList
	 */
	public void updateMain(JackSupplier jackSupplier,List<JackSupplierWarehouse> jackSupplierWarehouseList,List<JackSupplierGoods> jackSupplierGoodsList);
	
	/**
	 * 删除一对多
	 *
	 * @param id
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 *
	 * @param idList
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
