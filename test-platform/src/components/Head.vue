<template>
  <el-menu
      router
      :default-active="$route.meta.name"
      class="el-menu-demo"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      style="height: 50px"
  >
    <el-menu-item index="testTrack" :route="{path:'/TestTrack'}">测试跟踪</el-menu-item>
    <el-menu-item index="1" :route="{path:'/functionTest'}" >功能测试</el-menu-item>
    <el-menu-item index="interfaceTest" :route="{path:'/interfaceTest'}">接口测试</el-menu-item>
    <el-menu-item index="3" :route="{path:'/performanceTest'}">性能测试</el-menu-item>
    <el-sub-menu index="4" style="position: absolute;right: 0; height: 100%">
      <template #title >个人</template>
      <el-menu-item index="4-1">个人信息</el-menu-item>
      <el-menu-item index="4-2">退出系统</el-menu-item>
    </el-sub-menu>
  </el-menu>
  <router-view></router-view>
</template>

<script>
import {Plus,ArrowDown} from '@element-plus/icons'
export default {
  components: {
    Plus,ArrowDown,
  },
  name: "Head"
}
</script>

<style scoped>

</style>