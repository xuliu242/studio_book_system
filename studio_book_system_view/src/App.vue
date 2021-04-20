<template>
  <el-container>
    <el-header>
      <Header>
        <img slot="logo" src="../static/logo.png" alt="" />
        <span slot="logo-text">开放式工作室预定系统</span>
      </Header>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <Aside></Aside>
      </el-aside>
      <el-main>
        <Main></Main>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import Header from "./components/Header/Header";
import Aside from "./components/Aside/Aside";
import Main from "./components/Main/Main";
export default {
  name: "App",
  components: {
    Header,
    Aside,
    Main
  }
};
</script>

<style>
.el-header {
  background-color: rgba(29, 27, 27, 0.87);
  color: #333;
  text-align: center;
  line-height: 60px;
  /* 去两侧内边距 */
  padding: 0;
}
.el-header span {
  color: #fff;
  font-size: 20px;
}

.el-aside {
  background-color: rgba(29, 27, 27, 0.87);
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
  height: 100%;
}
</style>
