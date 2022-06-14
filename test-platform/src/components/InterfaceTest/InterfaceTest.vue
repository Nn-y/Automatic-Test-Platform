<template>
  <el-menu
      router
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
    </el-select>
  </el-menu>

    <el-container style="height: calc(100vh - 90px); width: 100%" class="layout-container-demo">
      <el-aside width="240px">
        <el-scrollbar style="height: 100%">
          <div class="down-tree">
            <el-tree
                :data="data"
                :props="defaultProps"
                ref="tree"
                node-key="id"
                default-expand-all
                :expand-on-click-node="false"
                style="padding-top: 10px;overflow:auto;"
                :current-node-key="currentNode"
                highlight-current
                @node-click="nodeClick"
            >
              <template #default="{ node, data }">
            <span class="custom-tree-node" >
              <span
                  v-if="!data.isEdit"
                  @dblclick="() => {
                    if(data.label !== '全部接口'){
                      data.isEdit = true;
                    }}"
              >{{ node.label }}</span>
                 <el-input
                     size="mini"
                     ref="inputVal"
                     v-if="data.isEdit"
                     v-model.trim="data.label"
                     @focus="focus($event, node)"
                     @input="(a) => inp(a, data)"
                     @blur="alters(node, data, $event)"
                     v-focus
                 >
          </el-input>
            </span>

                <span>
                  <el-icon @click="() => append(data)" color="#409EFC" class="no-inherit"><circle-plus /></el-icon>
                   <el-popconfirm title="确定要删除吗?"
                                  confirm-button-text="Yes"
                                  cancel-button-text="No"
                                  :icon="InfoFilled"
                                  icon-color="red"
                                  @confirm="remove(node, data)"
                                  @cancel="cancelEvent">
                    <template #reference>
                      <el-icon v-if="node.label !== '全部接口'"
                               color="#409EFC"
                               class="no-inherit">
                    <delete />
                  </el-icon>
                    </template>
                  </el-popconfirm>

              </span>
              </template>
            </el-tree>
          </div>
        </el-scrollbar>
      </el-aside>

      <el-main height="calc(100vh - 500px)" style="width: 100% ;border-radius: 20px;box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);margin: 10px">
        <el-tabs
            v-model="editableTabsValue"
            type="card"
            class="demo-tabs"
            @tab-remove="removeTab"
        >
          <el-tab-pane label="接口列表" :name="0">
            <el-scrollbar>
              <el-table :data="tableData" stripe height="calc(100vh - 170px)">
                <el-table-column prop="id" label="ID" width="120" />
                <el-table-column prop="name" label="接口名称" width="120" />
                <el-table-column prop="type" label="请求类型" width="120" />
                <el-table-column prop="user" label="责任人" width="120"/>
                <el-table-column prop="tag" label="标签" width="120"/>
<!--                <el-table-column prop="version" label="版本" width="120"/>-->
                <el-table-column prop="utime" label="更新时间" width="180"/>
                <el-table-column prop="ctime" label="创建时间" width="180"/>
                <el-table-column prop="status" label="接口状态" width="120"/>
                <el-table-column fixed="right" label="操作" width="180" :render-header="renderHeader">
                  <template v-slot:default="scope">
                    <el-button type="primary" circle  @click="addTab(scope.$index)">
                      <el-icon style="vertical-align: middle;">
                        <Edit/>
                      </el-icon>
                    </el-button>
                    <el-popconfirm title="确定要删除吗?"
                                   confirm-button-text="Yes"
                                   cancel-button-text="No"
                                   :icon="InfoFilled"
                                   icon-color="red"
                                   @confirm="deleteRow(scope.$index, tableData)"
                                   @cancel="cancelEvent">
                      <template #reference>
                        <el-button type="danger" circle>
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
          <el-tab-pane
              v-for="item in editableTabs"
              :key="item.id"
              :label="item.title"
              :name="item.id"
              closable
          >
            <component :is="item.content" :ref="item.content" :msg="item.id" height="calc(100vh - 170px)" />
          </el-tab-pane>
        </el-tabs>

      </el-main>
    </el-container>


</template>

