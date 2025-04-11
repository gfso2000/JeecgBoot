-- 注意：该页面对应的前台目录为views/jack文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2025040211075600500', NULL, 'jack supplier', '/jack/jackSupplierList', 'jack/JackSupplierList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2025-04-02 11:07:50', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025040211075610501', '2025040211075600500', '添加jack supplier', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_supplier:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-04-02 11:07:50', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025040211075610502', '2025040211075600500', '编辑jack supplier', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_supplier:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-04-02 11:07:50', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025040211075610503', '2025040211075600500', '删除jack supplier', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_supplier:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-04-02 11:07:50', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025040211075610504', '2025040211075600500', '批量删除jack supplier', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_supplier:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-04-02 11:07:50', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025040211075610505', '2025040211075600500', '导出excel_jack supplier', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_supplier:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-04-02 11:07:50', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025040211075610506', '2025040211075600500', '导入excel_jack supplier', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_supplier:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-04-02 11:07:50', NULL, NULL, 0, 0, '1', 0);