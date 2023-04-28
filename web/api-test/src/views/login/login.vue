<script setup>
import { reactive, ref, onBeforeMount } from 'vue';
import { login } from '@/api';
import { encrypt } from '@/utils/jsencrypt.js';

// 变量定义
const formData = reactive({
  loginName: '',
  passwordPre: '',
  code: ''
});
const imgCode = ref('');
const rules = {
  loginName: { required: true, message: '请输入用户名', trigger: 'blur'},
  passwordPre: { required: true, message: '请输入密码', trigger: 'blur'},
  code: { required: true, message: '请输入验证码', trigger: 'blur'}
}
const formRef = ref();

// 钩子函数
onBeforeMount(()=> {
  getImgCode();
});

// 方法
// 获取验证码
const getImgCode = ()=> {
  imgCode.value = 'data:image/jpeg;base64,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';
}
// 登录
const submitLogin = async formRef=> {
  if (!formRef) return;
  await formRef.validate((valid, fields) => {
    if (valid) {
      formData.password = encrypt(formData.passwordPre);
      login(formData).then(res=> {

      });
      console.log('submit!')
    } else {
      console.log('error submit!', fields)
    }
  })
}
</script>
<template>
  <div class="wrap-login-out">
    <el-card class="wrap-login">
      <el-form :model="formData" :rules="rules" ref="formRef" label-width="80px">
        <el-form-item label="用户名" prop="loginName">
          <el-input v-model="formData.loginName" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="passwordPre">
          <el-input type="passwordPre" v-model="formData.passwordPre" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="验证码" prop="code">
          <el-input v-model="formData.code" placeholder="请输入验证码" style="width:120px;"></el-input>
          <img :src="imgCode" alt="验证码" />
        </el-form-item>
        <el-form-item>
      <el-button type="primary" @click="submitLogin(formRef)" style="width: 100%;">
        登录
      </el-button>
    </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<style lang="less" scoped>
.wrap-login-out {
  height: 100vh;;
  background-image: url("/bg.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  .wrap-login {
    width: 370px;
    position: absolute;
    top: 20vh;
    right:10vw;
  }
  img {
    width: 120px;
    height: 32px;
    margin-left: 10px;
    border-radius: 3px;
  }
  img[src=''], img:not([src]) {
    display: none;
  }
}
</style>