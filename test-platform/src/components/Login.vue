<template>
  <div  class="loginPart">
    <h2>用户登录</h2>
        <el-form :model="form" :rules="rules" ref="form"  class="demo-ruleForm" style="width: 300px;margin-left: 25px">
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
          axios.post("/api/login", {
              'name':this.form.name,
              'pswd':this.form.pswd

          },{headers:{
              'Content-Type': 'application/json;charset=utf-8',
              'Accept': 'application/json',
            },withCredentials: true, },
          ).then(res=>{
            if(JSON.parse(JSON.stringify(res.data)) === 1){
              sessionStorage.setItem('token',this.form.name)
              this.$store.dispatch("asynShow",this.form.name)
              this.$store.dispatch("asynChange",0)
              alert('登录成功!');
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
.loginPart{
  position:absolute;
  /*定位方式绝对定位absolute*/
  top:50%;
  left:50%;
  /*顶和高同时设置50%实现的是同时水平垂直居中效果*/
  transform:translate(-50%,-50%);
  /*实现块元素百分比下居中*/
  width:450px;
  padding:50px;
  /*background: rgba(42, 41, 41, 0.5);*/
  /*背景颜色为黑色，透明度为0.8*/
  box-sizing:border-box;
  /*box-sizing设置盒子模型的解析模式为怪异盒模型，
  将border和padding划归到width范围内*/
  /*box-shadow: 0px 15px 25px rgba(0,0,0,.5);*/
  /*边框阴影  水平阴影0 垂直阴影15px 模糊25px 颜色黑色透明度0.5*/
  /*border-radius:15px;*/
  /*边框圆角，四个角均为15px*/
}
.loginPart h2{
  margin: 0 0 30px;
  padding: 0;
  color: #545c64;
  text-align: center;
  /*文字居中*/
}
</style>