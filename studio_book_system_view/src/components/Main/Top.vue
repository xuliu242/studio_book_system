<template>
  <div>
    <el-row id="row">
      <el-col :span="1">
        <el-button type="primary" size="small">+新建</el-button>
      </el-col>
      <el-col :span="4" :offset="1">
        <el-input
          placeholder="请输入角色名"
          prefix-icon="el-icon-search"
          v-model="input"
          size="small"
        >
        </el-input>
      </el-col>
      <el-col :span="4">
        <el-time-select
          v-model="datetime"
          :picker-options="{
            start: '08:30',
            step: '00:15',
            end: '18:30'
          }"
          size="small"
          placeholder="选择时间"
        >
        </el-time-select>
      </el-col>
      <el-col :span="7">
        <el-button type="primary" plain size="small">查询</el-button>
        <el-button type="success" plain size="small">重置</el-button>
        <el-button type="danger" plain size="small">批量删除</el-button>
        <el-button type="success" plain size="small">取消多选</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Top",
  data() {
    return {
      // 输入框绑定值
      input: "",
      // 时间选择器绑定
      datetime: ""
    };
  }
};
</script>

<style>
#row {
  margin-bottom: 10px;
}
</style>
