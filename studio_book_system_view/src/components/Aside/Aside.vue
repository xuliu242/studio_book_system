<template>
  <div>
    <el-menu
      default-active="1-1"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
    >
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span>系统</span>
        </template>
        <el-menu-item index="1-1" @click="roleClick">角色管理</el-menu-item>
        <el-menu-item index="1-2" @click="userClick">用户管理</el-menu-item>
        <el-menu-item index="1-3">菜单管理</el-menu-item>
      </el-submenu>
      <el-menu-item index="2" style="text-align: left;">
        <i class="el-icon-timer"></i>
        <span slot="title">预定信息</span>
      </el-menu-item>
      <el-submenu index="3" collapse="true">
        <template slot="title">
          <i class="el-icon-document"></i>
          <span>场地管理</span>
        </template>
        <el-menu-item index="3-1">工位相关</el-menu-item>
        <el-menu-item index="3-2">场地类型</el-menu-item>
      </el-submenu>
      <el-menu-item index="4" style="text-align: left;">
        <i class="el-icon-pie-chart"></i>
        <span slot="title">数据统计</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside",
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    roleClick() {
      this.$router.push("/roleTable").catch(err => err);
    },
    userClick() {
      this.$router.push("/userTable").catch(err => err);
    }
  }
};
</script>

<style>
/* 系统 靠左边显示 */
.el-submenu__title {
  padding-left: 5px !important;
  text-align: left;
}
/* 图标和文字间距 */
.el-submenu [class^="el-icon-"] {
  margin-right: 10px;
}
.el-menu-item [class^="el-icon-"] {
  margin-right: 10px;
}
/* 子项里面选项调整 也会影响到下面的 */
.el-menu-item {
  padding-left: 5px !important;
  padding-right: 0px;
}
</style>