<script>
import {
  Delete,
  Edit,
  InfoFilled,
  CirclePlus,
} from '@element-plus/icons-vue'
import InterfaceTestDetial from "@/components/InterfaceTest/InterfaceTestDetial";
import axios from "axios";
let id = 1000;
let nodeId;
function tree(data, arr) {
  arr.forEach(ele => {
    if (ele.id === data.pid) {
      ele.children.push({
        id: data.id,
        label: data.label,
        children: []
      })
    } else {
      tree(data, ele.children)
    }
  })
}
export default {
  name: "InterfaceTest",
  components:{
    Delete,
    Edit,
    InfoFilled,
    InterfaceTestDetial,
    CirclePlus,
  },
  props:{
    projectChange:Number
  },
  created(){
    this.load()
  },
  watch:{
    projectChange:function (nv,ov){
      this.load()
    },
    editableTabsValue:function (nv,ov){
      if(nv === "0"){
        this.listLoad()
      }
    },
  },

  data(){

    return{
      options: [],
      project: '',
      projectId:0,
      // projectName:'',
      data: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      currentNode: '',
      tableData: [],
      editableTabsValue: "0",
      editableTabs: [],
      tabIndex: 3

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
  methods: {
    load(){
      axios.get("/api/getprojects",{
        params:{
          user:this.$store.state.user
        }
      }).then(res =>{
        this.options = []
        this.options = res.data
        // console.log(res.data)
        if(this.options.length === 0) {
          this.projectId = 0
          this.$store.dispatch("asynChange", this.projectId)
          this.project = ''
        }else if(this.$store.state.project === 0){//默认显示第一个项目,并修改projectId
          // console.log(this.options[0])
          this.project = this.options[0].name
          this.projectId = this.options[0].id
          this.$store.dispatch("asynChange",this.projectId)
          this.treeLoad()
        }else {
          this.projectId = this.$store.state.project
          axios.get("/api/getproject",{
            params:{
              projectId:this.projectId
            }
          }).then(res=>{
            this.project = res.data
          })
        }
        this.treeLoad()
      })
    },
    treeLoad(){
      if(this.$store.state.project === 0){
        this.data = []
      }else {
        axios.get("/api/iftesttree",{
          params:{
            projectId:this.$store.state.project
          }
        }).then(res =>{
          nodeId = res.data[0].id
          this.currentNode = nodeId
          this.$nextTick(function () {
            this.$nextTick(() => {
              this.$refs.tree.setCurrentKey(this.currentNode);
            });
          });
          this.data = []
          this.tree_init(res.data,this.data)
          this.nodeLoad()
        })
      }

    },
    getProject(value){
      // this.projectName = this.project
      this.projectId = value
      this.$store.dispatch("asynChange",this.projectId)
      axios.get("/api/iftesttree",{
        params:{
          projectId:this.projectId
        }
      }).then(res =>{
        nodeId = res.data[0].id
        this.currentNode = nodeId
        this.$nextTick(function () {
          this.$nextTick(() => {
            this.$refs.tree.setCurrentKey(this.currentNode);
          });
        });
        this.data = []
        this.tree_init(res.data,this.data)
        // console.log(res.data[0].id)
        this.nodeLoad()
        this.editableTabs = []
      })
    },
    tree_init(data,arr) {
      data.forEach(ele => {
        if (!ele.pid) {
          arr.push({
            id: ele.id,
            label: ele.label,
            children: []
          })
        } else {
          tree(ele, arr)
        }
      })
      // console.log(arr)
    },
    nodeClick(data){
      // console.log(data.id)
      nodeId = data.id
      this.nodeLoad()
      // this.editableTabsValue = "0"

    },
    nodeLoad(){

      axios.get("/api/iftesttree/nodeclick",{
        params:{
          id:nodeId
        }
      }).
      then(res =>{
        this.tableData = res.data
        // console.log(this.tableData)
      })
      this.editableTabsValue = "0"
    },
    listLoad(){
      axios.get("/api/iftesttree/nodeclick",{
        params:{
          id:nodeId
        }
      }).
      then(res =>{
        this.tableData = res.data
        // console.log(this.tableData)
      })
    },
    focus(event, node) {
      // 获取焦点
      this.whetherEditNodeName = false;
      event.target.value = node.label;
      event.currentTarget.select();
    },
    inp(value, data) {
      console.log(data);
      // 修改
      this.whetherEditNodeName = true;
      // 这里得加判断，如果输入空，就显示之前的值
      if (value == "") {
        data.label = data.data.label;
      } else {
        data.label = value;
      }
    },
    alters(node, data, event) {
      // data里有节点名称，后端需要就传过去
      if (this.whetherEditNodeName) {
        // 调用后端修改nodeName的接口
        // 这里加判断是为了避免，双击后没修改nodeName也会调接口的情况
        axios.post("/api/iftesttree/update",JSON.parse(JSON.stringify(data)),
            {headers:{
                'Content-Type': 'application/json;charset=utf-8',
                'Accept': 'application/json',
              },withCredentials: true, },)
      }
      data.isEdit = !data.isEdit;
    },

    append(data) {
      axios.get("/api/iftesttree/add",{
        params:{
          id:data.id,
          projectId:this.$store.state.project
        }
      }).
      then(res =>{
        this.data = []
        this.tree_init(res.data,this.data)
      })
    },
//删除分类栏节点
    remove(node, data) {
      axios.get("/api/iftesttree/del",{
        params:{
          id:data.id,
          // projectId:this.$store.state.project
        }
      }).
      then(res =>{
        nodeId = res.data //删除子节点后高亮父节点
        axios.get("/api/iftesttree",{
          params:{
            projectId:this.$store.state.project
          }
        }).then(res =>{
          this.data = []
          this.tree_init(res.data,this.data)
          this.currentNode = nodeId
          this.$nextTick(function () {
            this.$nextTick(() => {
              this.$refs.tree.setCurrentKey(this.currentNode);
            });
          });
          this.nodeLoad()
        })
      })
    },
    handleNodeClick(data) {
      console.log(data);
    },

    cancelEvent(){

    },
    renderHeader (h) {
      return (
          <div>
            <span>操作</span>
            <el-button
                type="primary"
                plain
                style="padding:3px;margin-left:8px"
                size="mini"
                //加入点击事件---------
                onClick={
                  this.tableAddrow
                }
                //---------------------
            >添加</el-button></div>
      )
    },
    tableAddrow(){
      if(this.$store.state.project === 0){
        alert("请先添加项目！")
      }else {
        axios.get("/api/ifListAddDefault", {
          params: {
            nodeId: nodeId
          }
        }).then(res => {
          this.tableData = res.data
        })
      }
    },
    deleteRow(index, rows) {
      axios.get("/api/ifListDelete", {
        params:{
          id:rows[index].id,
          nodeId:nodeId
        }
      }).then(res=>{
        this.tableData = res.data
      })
    },

    addTab(row) {
      // let newTabName = ++this.tabIndex + '';
      this.editableTabs.push({
        title: this.tableData[row].name,
        id: this.tableData[row].id,
        content: 'InterfaceTestDetial'
      });
      this.editableTabsValue = this.tableData[row].id;

    },
    removeTab(targetName) {
      let tabs = this.editableTabs;
      let activeName = this.editableTabsValue;
      if (activeName === targetName) {
        tabs.forEach((tab, index) => {
          if (tab.id === targetName) {
            let nextTab = tabs[index + 1] || tabs[index - 1];
            if (nextTab) {
              activeName = nextTab.id;
            }else {
              activeName = "0";
            }
          }
        });
      }

      this.editableTabsValue = activeName;
      this.editableTabs = tabs.filter(tab => tab.id !== targetName);

    }
  }
}
</script>

<style lang="less" scoped>
.layout-container-demo .el-header {
  position: relative;
  background-color: #545c64;
  color: var(--el-text-color-primary);
}
.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
  background: #545c64;
}
.layout-container-demo .el-menu {
  border-right: none;
}
.layout-container-demo .el-main {
  padding: 0;
}
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
.down-tree /deep/ .el-tree-node.is-expanded > .el-tree-node__children {
  display: inline;
  min-width: 100%;
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