<template>
  <el-menu
      router
      :default-active="defaultActive"
      class="el-menu-demo"
      mode="horizontal"
      style="height: 40px"
  >


      <el-select v-model="project"  class="inputDeep"
                 style="width: 100px;margin-left: 20px;margin-top: 2px;"
                  @change="getProject">
        <el-option
            v-for="item in options"
            :key="item.name"
            :label="item.name"
            :value="item.id">
        </el-option>
<!--        <el-option style="border-top: #ebeef5 solid 1px">-->
<!--          <el-icon><plus /></el-icon>添加项目-->
<!--        </el-option>-->
      </el-select>
<!--    <el-sub-menu>-->
<!--      <template #title>-->

<!--      </template>-->
<!--      <el-menu-item >项目一</el-menu-item>-->
<!--      <el-menu-item>项目二</el-menu-item>-->
<!--      <el-menu-item >项目三</el-menu-item>-->
<!--      <el-menu-item divided><el-icon><plus /></el-icon>添加项目</el-menu-item>-->
<!--    </el-sub-menu>-->

    <el-menu-item index="function" :route="{path:'/TestTrack/function'}">功能测试用例</el-menu-item>
    <el-menu-item index="interface" :route="{path:'/TestTrack/interface'}">接口测试用例</el-menu-item>
<!--    <el-menu-item index="3" :route="{path:'/TestTrack/performance'}">性能测试用例</el-menu-item>-->
  </el-menu>
  <router-view :project="projectName"></router-view>
</template>

<script>
import {Plus,ArrowDown} from '@element-plus/icons'
import axios from "axios";
import { useStore } from 'vuex'

export default {
  components: {
    Plus,ArrowDown,
  },
  name: "TestTrackMenu",
  props:{
    projectChange:Number,
  },
  created() {

  },
  mounted() {
    this.load()
  },
  data(){
    return{
      options: [],
      project: '',
      projectName:0,
      projectId:0,
      defaultActive:'function'
    }
  },
  watch: {
    $route: {
      immediate: true,
      handler(to) {
        if (to.fullPath.endsWith('function')){
          this.defaultActive = 'function' // 给defaultActive重新赋值为当前组件的路由地址
        }else {
          this.defaultActive = 'interface'
        }
      }
    },
    projectChange:function (nv,ov){
      this.load()
    }
  },

  methods:{
    load(){
      axios.get("http://192.168.0.1:9090/getprojects",{
        params:{
          user:this.$store.state.user
        }
      }).then(res =>{
        this.options = []
        this.options = res.data
        if(this.$store.state.project === 0){
          console.log(this.options[0].id)
          this.project = this.options[0].name
          this.projectId = this.options[0].id
          this.$store.dispatch("asynChange",this.projectId)
        }else{
          this.projectId = this.$store.state.project
          axios.get("http://192.168.0.1:9090/getproject",{
            params:{
              projectId:this.projectId
            }
          }).then(res=>{
            this.project = res.data
          })
        }
        this.projectName = this.projectId

      })
    },
    getProject(value){
      this.projectId = value
      this.projectName = this.projectId
      // console.log(this.projectName)
      this.$store.dispatch("asynChange",this.projectId)
    }
  }
}
</script>

<style lang="less" scoped>

.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}

.inputDeep  /deep/ .el-input__inner,/deep/ .el-select:hover:not(.el-select--disabled) .el-input__inner ,
/deep/ .el-select .el-input.is-focus .el-input__inner,/deep/ .el-select .el-input__inner:focus
{
  -webkit-appearance: none;
  background-color: #FFF;
  background-image: none;
  border-radius: 4px;
  border: 0px;//改成0，边框就消失了！
  box-shadow: none !important;
  width: 100%;
  //font-size:14px;
}

</style>