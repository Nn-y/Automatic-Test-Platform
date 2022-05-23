<template>
  <el-container>
    <el-main style="display: flex;justify-content: center;">
      <div style="margin-top: 200px;">
        <el-form :model="form" :rules="rules" ref="form"  class="demo-ruleForm" style="width: 300px; ">
          <el-form-item prop="name">
            <el-input v-model="form.name" placeholder="用户名" type="text"></el-input>
          </el-form-item>
          <el-form-item prop="pswd">
            <el-input v-model="form.pswd"  autocomplete="off" placeholder="密码" type="password" show-password></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('form')">提交</el-button>
            <el-button type="primary" @click="signIn">注册</el-button>
          </el-form-item>
        </el-form>
      </div>

    </el-main>
  </el-container>

</template>

<script>
import axios from "axios";

export default {
  name: "Login",
  data(){
    return{
      form:{
        name:'',
        pswd:''
      },
      rules:{
        name:[
          {
            required: true, message: '请输入用户名', trigger: 'blur'
          }
        ],
        pswd:[
          {
            required: true, message: '请输入密码', trigger: 'blur'
          }
        ]
      }
    }
  },
  methods:{
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post("http://192.168.0.1:9090/login", {
              'name':this.form.name,
              'pswd':this.form.pswd

          }).then(res=>{
            if(JSON.parse(JSON.stringify(res.data)) === 1){
              sessionStorage.setItem('token',this.form.name)
              this.$store.dispatch("asynShow",this.form.name)
              this.$store.dispatch("asynChange",0)
              alert('submit!');
              this.$router.push({path:'/Test'})
            }else {
              this.$message.error('用户名或密码错误！');
            }
          })

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    signIn(){
      this.$router.push({path:'/Register'})
    }
  }
}
</script>

<style scoped>

</style>