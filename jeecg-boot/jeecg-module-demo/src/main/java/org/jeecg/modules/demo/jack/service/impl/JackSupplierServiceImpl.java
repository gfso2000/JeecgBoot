package org.jeecg.modules.demo.jack.service.impl;

import org.jeecg.modules.demo.jack.entity.JackSupplier;
import org.jeecg.modules.demo.jack.entity.JackSupplierWarehouse;
import org.jeecg.modules.demo.jack.entity.JackSupplierGoods;
import org.jeecg.modules.demo.jack.mapper.JackSupplierWarehouseMapper;
import org.jeecg.modules.demo.jack.mapper.JackSupplierGoodsMapper;
import org.jeecg.modules.demo.jack.mapper.JackSupplierMapper;
import org.jeecg.modules.demo.jack.service.IJackSupplierService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: jack supplier
 * @Author: jeecg-boot
 * @Date:   2025-04-02
 * @Version: V1.0
 */
@Service
public class JackSupplierServiceImpl extends ServiceImpl<JackSupplierMapper, JackSupplier> implements IJackSupplierService {

	@Autowired
	private JackSupplierMapper jackSupplierMapper;
	@Autowired
	private JackSupplierWarehouseMapper jackSupplierWarehouseMapper;
	@Autowired
	private JackSupplierGoodsMapper jackSupplierGoodsMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(JackSupplier jackSupplier, List<JackSupplierWarehouse> jackSupplierWarehouseList,List<JackSupplierGoods> jackSupplierGoodsList) {
		jackSupplierMapper.insert(jackSupplier);
		if(jackSupplierWarehouseList!=null && jackSupplierWarehouseList.size()>0) {
			for(JackSupplierWarehouse entity:jackSupplierWarehouseList) {
				//外键设置
				entity.setSupplierName(jackSupplier.getSupplierName());
				jackSupplierWarehouseMapper.insert(entity);
			}
		}
		if(jackSupplierGoodsList!=null && jackSupplierGoodsList.size()>0) {
			for(JackSupplierGoods entity:jackSupplierGoodsList) {
				//外键设置
				entity.setSupplierName(jackSupplier.getSupplierName());
				jackSupplierGoodsMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(JackSupplier jackSupplier,List<JackSupplierWarehouse> jackSupplierWarehouseList,List<JackSupplierGoods> jackSupplierGoodsList) {
		jackSupplierMapper.updateById(jackSupplier);
		
		//1.先删除子表数据
		jackSupplierWarehouseMapper.deleteByMainId(jackSupplier.getId());
		jackSupplierGoodsMapper.deleteByMainId(jackSupplier.getId());
		
		//2.子表数据重新插入
		if(jackSupplierWarehouseList!=null && jackSupplierWarehouseList.size()>0) {
			for(JackSupplierWarehouse entity:jackSupplierWarehouseList) {
				//外键设置
				entity.setSupplierName(jackSupplier.getSupplierName());
				jackSupplierWarehouseMapper.insert(entity);
			}
		}
		if(jackSupplierGoodsList!=null && jackSupplierGoodsList.size()>0) {
			for(JackSupplierGoods entity:jackSupplierGoodsList) {
				//外键设置
				entity.setSupplierName(jackSupplier.getSupplierName());
				jackSupplierGoodsMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		jackSupplierWarehouseMapper.deleteByMainId(id);
		jackSupplierGoodsMapper.deleteByMainId(id);
		jackSupplierMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			jackSupplierWarehouseMapper.deleteByMainId(id.toString());
			jackSupplierGoodsMapper.deleteByMainId(id.toString());
			jackSupplierMapper.deleteById(id);
		}
	}
	
}
