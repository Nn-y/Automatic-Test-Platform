<template>
  <el-container class="layout-container-demo" style="height: calc(100vh - 90px); width: 100%">
    <el-aside width="240px">
      <el-scrollbar style="height:100% ;" >
        <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick" style="padding-left: 30px; padding-top: 10px;" />
      </el-scrollbar>
    </el-aside>

    <el-container>
      <el-main style="width: 100% ;border-radius: 20px;box-shadow: 0px 2px 12px 0 rgba(0,0,0,0.1);margin: 10px">
        <el-scrollbar>
          <el-table :data="tableData" stripe height="calc(100vh - 90px)"  >
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
            <el-table-column fixed="right" label="操作" width="120">
              <template #default>
                <el-button type="primary" circle>
                  <el-icon style="vertical-align: middle;">
                    <Edit/>
                  </el-icon>
                </el-button>
                <el-button type="danger" circle>
                  <el-icon style="vertical-align: middle;">
                    <Delete/>
                  </el-icon>
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import {
  Delete,
  Edit,
} from '@element-plus/icons-vue'
export default {
  name: "InterfaceTestCases",
  components: {
    Delete,
    Edit,
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
      tableData: Array(20).fill(item),
      data: [
        {
          label: 'Interface',
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
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  methods: {
    handleNodeClick(data) {
      console.log(data);
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