<template>
  <el-menu
      router
      :default-active="$route.meta.name"
      class="el-menu-demo"
      mode="horizontal"
      style="height: 40px"
  >
    <el-sub-menu>
      <template #title>项目</template>
      <el-menu-item >项目一</el-menu-item>
      <el-menu-item>项目二</el-menu-item>
      <el-menu-item >项目三</el-menu-item>
      <el-menu-item divided><el-icon><plus /></el-icon>添加项目</el-menu-item>
    </el-sub-menu>
    <el-menu-item index="testTrack" :route="{path:'/TestTrack/function'}">功能测试用例</el-menu-item>
    <el-menu-item index="2" :route="{path:'/TestTrack/interface'}">接口测试用例</el-menu-item>
<!--    <el-menu-item index="3" :route="{path:'/TestTrack/performance'}">性能测试用例</el-menu-item>-->
  </el-menu>
  <router-view></router-view>
</template>

<script>
import {Plus,ArrowDown} from '@element-plus/icons'
export default {
  components: {
    Plus,ArrowDown,
  },
  name: "TestTrackMenu"
}
</script>

<style scoped>

.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
</style>