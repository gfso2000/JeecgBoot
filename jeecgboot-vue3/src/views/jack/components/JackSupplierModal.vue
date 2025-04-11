<template>
  <BasicModal v-bind="$attrs" @register="registerModal" destroyOnClose :title="title" :width="800" @ok="handleSubmit">
    <BasicForm @register="registerForm" ref="formRef" name="JackSupplierForm"/>
    <!-- 子表单区域 -->
    <a-tabs v-model:activeKey="activeKey" animated @change="handleChangeTabs">
      <a-tab-pane tab="jack_supplier_warehouse" key="jackSupplierWarehouse" :forceRender="true">
        <JackSupplierWarehouseForm ref="jackSupplierWarehouseForm" :disabled="formDisabled"></JackSupplierWarehouseForm>
      </a-tab-pane>

      <a-tab-pane tab="jack_supplier_goods" key="jackSupplierGoods" :forceRender="true">
        <JVxeTable
          keep-source
          resizable
          ref="jackSupplierGoods"
          :loading="jackSupplierGoodsTable.loading"
          :columns="jackSupplierGoodsTable.columns"
          :dataSource="jackSupplierGoodsTable.dataSource"
          :height="340"
          :rowNumber="true"
          :rowSelection="true"
          :disabled="formDisabled"
          :toolbar="true"
          />
      </a-tab-pane>
    </a-tabs>
  </BasicModal>
</template>

<script lang="ts" setup>
    import {ref, computed, unref,reactive} from 'vue';
    import {BasicModal, useModalInner} from '/@/components/Modal';
    import {BasicForm, useForm} from '/@/components/Form/index';
    import { JVxeTable } from '/@/components/jeecg/JVxeTable'
    import { useJvxeMethod } from '/@/hooks/system/useJvxeMethods.ts'
    import JackSupplierWarehouseForm from './JackSupplierWarehouseForm.vue'
    import {formSchema,jackSupplierGoodsColumns} from '../JackSupplier.data';
    import {saveOrUpdate,jackSupplierWarehouseList,jackSupplierGoodsList} from '../JackSupplier.api';
    import { VALIDATE_FAILED } from '/@/utils/common/vxeUtils'
    // Emits声明
    const emit = defineEmits(['register','success']);
    const isUpdate = ref(true);
    const formDisabled = ref(false);
    const refKeys = ref(['jackSupplierWarehouse', 'jackSupplierGoods', ]);
    const activeKey = ref('jackSupplierWarehouse');
    const jackSupplierWarehouseForm = ref();
    const jackSupplierGoods = ref();
    const tableRefs = {jackSupplierGoods, };
    const jackSupplierGoodsTable = reactive({
          loading: false,
          dataSource: [],
          columns:jackSupplierGoodsColumns
    })
    //表单配置
    const [registerForm, {setProps,resetFields, setFieldsValue, validate}] = useForm({
        labelWidth: 150,
        schemas: formSchema,
        showActionButtonGroup: false,
        baseColProps: {span: 24}
    });
     //表单赋值
    const [registerModal, {setModalProps, closeModal}] = useModalInner(async (data) => {
        //重置表单
        await reset();
        setModalProps({confirmLoading: false,showCancelBtn:data?.showFooter,showOkBtn:data?.showFooter});
        isUpdate.value = !!data?.isUpdate;
        formDisabled.value = !data?.showFooter;
        if (unref(isUpdate)) {
            //表单赋值
            await setFieldsValue({
                ...data.record,
            });
             jackSupplierWarehouseForm.value.initFormData(jackSupplierWarehouseList,data?.record?.id)
             requestSubTableData(jackSupplierGoodsList, {id:data?.record?.id}, jackSupplierGoodsTable)
        }
        // 隐藏底部时禁用整个表单
       setProps({ disabled: !data?.showFooter })
    });
    //方法配置
    const [handleChangeTabs,handleSubmit,requestSubTableData,formRef] = useJvxeMethod(requestAddOrEdit,classifyIntoFormData,tableRefs,activeKey,refKeys,validateSubForm);

    //设置标题
    const title = computed(() => (!unref(isUpdate) ? '新增' : !unref(formDisabled) ? '编辑' : '详情'));

    async function reset(){
      await resetFields();
      activeKey.value = 'jackSupplierWarehouse';
      jackSupplierWarehouseForm.value.resetFields();
      jackSupplierGoodsTable.dataSource = [];
    }
    function classifyIntoFormData(allValues) {
         let main = Object.assign({}, allValues.formValue)
         return {
           ...main, // 展开
           jackSupplierWarehouseList: jackSupplierWarehouseForm.value.getFormData(),
           jackSupplierGoodsList: allValues.tablesValue[0].tableData,
         }
       }
     //校验所有一对一子表表单
     function validateSubForm(allValues){
         return new Promise((resolve,reject)=>{
             Promise.all([
                  jackSupplierWarehouseForm.value.validateForm(0),
             ]).then(() => {
                 resolve(allValues)
             }).catch(e => {
                 if (e.error === VALIDATE_FAILED) {
                     // 如果有未通过表单验证的子表，就自动跳转到它所在的tab
                     activeKey.value = e.index == null ? unref(activeKey) : refKeys.value[e.index]
                     if (e.errorFields) {
                       const firstField = e.errorFields[0];
                       if (firstField) {
                         e.scrollToField(firstField.name, { behavior: 'smooth', block: 'center' });
                       }
                     }
                 } else {
                     console.error(e)
                 }
             })
         })
     }
    //表单提交事件
    async function requestAddOrEdit(values) {
        try {
            setModalProps({confirmLoading: true});
            //提交表单
            await saveOrUpdate(values, isUpdate.value);
            //关闭弹窗
            closeModal();
            //刷新列表
            emit('success');
        } finally {
            setModalProps({confirmLoading: false});
        }
    }
</script>

<style lang="less" scoped>
	/** 时间和数字输入框样式 */
  :deep(.ant-input-number) {
    width: 100%;
  }

  :deep(.ant-calendar-picker) {
    width: 100%;
  }
</style>