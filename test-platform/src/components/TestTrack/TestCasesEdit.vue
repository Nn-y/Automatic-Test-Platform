<template>
    <el-tabs type="border-card" style="height:400px">
      <el-tab-pane label="信息">
        <el-form :inline="true" v-model="form2" label-width="120px">
          <el-form-item label="ID">
            <el-input v-model="form2.id" :disabled="true"/>
          </el-form-item>
          <el-form-item label="名称">
            <el-input v-model="form2.name" />
          </el-form-item>
          <el-form-item label="版本">
            <el-input v-model="form2.version" />
          </el-form-item>
          <el-form-item label="标签">
            <el-input v-model="form2.tag" />
          </el-form-item>
          <el-form-item label="所属模块">
            <el-input v-model="form2.module" />
          </el-form-item>
          <el-form-item label="缺陷">
          <el-input v-model="form2.bugs" />
        </el-form-item>
          <el-form-item label="执行人">
          <el-input v-model="form2.user" />
        </el-form-item>
          <el-form-item label="执行结果">
            <el-input v-model="form2.result" />
          </el-form-item>
          <el-form-item label="用例等级">
            <el-input v-model="form2.level" />
          </el-form-item>
          <el-form-item label="用例状态">
            <el-input v-model="form2.status" />
          </el-form-item>

        </el-form>

      </el-tab-pane>

      <el-tab-pane label="详情">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="前置条件">
            <el-input type="textarea" v-model="form"></el-input>
          </el-form-item>
        </el-form>

        <el-scrollbar>
          <el-table :data="gridData" @cell-dblclick="cellClick" stripe height="260px">
            <el-table-column type="index" label="序号" width="100" align="center"></el-table-column>
            <el-table-column property="steps" label="用例步骤" width="150"  align="center">
              <template v-slot:default="scope">
                <el-input size="mini" v-model="scope.row.steps" v-if="scope.row.flag1" @blur="inputClick(scope.row)" v-focus></el-input>
                <span v-else>{{scope.row.steps}}</span>
              </template>
            </el-table-column>
            <el-table-column property="expected" label="预期结果" width="200" align="center">
              <template v-slot:default="scope">
                <el-input size="mini" v-model="scope.row.expected" v-if="scope.row.flag2" @blur="inputClick(scope.row)" v-focus></el-input>
                <span v-else>{{scope.row.expected}}</span>
              </template>
            </el-table-column>
            <el-table-column property="actual" label="实际结果" align="center">
              <template v-slot:default="scope">
                <el-input size="mini" v-model="scope.row.actual" v-if="scope.row.flag3" @blur="inputClick(scope.row)" v-focus></el-input>
                <span v-else>{{scope.row.actual}}</span>
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

      </el-tab-pane>
    </el-tabs>

</template>

<script>
import {
  Delete,
  Plus
} from '@element-plus/icons-vue'
import axios from "axios";

let param;
export default {
  name: "TestCasesEdit",
  props:{
    msg:Number,
    type:Number
  },
  components:{
    Delete,
    Plus
  },
  data(){
    return{
      form:'',

      form2:'',

      dialogVisible: false,
      gridData:[],
    }
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
  created() {
    if(this.type === 0){
      this.funcLoad()
    }else {
      this.ifLoad()
    }
  },
  updated() {
    if(this.type === 0){
      this.funcLoad()
    }else {
      this.ifLoad()
    }
  },
  methods:{

    funcLoad(){
      axios.get("http://192.168.0.1:9090/functionInfo/one",{
        params:{
          id:this.msg
        }
      }).then(res =>{
        this.form2 = res.data
        param = this.form2.id
        // console.log(param)
        axios.get("http://192.168.0.1:9090/funcdetial",{
          params:{
            id:param
          }
        }).
        then(res =>{
          this.gridData = res.data
        })

        axios.get("http://192.168.0.1:9090/funcpre",{
          params:{
            id:param
          }
        }).
        then(res =>{
          this.form = JSON.parse(JSON.stringify(res.data))
          // console.log(JSON.parse(JSON.stringify(res.data)))
        })
      })
    },
    ifLoad(){
      axios.get("http://192.168.0.1:9090/interfaceInfo/one",{
        params:{
          id:this.msg
        }
      }).then(res =>{
        this.form2 = res.data
        param = this.form2.id
        // console.log(param)
        axios.get("http://192.168.0.1:9090/ifdetial",{
          params:{
            id:param
          }
        }).
        then(res =>{
          this.gridData = res.data
        })

        axios.get("http://192.168.0.1:9090/ifpre",{
          params:{
            id:param
          }
        }).
        then(res =>{
          this.form = JSON.parse(JSON.stringify(res.data))
          // console.log(JSON.parse(JSON.stringify(res.data)))
        })
      })
    },
    cellClick(row, column, cell, event){
      switch (column.label) {
        case '用例步骤':
          row.flag1=true
          break
        case '预期结果':
          row.flag2=true
          break
        case '实际结果':
          row.flag3=true
          break
        default: return
      }

    },
    //input框失去焦点事件
    inputClick(row){
      row.flag1=false
      row.flag2=false
      row.flag3=false
    },
    addRow(){
      if(this.type === 0){
        axios.get("http://192.168.0.1:9090/funcaddrow",{
          params:{
            id:param
          }
        }).
        then(res =>{
          this.gridData = res.data
          // console.log(param)
        })
      }else{
        axios.get("http://192.168.0.1:9090/ifaddrow",{
          params:{
            id:param
          }
        }).
        then(res =>{
          this.gridData = res.data
          // console.log(param)
        })
      }

    },
    deleteRow(index, rows) {
      if(this.type === 0){
        axios.post("http://192.168.0.1:9090/funcdeleterow",JSON.parse(JSON.stringify(rows[index]))).then(res =>{
          this.gridData = res.data
        })
      }else {
        axios.post("http://192.168.0.1:9090/ifdeleterow",JSON.parse(JSON.stringify(rows[index]))).then(res =>{
          this.gridData = res.data
        })
      }

      // console.log(JSON.parse(JSON.stringify(rows[index])))

    },
    cancelEvent(){

    }
  }
}
</script>

<style scoped>

</style>