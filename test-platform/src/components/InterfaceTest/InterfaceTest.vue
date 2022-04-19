<template>

    <el-menu
        router
        mode="horizontal"
        style="height: 40px"
    >
      <el-sub-menu>
        <template #title>项目</template>
        <el-menu-item >项目一</el-menu-item>
        <el-menu-item>项目二</el-menu-item>
        <el-menu-item >项目三</el-menu-item>
      </el-sub-menu>
  </el-menu>

  <el-card class="box-card">
  <el-scrollbar>
  <div>
  <div style="border-left: #545c64 solid 4px">基础信息</div>
  <el-form :inline="true" :model="form" label-width="120px">
    <el-row :gutter="20">
      <el-col :span="8">
        <el-form-item label="名称">
          <el-input v-model="form.name" />
        </el-form-item>
      </el-col>
      <el-col :span="16">
        <el-form-item label="请求">
          <el-row>
            <el-col :span="8">
              <el-select v-model="form.request">
                <el-option label="get" value="GET" />
                <el-option label="post" value="POST" />
                <el-option label="put" value="PUT" />
                <el-option label="delete" value="DELETE" />
                <el-option label="head" value="HEAD" />
                <el-option label="patch" value="PATCH" />
                <el-option label="options" value="OPTIONS" />
                <el-option label="connect" value="CONNECT" />
              </el-select>
            </el-col>
            <el-col :span="16">
              <el-input v-model="form.url" />
            </el-col>
          </el-row>
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
              v-model="form.desc">
          </el-input>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
  </div>

  <div>
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
      </el-table>
    </el-tab-pane>
    <el-tab-pane label="请求头" name="second">请求头</el-tab-pane>
    <el-tab-pane label="请求体" name="third">
      <el-radio-group v-model="radio">
        <el-radio :label="1">form-data</el-radio>
        <el-radio :label="2">x-www-form-urlencoded</el-radio>
        <el-radio :label="3">json</el-radio>
        <el-radio :label="4">xml</el-radio>
        <el-radio :label="5">raw</el-radio>
        <el-radio :label="6">binary</el-radio>
      </el-radio-group>

      <div>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="键">
              <el-input v-model="form.user" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="值">
              <el-input v-model="form.module" />
            </el-form-item>
          </el-col>
        </el-row>
      </div>
    </el-tab-pane>
    <el-tab-pane label="前置脚本" name="fourth">前置脚本</el-tab-pane>
  </el-tabs>
  </div>

  <div>
  <div style="border-left: #545c64 solid 4px">响应内容</div>
  <el-row :gutter="10">
    <el-col :span="8">
      <span>响应码</span>
    </el-col>
    <el-col :span="8">
      <span>响应时间</span>
    </el-col>
    <el-col :span="8">
     <span>响应大小</span>
    </el-col>
  </el-row>
    <el-tabs v-model="activeName2" @tab-click="handleClick">
      <el-tab-pane label="响应体" name="first">响应体</el-tab-pane>
      <el-tab-pane label="响应头" name="second">响应头</el-tab-pane>
      <el-tab-pane label="控制台" name="third">控制台</el-tab-pane>
      <el-tab-pane label="断言" name="fourth">断言</el-tab-pane>
      <el-tab-pane label="提取" name="fifth">提取</el-tab-pane>
      <el-tab-pane label="请求内容" name="sixth">请求内容</el-tab-pane>
    </el-tabs>
  </div>
  </el-scrollbar>
  </el-card>
</template>

<script>
export default {
  name: "InterfaceTest",
  data(){
    return{
      form:{
        name: '',
        request:'',
        url:'',
        tag:'',
        module:'',
        user:'',
        status:'',
        desc:''
      },
      activeName: 'first',
      activeName2: 'first',
      paramsData: [{
        key: '步骤一',
        flag1 : false,
        flag2 : false,
        flag3 : false,
        value: '预期',
        desc: '结果一'
      }, {
        key: '步骤二',
        flag1 : false,
        flag2 : false,
        flag3 : false,
        value: '预期',
        desc: '结果二'
      }, {
        key: '步骤三',
        flag1 : false,
        flag2 : false,
        flag3 : false,
        value: '预期',
        desc: '结果三'
      }, {
        key: '步骤四',
        flag1 : false,
        flag2 : false,
        flag3 : false,
        value: '预期',
        desc: '结果四'
      }],
      radio: 1
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
    handleClick(tab, event) {
      console.log(tab, event);
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
    },
  }
}
</script>

<style scoped>

</style>