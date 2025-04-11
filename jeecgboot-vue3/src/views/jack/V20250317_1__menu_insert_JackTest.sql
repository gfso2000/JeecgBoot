-- 注意：该页面对应的前台目录为views/jack文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2025031703058200560', NULL, 'jack desc', '/jack/jackTestList', 'jack/JackTestList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2025-03-17 15:05:56', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025031703058200561', '2025031703058200560', '添加jack desc', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_test:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-03-17 15:05:56', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025031703058200562', '2025031703058200560', '编辑jack desc', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_test:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-03-17 15:05:56', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025031703058200563', '2025031703058200560', '删除jack desc', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_test:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-03-17 15:05:56', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025031703058200564', '2025031703058200560', '批量删除jack desc', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_test:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-03-17 15:05:56', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025031703058200565', '2025031703058200560', '导出excel_jack desc', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_test:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-03-17 15:05:56', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025031703058200566', '2025031703058200560', '导入excel_jack desc', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_test:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-03-17 15:05:56', NULL, NULL, 0, 0, '1', 0);