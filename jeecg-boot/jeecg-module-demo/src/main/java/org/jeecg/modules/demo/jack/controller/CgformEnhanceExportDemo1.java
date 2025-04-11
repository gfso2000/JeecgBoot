package org.jeecg.modules.demo.jack.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.online.cgform.enhance.CgformEnhanceJavaListInter;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

/**
 * Excel导出增强简单示例-数据转换
 */
@Slf4j
@Component("cgformEnhanceExportDemo1")
public class CgformEnhanceExportDemo1 implements CgformEnhanceJavaListInter {

    @Override
    public void execute(String tableName, List<Map<String, Object>> data) throws BusinessException {
        for (Map<String, Object> map : data) {
            // 获取项目名称字段的值
            Object projectName = map.get("project_name");
            if(projectName!=null){
                // 满足一定的条件 将值替换成自定义的格式
                if(projectName.toString().equalsIgnoreCase("abc")){
                    map.put("project_name", "这是字母abc");
                }else if(projectName.toString().equalsIgnoreCase("123")){
                    map.put("project_name", "这是数字123");
                }
            }
        }
    }
}