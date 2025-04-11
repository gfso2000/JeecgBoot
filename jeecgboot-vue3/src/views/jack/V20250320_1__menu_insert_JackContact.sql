-- 注意：该页面对应的前台目录为views/jack文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2025032002548950440', NULL, 'jack contact', '/jack/jackContactList', 'jack/JackContactList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2025-03-20 14:54:44', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025032002548950441', '2025032002548950440', '添加jack contact', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_contact:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-03-20 14:54:44', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025032002548950442', '2025032002548950440', '编辑jack contact', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_contact:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-03-20 14:54:44', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025032002548950443', '2025032002548950440', '删除jack contact', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_contact:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-03-20 14:54:44', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025032002548950444', '2025032002548950440', '批量删除jack contact', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_contact:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-03-20 14:54:44', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025032002548950445', '2025032002548950440', '导出excel_jack contact', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_contact:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-03-20 14:54:44', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2025032002548950446', '2025032002548950440', '导入excel_jack contact', NULL, NULL, 0, NULL, NULL, 2, 'jack:jack_contact:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-03-20 14:54:44', NULL, NULL, 0, 0, '1', 0);