<template>
  <div id="bg">
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
    <el-table :data="tableData" height="100%" border style="width: 100%;">
      <el-table-column type="selection" width="55px"> </el-table-column>
      <el-table-column prop="id" label="序号" width="85px"> </el-table-column>
      <el-table-column prop="name" label="角色名称" width="200px">
      </el-table-column>
      <el-table-column prop="amount1" label="角色描述" width="210px">
      </el-table-column>
      <el-table-column prop="amount2" sortable label="创建时间" width="220px">
      </el-table-column>
      <el-table-column label="状态">
        <el-switch v-model="value"></el-switch>
      </el-table-column>
      <el-table-column label="操作">
        <el-button plain size="small">编辑</el-button>
        <el-button type="warning" size="small">授权</el-button>
        <el-button type="danger" size="small">删除</el-button>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "roleTable",
  data() {
    return {
      // 开关状态值
      value: true,
      // 输入框绑定值
      input: "",
      // 时间选择器绑定
      datetime: "",
      tableData: [
        {
          id: "1",
          name: "王小虎",
          amount1: "234",
          amount2: "3.2"
        },
        {
          id: "2",
          name: "王小虎",
          amount1: "165",
          amount2: "4.43"
        },
        {
          id: "3",
          name: "王小虎",
          amount1: "324",
          amount2: "1.9"
        },
        {
          id: "4",
          name: "王小虎",
          amount1: "621",
          amount2: "2.2"
        },
        {
          id: "12987126",
          name: "王小虎",
          amount1: "539",
          amount2: "4.1"
        }
      ]
    };
  }
};
</script>

<style>
.el-main {
  line-height: 10px;
}
/* 往背景添加白色 */
#bg {
  background-color: #fff;
  margin-top: 15px;
  padding: 15px 20px 10px;
}

#row {
  margin-bottom: 10px;
}
</style>
