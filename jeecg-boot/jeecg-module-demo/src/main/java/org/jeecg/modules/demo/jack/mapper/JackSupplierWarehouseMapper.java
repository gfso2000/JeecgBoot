package org.jeecg.modules.demo.jack.mapper;

import java.util.List;
import org.jeecg.modules.demo.jack.entity.JackSupplierWarehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: jack_supplier_warehouse
 * @Author: jeecg-boot
 * @Date:   2025-04-02
 * @Version: V1.0
 */
public interface JackSupplierWarehouseMapper extends BaseMapper<JackSupplierWarehouse> {

	/**
	 * 通过主表id删除子表数据
	 *
	 * @param mainId 主表id
	 * @return boolean
	 */
	public boolean deleteByMainId(@Param("mainId") String mainId);

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId 主表id
   * @return List<JackSupplierWarehouse>
   */
	public List<JackSupplierWarehouse> selectByMainId(@Param("mainId") String mainId);
}
