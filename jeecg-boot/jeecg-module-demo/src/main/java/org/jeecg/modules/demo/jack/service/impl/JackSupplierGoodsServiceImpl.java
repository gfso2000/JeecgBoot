package org.jeecg.modules.demo.jack.service.impl;

import org.jeecg.modules.demo.jack.entity.JackSupplierGoods;
import org.jeecg.modules.demo.jack.mapper.JackSupplierGoodsMapper;
import org.jeecg.modules.demo.jack.service.IJackSupplierGoodsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: jack_supplier_goods
 * @Author: jeecg-boot
 * @Date:   2025-04-02
 * @Version: V1.0
 */
@Service
public class JackSupplierGoodsServiceImpl extends ServiceImpl<JackSupplierGoodsMapper, JackSupplierGoods> implements IJackSupplierGoodsService {
	
	@Autowired
	private JackSupplierGoodsMapper jackSupplierGoodsMapper;
	
	@Override
	public List<JackSupplierGoods> selectByMainId(String mainId) {
		return jackSupplierGoodsMapper.selectByMainId(mainId);
	}
}
