<template>
  <a-form :labelCol="{span:3}">
    <a-form-item label="名称" >
      <a-input placeholder="请输入名称" v-model:value="name"/>
    </a-form-item>
  </a-form>
</template>

<script>

import { ref, onMounted } from 'vue'
import { defHttp } from '/@/utils/http/axios'
import { useMessage } from '/@/hooks/web/useMessage';

export default {
  name: "hello",
  props: {
    // 行数据
    row: {
      type: Object,
      default: () => {
      },
      required: false
    },
    // 该地址是online默认的编辑提交地址 如不满足要求需自定义
    url: {
      type: String,
      default: '',
      required: false
    }
  },
  setup(props, {emit}) {
    const name = ref('')
    const { createMessage } = useMessage();

    onMounted(() => {
      name.value = props.row.my_name
    });

    /**
     * 自定义的表单页面 弹窗确定按钮触发函数名必须写：handleSubmit
     */
    function handleSubmit() {
      let params = Object.assign({}, props.row, { my_name: name.value });
      defHttp.put({ url: props.url, params }, { isTransformResponse: false }).then((res) => {
        if (res.success) {
          // 提交完毕 关闭弹窗 调用事件：emit('close')
          emit('close')
        } else {
          createMessage.warning(res.message)
        }
      });
    }
    return {
      name,
      handleSubmit
    }
  }

}
</script>
