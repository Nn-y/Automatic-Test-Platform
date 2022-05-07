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

    <el-container style="height: calc(100vh - 90px); width: 100%" class="layout-container-demo">
      <el-aside width="240px">
        <el-scrollbar style="height: 100%">
          <div class="down-tree">
            <el-tree
                :data="data"
                :props="defaultProps"
                node-key="id"
                default-expand-all
                :expand-on-click-node="false"
                style="padding-top: 10px;overflow:auto;"
                highlight-current
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
                  <el-icon v-if="node.label !== '全部用例'" @click="() => remove(node, data)" color="#409EFC" class="no-inherit"><delete /></el-icon>

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
          <el-tab-pane label="接口列表" :name="1">
            <el-scrollbar>
              <el-table :data="tableData" stripe height="calc(100vh - 170px)"  >
                <el-table-column prop="ID" label="ID" width="120" />
                <el-table-column prop="name" label="接口名称" width="120" />
                <el-table-column prop="type" label="请求类型" width="120" />
                <el-table-column prop="user" label="责任人" width="100"/>
                <el-table-column prop="tag" label="标签" width="120"/>
                <el-table-column prop="version" label="版本" width="120"/>
                <el-table-column prop="utime" label="更新时间" width="140"/>
                <el-table-column prop="ctime" label="创建时间" width="140"/>
                <el-table-column prop="status" label="接口状态" width="120"/>
                <el-table-column fixed="right" label="操作" width="120" :render-header="renderHeader">
                  <template v-slot:default="scope">
                    <el-button type="primary" circle  @click="addTab(editableTabsValue)">
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
              :key="item.name"
              :label="item.title"
              :name="item.name"
              closable
          >
            <component :is="item.content" :ref="item.content" height="calc(100vh - 170px)" />
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
let id = 1000;
export default {
  name: "InterfaceTest",
  components:{
    Delete,
    Edit,
    InfoFilled,
    InterfaceTestDetial,
    CirclePlus,
  },
  data(){
    const item = {
      ID: '100001',
      name: 'Test',
      type:'get',
      version: '2.0.1',
      tag:' ',
      user:'Tom',
      utime:'2022-4-13',
      ctime:'2022-4-13',
      status:'已执行',
    };
    return{
      data: [
        {
          id:1,
          label: '全部用例',
          children: [
            {
              id:2,
              label: 'Interface 1-1',
              isEdit:false,
              children: [
                {
                  id:5,
                  label: 'Interface 1-1-1',
                  isEdit:false,
                  children:[]
                },
              ],
            },
            {
              id:3,
              label: 'Function 2',
              isEdit:false,
              children: [
                {
                  id:6,
                  label: 'Function 2-1',
                  isEdit:false,
                  children:[]
                },
                {
                  id:7,
                  label: 'Function 2-2',
                  isEdit:false,
                  children: [
                    {
                      id:11,
                      label: 'Function 2-2-1',
                      isEdit:false,
                      children:[]
                    },
                  ],
                },
              ],
            },
            {
              id:4,
              label: 'Function 3',
              isEdit:false,
              children: [
                {
                  id:8,
                  label: 'Function 3-1',
                  isEdit:false,
                  children: [
                    {
                      id:12,
                      label: 'Function 3-1-1',
                      isEdit:false,
                      children:[]
                    },
                  ],
                },
                {
                  id:9,
                  label: 'Function 3-2',
                  isEdit:false,
                  children: [
                    {
                      id:13,
                      label: 'Function 3-2-1',
                      isEdit:false,
                      children:[]
                    },
                  ],
                },
              ],
            },
          ],

        },
      ],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      tableData: Array(15).fill(item),
      editableTabsValue: '2',
      editableTabs: [{
        title: 'Tab 1',
        name: '2',
        content: 'InterfaceTestDetial',
      }, {
        title: 'Tab 2',
        name: '3',
        content: 'InterfaceTestDetial',
      }],
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
      }
      data.isEdit = !data.isEdit;
    },

    append(data) {
      const newChild = { id: id++, label: 'testtest', children: [],   isEdit: true, };
      if (!data.children) {
        this.$set(data, 'children', []);
      }
      data.children.push(newChild);
    },

    remove(node, data) {
      const parent = node.parent;
      const children = parent.data.children || parent.data;
      const index = children.findIndex(d => d.id === data.id);
      if(node.childNodes.length !== 0){
        this.$message({
          message: '该节点下存在子节点，不允许直接删除',
          type: 'warning'
        });
        return;
      }
      children.splice(index, 1);
    },
    handleNodeClick(data) {
      console.log(data);
    },
    deleteRow(index, rows) {
      rows.splice(index, 1);
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
      this.tableData.push({
        ID: '未创建',
        name: '未创建',
        version: '',
        tag:'',
        type:'未知',
        user:'',
        utime:'',
        ctime:'',
        status:'',
      })
    },
    addTab(targetName) {
      let newTabName = ++this.tabIndex + '';
      this.editableTabs.push({
        title: 'New Tab',
        name: newTabName,
        content: 'InterfaceTestDetial'
      });
      this.editableTabsValue = newTabName;
    },
    removeTab(targetName) {
      let tabs = this.editableTabs;
      let activeName = this.editableTabsValue;
      if (activeName === targetName) {
        tabs.forEach((tab, index) => {
          if (tab.name === targetName) {
            let nextTab = tabs[index + 1] || tabs[index - 1];
            if (nextTab) {
              activeName = nextTab.name;
            }else{
              activeName = 1;
            }
          }
        });
      }
      this.editableTabsValue = activeName;
      this.editableTabs = tabs.filter(tab => tab.name !== targetName);

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
</style>