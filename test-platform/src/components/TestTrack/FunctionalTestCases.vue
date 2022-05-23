<template>
  <el-container class="layout-container-demo" style="height: calc(100vh - 90px); width: 100%">
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
                  @dblclick="() => {data.isEdit = true;}"
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
                      <el-icon v-if="node.label !== '全部用例'"
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

    <el-container>
      <el-main style="width: 100% ;border-radius: 20px;box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);margin: 10px">

        <el-scrollbar>
          <el-table :data="tableData" stripe height="calc(100vh - 110px)"  >
            <el-table-column prop="id" label="ID" width="120" />
            <el-table-column prop="name" label="名称" width="120" />
            <el-table-column prop="version" label="版本" width="120"/>
            <el-table-column prop="tag" label="标签" width="120"/>
            <el-table-column prop="module" label="所属模块" width="120" />
            <el-table-column prop="bugs" label="缺陷" width="80"/>
            <el-table-column prop="user" label="执行人" width="100"/>
            <el-table-column prop="result" label="执行结果" width="100"/>
            <el-table-column prop="utime" label="更新时间" width="180"/>
            <el-table-column prop="ctime" label="创建时间" width="180"/>
            <el-table-column prop="level" label="用例等级" width="120"/>
            <el-table-column prop="status" label="用例状态" width="120"/>
            <el-table-column fixed="right" label="操作" width="120" :render-header="renderHeader">
              <template v-slot:default="scope">
                <el-button type="primary" circle  @click="editCase(scope.$index)">
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

      </el-main>
      <el-dialog v-model="dialogTableVisible" :close-on-click-modal="false" width="70%" class="el-dialog">
          <TestCasesEdit :msg="indexNum" :type="func" ref="dialog"></TestCasesEdit>

          <template #footer>
            <span class="dialog-footer">
              <el-button type="success" round  @click="save">保存</el-button>
            </span>
          </template>

      </el-dialog>
    </el-container>

  </el-container>
</template>

<script>
import {
  Delete,
  Edit,
  InfoFilled,
  CirclePlus,
  EditPen
} from '@element-plus/icons-vue'
import {Plus} from "@element-plus/icons";
import TestCasesEdit from "@/components/TestTrack/TestCasesEdit";
import axios from "axios";
import request from "@/utils/request";
import useStore from 'vuex'
let id = 1000;
let nodeId;
// let param = this.$store.state.project
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
  name: "FunctionalTestCases",
  components: {
    Delete,
    Edit,
    Plus,
    InfoFilled,
    CirclePlus,
    EditPen,
    TestCasesEdit
  },
  props:{
    project:Number
  },
  data() {
    return {
      tableData: [],
      indexNum:'',
      func:0,
      data:[],
      dialogTableVisible: false,
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      currentNode: '',
      param:0
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
  created(){
    axios.get("http://192.168.0.1:9090/functctree",{
      params:{
        projectId:this.$store.state.project
      }
    }).then(res =>{
      nodeId = parseInt(res.data[0].id)
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
  },
  mounted() {

  },
  watch:{
    project:function (nv,ov){
      this.param = nv
      // console.log(this.project)
      // console.log(nv,ov)
      axios.get("http://192.168.0.1:9090/functctree",{
        params:{
          projectId:this.param
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
      })
    }
  },
  methods: {
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

    },
    nodeLoad(){

      axios.get("http://192.168.0.1:9090/functctree/nodeclick",{
        params:{
          id:nodeId
        }
      }).
      then(res =>{
        this.tableData = res.data
      })
    },

    editCase(row){
      this.dialogTableVisible = true
      // console.log(this.tableData[row].id)
      this.indexNum = this.tableData[row].id
    },
    focus(event, node) {
      // 获取焦点
      this.whetherEditNodeName = false;
      event.target.value = node.label;
      event.currentTarget.select();
    },
    inp(value, data) {
      // console.log(data);
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
        axios.post("http://192.168.0.1:9090/functctree/update",JSON.parse(JSON.stringify(data)))
      }
      data.isEdit = !data.isEdit;
    },

    append(data) {
      axios.get("http://192.168.0.1:9090/functctree/add",{
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

    remove(node, data) {
      axios.get("http://192.168.0.1:9090/functctree/del",{
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
    handleNodeClick(data) {
      console.log(data);
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
      axios.get("http://192.168.0.1:9090/funcInfoAddDefault",{
        params:{
          nodeId:nodeId
        }
      }).then(res =>{
        this.tableData = res.data
      })
    },
    deleteRow(index, rows) {
      // let param = JSON.parse(JSON.stringify(rows[index]))
      // console.log(rows[index].id)
      axios.get("http://192.168.0.1:9090/funcInfoDelete", {
        params:{
          id:rows[index].id,
          nodeId:nodeId
        }
      }).then(res=>{
        this.tableData = res.data
      })
      // this.nodeLoad()

    },
    cancelEvent(){

    },
    save(){
      this.dialogTableVisible = false
      let params = JSON.parse(JSON.stringify(this.$refs.dialog.form2))
      let detial = JSON.parse(JSON.stringify(this.$refs.dialog.gridData))
      // console.log(detial)
      axios.post("http://192.168.0.1:9090/funcInfoUpdate",params)
      axios.post("http://192.168.0.1:9090/funcdetialupdate",detial)

      this.nodeLoad()

    }

  }
}
</script>

<style  lang="less" scoped>

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

.show-hide:hover :nth-child(2) {
  display: inline-block !important;
}

</style>