<template>
  <el-menu
      router
      :default-active="$route.meta.name"
      class="el-menu-demo"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      style="height: 50px"
  >
    <el-menu-item index="testTrack" :route="{path:'/TestTrack'}">测试跟踪</el-menu-item>
<!--    <el-menu-item index="1" :route="{path:'/functionTest'}" >功能测试</el-menu-item>-->
    <el-menu-item index="interfaceTest" :route="{path:'/interfaceTest'}">接口测试</el-menu-item>
<!--    <el-menu-item index="3" :route="{path:'/performanceTest'}">性能测试</el-menu-item>-->
    <el-sub-menu index="4" style="position: absolute;right: 0; height: 100%">
      <template #title >{{user}}</template>
      <el-menu-item index="" @click="projectDialog">项目管理</el-menu-item>
      <el-menu-item index="4-2" :route="{path:'/'}" @click="clear">退出系统</el-menu-item>
    </el-sub-menu>
  </el-menu>
  <router-view :projectChange="flag"></router-view>
  <!--项目管理弹框-->
  <el-dialog v-model="dialogTableVisible" :close-on-click-modal="false" title="项目管理" width="400px">
    <el-scrollbar>
      <el-table :data="gridData" @cell-dblclick="cellClick" stripe height="260px">
        <el-table-column type="index" label="序号" width="100" align="center"></el-table-column>
        <el-table-column property="name" label="项目名称" width="150"  align="center">
          <template v-slot:default="scope">
            <el-input size="mini" v-model="scope.row.name" v-if="scope.row.flag" @blur="inputClick(scope.row)" v-focus></el-input>
            <span v-else>{{scope.row.name}}</span>
          </template>
        </el-table-column>
        <el-table-column property="action" label="操作" align="center">
          <template #header>
            <span>操作</span>
            <el-button round style="margin-left:8px" @click="addRow()">
              <el-icon style="vertical-align: middle;">
                <Plus/>
              </el-icon>
            </el-button>
          </template>
          <template v-slot:default="scope">
            <el-popconfirm title="确定要删除吗?"
                           confirm-button-text="Yes"
                           cancel-button-text="No"
                           :icon="InfoFilled"
                           icon-color="red"
                           @confirm="deleteRow(scope.$index, gridData)"
                           @cancel="cancelEvent">
              <template #reference>
                <el-button round>
                  <el-icon style="vertical-align: middle;">
                    <Delete/>
                  </el-icon>
                </el-button>
              </template>
            </el-popconfirm>

          </template>
        </el-table-column>
      </el-table>
    </el-scrollbar>
    <template #footer>
            <span class="dialog-footer">
              <el-button type="success" round  @click="save">保存</el-button>
            </span>
    </template>
  </el-dialog>
</template>

<script>
import {Plus,ArrowDown,Delete,InfoFilled} from '@element-plus/icons'
import axios from "axios";

export default {
  components: {
    Plus,ArrowDown,Delete,InfoFilled
  },
  name: "Head",
  data(){
    return{
      user:'',
      dialogTableVisible:false,
      gridData:[],
      flag:0
    }
  },
  mounted() {
    this.user = this.$store.state.user
    axios.get("http://192.168.0.1:9090/getprojects",{
      params:{
        user:this.$store.state.user
      }
    }).then(res=>{
      this.gridData = res.data
    })
  },
  directives: {
    // 注册一个局部的自定义指令 v-focus
    focus: {
      // 指令的定义
      inserted: function (el) {
        // 聚焦元素
        el.querySelector('input').focus();
      }
    }
  },
  methods:{
    clear(){
      this.$store.dispatch("asynShow",'')
      this.$store.dispatch("asynChange",0)
      sessionStorage.setItem('token','')
    },
    projectDialog(){
      this.dialogTableVisible = true
    },
    cellClick(row, column, cell, event){
      switch (column.label) {
        case '项目名称':
          row.flag=true
          break
        default: return
      }

    },
    //input框失去焦点事件
    inputClick(row){
      row.flag=false
    },
    cancelEvent(){

    },
    addRow(){
      axios.get("http://192.168.0.1:9090/addproject",{
        params:{
          user:this.$store.state.user
        }
      }).then(res=>{
        this.gridData = res.data
        this.flag++
      })
    },
    deleteRow(index,rows){
      axios.get("http://192.168.0.1:9090/delproject",{
        params:{
          id:rows[index].id,
          user:this.$store.state.user
        }
      }).then(res=>{
        this.gridData = res.data
        this.flag++
      })
      // console.log(rows[index].id)
    },
    save(){
      this.dialogTableVisible = false
      axios.post("http://192.168.0.1:9090/updateproject",JSON.parse(JSON.stringify(this.gridData))).then(res=>{
        this.flag++
      })
    }
  }

}
</script>

<style scoped>

</style>