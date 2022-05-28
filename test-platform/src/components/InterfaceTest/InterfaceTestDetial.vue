<template>
  <el-scrollbar>
    <el-card>
      <div style="margin-bottom: 20px">
        <div style="border-left: #545c64 solid 4px;">基础信息</div>
        <el-form :inline="true" :model="form" label-width="120px">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="名称">
                <el-input v-model="form.name" />
              </el-form-item>
            </el-col>
            <el-col :span="16">
              <el-form-item label="请求">

                <el-input v-model="form.domain" style="width: 300px"  placeholder="server name">
                  <template #prepend>
                    <el-select v-model="form.request" placeholder="Select" style="width: 110px">
                      <el-option label="get" value="GET" />
                      <el-option label="post" value="POST" />
<!--                      <el-option label="put" value="PUT" />-->
<!--                      <el-option label="delete" value="DELETE" />-->
<!--                      <el-option label="head" value="HEAD" />-->
<!--                      <el-option label="patch" value="PATCH" />-->
<!--                      <el-option label="options" value="OPTIONS" />-->
<!--                      <el-option label="connect" value="CONNECT" />-->
                    </el-select>

                  </template>
                </el-input>
                <el-input style="width: 100px" placeholder="port"  v-model="form.port">

                </el-input>
                <el-input style="width: 200px" placeholder="path" v-model="form.path">

                </el-input>

              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="责任人">
                <el-input v-model="form.user" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="模块">
                <el-input v-model="form.module" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="状态">
                <el-input v-model="form.status" />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="标签">
                <el-input v-model="form.tag" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="描述">
                <el-input
                    type="textarea"
                    autosize
                    v-model="form.describes">
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-button type="success" round  @click="save" style="float: right;margin-right: 40px">保存</el-button>

      </div>

      <div style="margin-bottom: 20px">
        <div style="border-left: #545c64 solid 4px">请求内容</div>
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="请求参数" name="first">
            <el-table :data="paramsData" @cell-dblclick="cellClick" stripe height="260px">
              <el-table-column type="index" label="序号" width="100" align="center"></el-table-column>
              <el-table-column property="key" label="键" width="150"  align="center">
                <template v-slot:default="scope">
                  <el-input size="mini" v-model="scope.row.key" v-if="scope.row.flag1" @blur="inputClick(scope.row)" v-focus></el-input>
                  <span v-else>{{scope.row.key}}</span>
                </template>
              </el-table-column>
              <el-table-column property="value" label="值" width="200" align="center">
                <template v-slot:default="scope">
                  <el-input size="mini" v-model="scope.row.value" v-if="scope.row.flag2" @blur="inputClick(scope.row)" v-focus></el-input>
                  <span v-else>{{scope.row.value}}</span>
                </template>
              </el-table-column>
              <el-table-column property="desc" label="描述" align="center">
                <template v-slot:default="scope">
                  <el-input size="mini" v-model="scope.row.desc" v-if="scope.row.flag3" @blur="inputClick(scope.row)" v-focus></el-input>
                  <span v-else>{{scope.row.desc}}</span>
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
                      <el-button round @click="deleteRow(scope.$index,paramsData)">
                        <el-icon style="vertical-align: middle;">
                          <Delete/>
                        </el-icon>
                      </el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
<!--          <el-tab-pane label="请求头" name="second">-->
<!--            <el-row :gutter="20">-->
<!--              <el-col :span="6">-->
<!--                <el-input v-model="input" placeholder="键" />-->
<!--              </el-col>-->
<!--              <el-col :span="6">-->
<!--                <el-input v-model="input" placeholder="值" />-->
<!--              </el-col>-->
<!--            </el-row>-->
<!--          </el-tab-pane>-->
<!--          <el-tab-pane label="请求体" name="third">-->
<!--            <el-radio-group v-model="radio">-->
<!--              <el-radio :label="1">form-data</el-radio>-->
<!--              <el-radio :label="2">x-www-form-urlencoded</el-radio>-->
<!--              <el-radio :label="3">json</el-radio>-->
<!--              <el-radio :label="4">xml</el-radio>-->
<!--              <el-radio :label="5">raw</el-radio>-->
<!--              <el-radio :label="6">binary</el-radio>-->
<!--            </el-radio-group>-->
<!--            <div v-if="radio===1 || radio===2">-->
<!--              <el-row :gutter="20">-->
<!--                <el-col :span="6">-->
<!--                  <el-input v-model="input" placeholder="键" />-->
<!--                </el-col>-->
<!--                <el-col :span="6">-->
<!--                  <el-input v-model="input" placeholder="值" />-->
<!--                </el-col>-->
<!--              </el-row>-->
<!--              <el-row :gutter="20">-->
<!--                <el-col :span="6">-->
<!--                  <el-input v-model="input" placeholder="键" />-->
<!--                </el-col>-->
<!--                <el-col :span="6">-->
<!--                  <el-input v-model="input" placeholder="值" />-->
<!--                </el-col>-->
<!--              </el-row>-->
<!--            </div>-->

