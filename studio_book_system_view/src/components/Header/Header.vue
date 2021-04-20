<template>
  <div>
    <el-row>
      <el-col :span="5">
        <slot name="logo"></slot>
        <slot name="logo-text"></slot>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Header"
};
</script>

<style>
header img {
  width: 60px;
  height: 60px;
  margin-left: 0;
}
</style>
