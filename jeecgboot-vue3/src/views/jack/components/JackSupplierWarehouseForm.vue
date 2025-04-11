<template>
    <BasicForm @register="registerForm" name="JackSupplierWarehouseForm" class="basic-modal-form"/>
</template>
<script lang="ts">
    import {defineComponent} from 'vue';
    import {BasicForm, useForm} from '/@/components/Form/index';
    import {jackSupplierWarehouseFormSchema} from '../JackSupplier.data';
    import {defHttp} from '/@/utils/http/axios';
    import { VALIDATE_FAILED } from '/@/utils/common/vxeUtils'

    export default defineComponent({
        name:"JackSupplierWarehouseForm",
        components: {BasicForm},
        emits:['register'],
        props:{
            disabled: {
                type: Boolean,
                default: false
            }
        },
        setup(props,{emit}) {
            const [registerForm, { setProps, resetFields, setFieldsValue, getFieldsValue, validate, scrollToField }] = useForm({
                labelWidth: 150,
                schemas: jackSupplierWarehouseFormSchema,
                showActionButtonGroup: false,
                baseColProps: {span: 24}
            });
            /**
            *初始化加载数据
            */
            function initFormData(url,id){
                if(id){
                     defHttp.get({url,params:{id}},{isTransformResponse:false}).then(res=>{
                       res.success && setFieldsValue({...res.result[0]});
                    })
                }
                setProps({disabled: props.disabled})
            }
           /**
            *获取表单数据
            */
            function getFormData(){
               let formData = getFieldsValue();
               Object.keys(formData).map(k=>{
                    if(formData[k] instanceof Array){
                        formData[k] = formData[k].join(',')
                    }
               });
               return [formData];
            }
            /**
            *表单校验
            */
            function validateForm(index){
                return new Promise((resolve, reject) => {
                    // 验证子表表单
                    validate().then(()=>{
                        return resolve()
                    }).catch(({ errorFields }) => {
                      return reject({ error: VALIDATE_FAILED, index, errorFields: errorFields, scrollToField: scrollToField });
                    });
                })
            }
            return {
                registerForm,
                resetFields,
                initFormData,
                getFormData,
                validateForm
            }
        }
    })
</script>
<style lang="less" scoped>
  .basic-modal-form {
    overflow: auto;
    height: 340px;
  }
</style>