<!--            <div v-if="radio===3">-->
<!--              <code-editor ref="_firstRefs" class="editor h-100" v-model="editorContent"-->
<!--                           readonly language="json" theme="dracula"></code-editor>-->
<!--            </div>-->

<!--            <div v-if="radio===4">-->
<!--              <code-editor ref="_firstRefs" class="editor h-100" v-model="editorContent"-->
<!--                           readonly language="xml" theme="dracula"></code-editor>-->
<!--            </div>-->

<!--            <div v-if="radio===5">-->
<!--              <code-editor ref="_firstRefs" class="editor h-100" v-model="editorContent"-->
<!--                           readonly language="raw" theme="dracula"></code-editor>-->
<!--            </div>-->

<!--            <div v-if="radio===6">-->
<!--              <el-row :gutter="20">-->
<!--                <el-col :span="6">-->
<!--                  <el-input-->
<!--                      type="text"-->
<!--                      placeholder="描述"-->
<!--                      v-model="desctext"-->
<!--                      maxlength="200"-->
<!--                      show-word-limit-->
<!--                  ></el-input>-->
<!--                </el-col>-->
<!--                <el-col :span="6">-->
<!--                  <el-upload-->
<!--                  >-->
<!--                    <el-button type="primary">上传文件</el-button>-->

<!--                  </el-upload>-->
<!--                </el-col>-->
<!--              </el-row>-->
<!--            </div>-->
<!--          </el-tab-pane>-->

<!--          <el-tab-pane label="前置脚本" name="fourth">-->
<!--            <ace></ace>-->
<!--          </el-tab-pane>-->
        </el-tabs>
        <el-button type="success" round  @click="execute" style="float: right;margin-right: 40px">执行</el-button>
      </div>

      <div>
        <div style="border-left: #545c64 solid 4px;margin-bottom: 10px">响应内容</div>
        <el-row :gutter="10">
          <el-col :span="8">
            <span>响应码:{{code}}</span>
          </el-col>
          <el-col :span="8">
            <span>响应时间:{{time}}</span>
          </el-col>
          <el-col :span="8">
            <span>响应大小:{{size}}</span>
          </el-col>
        </el-row>
<!--        <el-descriptions>-->
<!--          <el-descriptions-item label="响应码:" v-model="code"></el-descriptions-item>-->
<!--          <el-descriptions-item label="响应时间:" v-model="time"></el-descriptions-item>-->
<!--          <el-descriptions-item label="响应大小:" v-model="size"></el-descriptions-item>-->
<!--        </el-descriptions>-->
        <el-tabs v-model="activeName2" @tab-click="handleClick">
          <el-tab-pane label="响应体" name="responseData" >
            <div style="height: 300px;" >
              <ace :value="responsedata" :key="refresh"></ace>
            </div>
          </el-tab-pane>
          <el-tab-pane label="响应头" name="responseHeader">
            <div style="height: 300px;" >
<!--              <code-editor ref="_firstRefs" class="editor h-100" v-model="responseheader"-->
<!--                           readonly language="json" theme="dracula"></code-editor>-->
              <ace :value="responseheader" :key="refresh"></ace>
              <!-- 只要对editScriptType赋值就可以使用对应语言的编辑器了 -->


            </div>
          </el-tab-pane>
<!--          <el-tab-pane label="控制台" name="third">控制台</el-tab-pane>-->
<!--          <el-tab-pane label="断言" name="fourth">断言</el-tab-pane>-->
<!--          <el-tab-pane label="提取" name="fifth">提取</el-tab-pane>-->
<!--          <el-tab-pane label="请求内容" name="sixth">请求内容</el-tab-pane>-->
        </el-tabs>
      </div>

    </el-card>
  </el-scrollbar>
