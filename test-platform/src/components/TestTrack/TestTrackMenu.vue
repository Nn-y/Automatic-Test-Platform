<template>
  <el-menu
      router
      :default-active="$route.meta.name"
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
            :value="item.name">
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

    <el-menu-item index="testTrack" :route="{path:'/TestTrack/function'}">功能测试用例</el-menu-item>
    <el-menu-item index="2" :route="{path:'/TestTrack/interface'}">接口测试用例</el-menu-item>
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
  created() {

  },
  mounted() {
    axios.get("http://192.168.0.1:9090/getprojects").then(res =>{
      this.options = res.data
      // console.log(res.data)
      if(this.$store.state.project === ''){
        this.project = this.options[0].name
        this.$store.dispatch("asynChange",this.project)
      }else{
        this.project = this.$store.state.project
      }
      this.projectName = this.project

    })
  },
  data(){
    return{
      options: [],
      project: '',
      projectName:'',
    }
  },
  methods:{
    getProject(){
      this.projectName = this.project
      this.$store.dispatch("asynChange",this.projectName)
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