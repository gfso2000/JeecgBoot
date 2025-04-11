import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import {JVxeTypes,JVxeColumn} from '/@/components/jeecg/JVxeTable/types'
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: 'supplier name',
    align:"center",
    dataIndex: 'supplierName'
   },
   {
    title: 'supplier_dropdown',
    align:"center",
    dataIndex: 'supplierDropdown_dictText'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "更新日期",
      field: "updateTime",
      component: 'DatePicker',
      componentProps: {
         showTime:true,
         valueFormat: 'YYYY-MM-DD HH:mm:ss'
       },
      //colProps: {span: 6},
 	},
	{
      label: "所属部门",
      field: "sysOrgCode",
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "supplier name",
      field: "supplierName",
      component: 'Input',
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: 'supplier name',
    field: 'supplierName',
    component: 'Input',
  },
  {
    label: 'supplier_dropdown',
    field: 'supplierDropdown',
    component: 'JSearchSelect',
    componentProps:{
       dict:"sys_user where username like '%a%',email,email"
    },
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];
//子表单数据
export const jackSupplierWarehouseFormSchema: FormSchema[] = [
  {
    label: 'warehouse',
    field: 'warehouse',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:""
     },
  },
  {
    label: 'contact',
    field: 'contact',
    component: 'JSearchSelect',
    componentProps:{
       dict:"sys_user where username like '%a%',email,email"
    },
  },
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];
//子表表格配置
export const jackSupplierGoodsColumns: JVxeColumn[] = [
    {
      title: 'num',
      key: 'num',
      type: JVxeTypes.inputNumber,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'goods',
      key: 'goods',
      type: JVxeTypes.select,
      options:[],
      dictCode:"",
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'supplier_name',
      key: 'supplierName',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'contact',
      key: 'contact',
      type: JVxeTypes.selectSearch,
      dictCode:"sys_user where username like '%a%',email,email",
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
  ]


// 高级查询数据
export const superQuerySchema = {
  supplierName: {title: 'supplier name',order: 2,view: 'text', type: 'string',},
  supplierDropdown: {title: 'supplier_dropdown',order: 3,view: 'sel_search', type: 'string',dictTable: "sys_user where username like '%a%'", dictCode: 'email', dictText: 'email',},
  //子表高级查询
  jackSupplierWarehouse: {
    title: 'jack_supplier_warehouse',
    view: 'table',
    fields: {
        warehouse: {title: 'warehouse',order: 0,view: 'list', type: 'string',dictCode: '',},
        contact: {title: 'contact',order: 1,view: 'sel_search', type: 'string',dictTable: "sys_user where username like '%a%'", dictCode: 'email', dictText: 'email',},
    }
  },
  jackSupplierGoods: {
    title: 'jack_supplier_goods',
    view: 'table',
    fields: {
        num: {title: 'num',order: 0,view: 'number', type: 'number',},
        goods: {title: 'goods',order: 1,view: 'list', type: 'string',dictCode: '',},
        supplierName: {title: 'supplier_name',order: 2,view: 'text', type: 'string',},
        contact: {title: 'contact',order: 3,view: 'sel_search', type: 'string',dictTable: "sys_user where username like '%a%'", dictCode: 'email', dictText: 'email',},
    }
  },
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
// 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}