</template>

<script>
import CodeEditor from '@/components/CodeEditor'
import {
  Delete,
  Plus
} from '@element-plus/icons-vue'
import axios from "axios";
export default {
  name: "InterfaceTestDetial",
  components:{
    CodeEditor,
    Delete,
    Plus
  },
  props:{
    msg:Number
  },
  created() {
    axios.get("/api/iflistdetial",{
      params:{
        id:this.msg
      }
    }).then(res=>{
        this.form = res.data
    }),
        axios.get("/api/getparams",{
          params:{
            id:this.msg
          }
        }).then(res=>{
          this.paramsData = res.data
        })
  },
  watch:{
    responseheader:function (nv,ov){
      this.refresh= !this.refresh
    },
    responsedata:function (nv,ov){
      this.refresh= !this.refresh
    }
  },
  data(){
    return{
      editorContent:'',
      scriptContent:'',
      input:'',
      desctext:'',
      form:'',
      refresh:true,
      activeName: 'first',
      activeName2: 'responseData',
      responsedata:'',
      responseheader:'',
      paramsData: [],
      radio: 1,
      code:'',
      time:'',
      size:''
    }
  },
  directives: {
    // 注册一个局部的自定义指令 v-focus
    focus: {
      // 指令的定义
      inserted: function (el) {
        // 聚焦元素
        el.querySelector('input').focus()
      }
    }
  },
  methods: {
    save(){
      let param = JSON.parse(JSON.stringify(this.form))
      axios.post("/api/iflistdetial/save",param,
          {headers:{
              'Content-Type': 'application/json;charset=utf-8',
              'Accept': 'application/json',
            },withCredentials: true, },)

      this.$message({
        message: '保存成功！',
        type: 'success'
      });
    },
    execute(){
      axios.get("/api/getrequest",{
        params:{
          id:this.msg
        }
      }).then(res=>{
        axios.get("/api/getresponse", {
          params: {
            content: 'responseHeader'
          }
        }).then(res=>{
          // console.log(JSON.parse(JSON.stringify(res.data)))
          this.responseheader = res.data[0]
        }),
        axios.get("/api/getresponse", {
          params: {
            content: 'responseData'
          }
        }).then(res=>{
          // console.log(JSON.parse(JSON.stringify(res.data)))
          this.responsedata = res.data[0]
        }),
        axios.get("/api/getresponse/getbrief").then(res=>{
          console.log(res.data[0])
          this.code = res.data[0]
          this.time = res.data[1]+' ms'
          this.size = res.data[2]+' bytes'
        })
      })



    },
    handleClick(tab, event) {

      // axios.get("http://192.168.0.1:9090/getresponse",{
      //   params:{
      //     content:'responseHeader'
      //   }.then(res=>{
      //     console.log(res.data)
      //     // this.responseheader = res.data
      //   })
      // })
      // console.log(tab, event);
    },
    cellClick(row, column, cell, event){
      switch (column.label) {
        case '键':
          row.flag1=true
          break
        case '值':
          row.flag2=true
          break
        case '描述':
          row.flag3=true
        default: return
      }

    },
    //input框失去焦点事件
    inputClick(row){
      row.flag1=false
      row.flag2=false
      row.flag3=false
      axios.post("/api/updateparams",JSON.parse(JSON.stringify(row)),
          {headers:{
              'Content-Type': 'application/json;charset=utf-8',
              'Accept': 'application/json',
            },withCredentials: true, },)
      // console.log(JSON.parse(JSON.stringify(row)))
    },
    addRow(){
      axios.get("/api/addparams",{
        params:{
          id:this.msg
        }
      }).then(res=>{
        this.paramsData = res.data
      })
    },
    deleteRow(index,rows){
      axios.post("/api/deleteparams",JSON.parse(JSON.stringify(rows[index])),
          {headers:{
              'Content-Type': 'application/json;charset=utf-8',
              'Accept': 'application/json',
            },withCredentials: true, },).then(res=>{
        this.paramsData = res.data
      })
    }
  }
}
</script>

<style scoped>

</style>