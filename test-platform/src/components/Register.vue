<template>
  <el-container>
  <el-main style="display: flex;justify-content: center;">
    <div style="margin-top: 200px;">
  <el-form :model="form" :rules="rules" ref="form" style="width: 300px; ">
    <el-form-item prop="name">
      <el-input v-model="form.name" type="text" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item prop="pswd">
      <el-input v-model="form.pswd" placeholder="密码" autocomplete="off" type="password" show-password></el-input>
    </el-form-item>
    <el-form-item prop="checkpass">
      <el-input type="password" placeholder="确认密码" v-model="form.checkpass" autocomplete="off" show-password></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('form')">确定</el-button>
      <el-button @click="resetForm('form')">重置</el-button>
    </el-form-item>
  </el-form>
      </div>
  </el-main>
  </el-container>
</template>

<script>
import axios from "axios";
export default {
  name: "Register",
  data(){
    let validateName = (rule, value, callback) =>{
      if (value === '') {
        callback(new Error('请输入用户名'));
      } else {
        if (this.form.name !== '') {
          axios.get("http://192.168.0.1:9090/register/isexist",{
            params:{
              name:this.form.name
            }
          }).then(res=>{
            // console.log(typeof (res.data))
            if(JSON.parse(JSON.stringify(res.data)) === 1 ){
              callback(new Error('用户名已存在'));
            }else {
              this.$refs.ruleForm.validateField('name');
              callback();
            }
          })

        }
        callback();
      }
    };
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.form.checkpass !== '') {
          this.$refs.ruleForm.validateField('checkpass');
        }
        callback();
      }
    };
    let validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.form.pswd) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return{
      form:{
        name:'',
        pswd:'',
        checkpass:''
      },
      rules: {
        name:[
          {
            required: true, validator: validateName, trigger: 'blur'
          }
        ],
        pswd: [
          {required: true, validator: validatePass, trigger: 'blur' }
        ],
        checkpass: [
          { required: true,validator: validatePass2, trigger: 'blur' }
        ],
      }
    }

  },

  methods:{
    submitForm(formName) {
      console.log('111')
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.get("http://192.168.0.1:9090/register",{
            params:{
              name:this.form.name,
              pswd:this.form.pswd
            }
          })
          alert('注册成功!');
          this.$router.push({path:'/'})

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>
/*.loginPart{*/
/*  position:absolute;*/
/*  !*定位方式绝对定位absolute*!*/
/*  top:50%;*/
/*  left:50%;*/
/*  !*顶和高同时设置50%实现的是同时水平垂直居中效果*!*/
/*  transform:translate(-50%,-50%);*/
/*  !*实现块元素百分比下居中*!*/
/*  width:450px;*/
/*  padding:50px;*/
/*  background: rgba(0,0,0,.5);*/
/*  !*背景颜色为黑色，透明度为0.8*!*/
/*  box-sizing:border-box;*/
/*  !*box-sizing设置盒子模型的解析模式为怪异盒模型，*/
/*  将border和padding划归到width范围内*!*/
/*  box-shadow: 0px 15px 25px rgba(0,0,0,.5);*/
/*  !*边框阴影  水平阴影0 垂直阴影15px 模糊25px 颜色黑色透明度0.5*!*/
/*  border-radius:15px;*/
/*  !*边框圆角，四个角均为15px*!*/
/*}*/
/*.loginPart h2{*/
/*  margin: 0 0 30px;*/
/*  padding: 0;*/
/*  color: #fff;*/
/*  text-align: center;*/
/*  !*文字居中*!*/
/*}*/
/*.form{*/
/*  margin-left: 30px;*/
/*}*/
</style>