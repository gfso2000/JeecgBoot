package org.jeecg.modules.demo.jack.service.impl;

import org.jeecg.modules.demo.jack.entity.JackSupplierWarehouse;
import org.jeecg.modules.demo.jack.mapper.JackSupplierWarehouseMapper;
import org.jeecg.modules.demo.jack.service.IJackSupplierWarehouseService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: jack_supplier_warehouse
 * @Author: jeecg-boot
 * @Date:   2025-04-02
 * @Version: V1.0
 */
@Service
public class JackSupplierWarehouseServiceImpl extends ServiceImpl<JackSupplierWarehouseMapper, JackSupplierWarehouse> implements IJackSupplierWarehouseService {
	
	@Autowired
	private JackSupplierWarehouseMapper jackSupplierWarehouseMapper;
	
	@Override
	public List<JackSupplierWarehouse> selectByMainId(String mainId) {
		return jackSupplierWarehouseMapper.selectByMainId(mainId);
	}
}
