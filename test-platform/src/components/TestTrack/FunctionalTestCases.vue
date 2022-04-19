<template>
  <el-container class="layout-container-demo" style="height: calc(100vh - 90px); width: 100%">
    <el-aside width="240px">
      <el-scrollbar style="height:100% ;" >
        <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick" style="padding-left: 30px; padding-top: 10px;" />
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
          <el-tabs type="border-card" style="height:400px">
            <el-tab-pane label="信息">
              <el-form :inline="true" :model="form2" label-width="120px">
                <el-form-item label="ID">
                  <el-input v-model="form2.id"/>
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
                <el-form-item label="所属项目">
                  <el-input v-model="form2.project" />
                </el-form-item><el-form-item label="缺陷">
                <el-input v-model="form2.bugs" />
              </el-form-item><el-form-item label="执行人">
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
                  <el-input type="textarea" v-model="form.pre"></el-input>
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
                    <template v-slot:default="scope">
                      <el-button round  @click="addRow(scope.$index, gridData)">
                        <el-icon style="vertical-align: middle;">
                          <Plus/>
                        </el-icon>
                      </el-button>
                      <el-button round  @click.native.prevent="deleteRow(scope.$index, gridData)">
                        <el-icon style="vertical-align: middle;">
                          <Delete/>
                        </el-icon>
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-scrollbar>

            </el-tab-pane>
          </el-tabs>

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
} from '@element-plus/icons-vue'
import {Plus} from "@element-plus/icons";
export default {
  name: "FunctionalTestCases",
  components: {
    Delete,
    Edit,
    Plus,
    InfoFilled
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
      form:{
        pre:'',
      },
      form2:{
        id:'',
        name: '',
        version: '',
        tag:'',
        module:'',
        project:'',
        bugs:'',
        user:'',
        result:'',
        level:'',
        status:'',
      },
      dialogVisible: false,
      data: [
        {
          label: 'Function',
          children: [
            {
              label: 'Function 1-1',
              children: [
                {
                  label: 'Function 1-1-1',
                },
              ],
            },
          ],
        },
        {
          label: 'Function 2',
          children: [
            {
              label: 'Function 2-1',
              children: [
                {
                  label: 'Function 2-1-1',
                },
              ],
            },
            {
              label: 'Function 2-2',
              children: [
                {
                  label: 'Function 2-2-1',
                },
              ],
            },
          ],
        },
        {
          label: 'Function 3',
          children: [
            {
              label: 'Function 3-1',
              children: [
                {
                  label: 'Function 3-1-1',
                },
              ],
            },
            {
              label: 'Function 3-2',
              children: [
                {
                  label: 'Function 3-2-1',
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
        label: 'label'
      }
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
    handleNodeClick(data) {
      console.log(data);
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
        default: return
      }

    },
    //input框失去焦点事件
    inputClick(row){
      row.flag1=false
      row.flag2=false
      row.flag3=false
    },
    addRow(index,rows){
      rows.splice(index+1, 0,
          {
            steps: '',
            flag1 : false,
            flag2 : false,
            flag3 : false,
            expected: '',
            actual: ''
          });
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
    }

  }
}
</script>

<style scoped>

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

</style>