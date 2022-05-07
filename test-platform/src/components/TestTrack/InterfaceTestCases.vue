<template>
  <el-container class="layout-container-demo" style="height: calc(100vh - 90px); width: 100%">
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

    <el-container>
      <el-main style="width: 100% ;border-radius: 20px;box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);margin: 10px">
        <el-scrollbar>
          <el-table :data="tableData" stripe height="calc(100vh - 110px)"  >
            <el-table-column prop="ID" label="ID" width="120" />
            <el-table-column prop="name" label="名称" width="120" />
            <el-table-column prop="version" label="版本" width="120"/>
            <el-table-column prop="tag" label="标签" width="120"/>
            <el-table-column prop="module" label="所属模块" width="120" />
            <el-table-column prop="project" label="所属项目" width="120"/>
            <el-table-column prop="bugs" label="缺陷" width="80"/>
            <el-table-column prop="user" label="执行人" width="100"/>
            <el-table-column prop="result" label="执行结果" width="100"/>
            <el-table-column prop="utime" label="更新时间" width="140"/>
            <el-table-column prop="ctime" label="创建时间" width="140"/>
            <el-table-column prop="level" label="用例等级" width="120"/>
            <el-table-column prop="status" label="用例状态" width="120"/>
            <el-table-column fixed="right" label="操作" width="120" :render-header="renderHeader">
              <template v-slot:default="scope">
                <el-button type="primary" circle  @click="dialogTableVisible = true">
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
              <TestCasesEdit></TestCasesEdit>
        <template #footer>
            <span class="dialog-footer">
              <el-button type="success" round  @click="dialogVisible = false">保存</el-button>
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
let id = 1000;
export default {
  name: "FunctionalTestCases",
  components: {
    Delete,
    Edit,
    Plus,
    InfoFilled,
    CirclePlus,
    EditPen,
    TestCasesEdit,
  },
  data() {
    const item = {
      ID: '100001',
      name: 'Test',
      version: '2.0.1',
      tag:' ',
      module:'无',
      project:'项目一',
      bugs:'0',
      user:'Tom',
      result:'successful',
      utime:'2022-4-13',
      ctime:'2022-4-13',
      level:'P0',
      status:'已执行',
    };
    return {
      tableData: Array(15).fill(item),
      whetherEditNodeName:false,
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
      gridData: [{
        steps: '步骤一',
        flag1 : false,
        flag2 : false,
        flag3 : false,
        expected: '预期',
        actual: '结果一'
      }, {
        steps: '步骤二',
        flag1 : false,
        flag2 : false,
        flag3 : false,
        expected: '预期',
        actual: '结果二'
      }, {
        steps: '步骤三',
        flag1 : false,
        flag2 : false,
        flag3 : false,
        expected: '预期',
        actual: '结果三'
      }, {
        steps: '步骤四',
        flag1 : false,
        flag2 : false,
        flag3 : false,
        expected: '预期',
        actual: '结果四'
      }],
      dialogTableVisible: false,
      defaultProps: {
        children: 'children',
        label: 'label',
        id:'id'
      }
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
        module:'无',
        project:'',
        bugs:'0',
        user:'',
        result:'',
        utime:'',
        ctime:'',
        level:'',
        status:'',
      })
    },


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