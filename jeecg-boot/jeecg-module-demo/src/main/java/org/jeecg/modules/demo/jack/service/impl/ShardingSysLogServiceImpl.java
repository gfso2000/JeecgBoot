package org.jeecg.modules.demo.jack.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.jack.entity.ShardingSysLog;
import org.jeecg.modules.demo.jack.mapper.ShardingSysLogMapper;
import org.jeecg.modules.demo.jack.service.IShardingSysLogService;
import org.springframework.stereotype.Service;

/**
 * 系统日志表 服务实现类
 * @author: zyf
 * @date: 2022/04/21
 */
@Service
@DS("sharding")
public class ShardingSysLogServiceImpl extends ServiceImpl<ShardingSysLogMapper, ShardingSysLog> implements IShardingSysLogService {

}
