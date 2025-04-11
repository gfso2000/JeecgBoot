package org.jeecg.modules.demo.jack.service;

import org.jeecg.modules.demo.jack.entity.JackSupplierGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: jack_supplier_goods
 * @Author: jeecg-boot
 * @Date:   2025-04-02
 * @Version: V1.0
 */
public interface IJackSupplierGoodsService extends IService<JackSupplierGoods> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<JackSupplierGoods>
	 */
	public List<JackSupplierGoods> selectByMainId(String mainId);
}
