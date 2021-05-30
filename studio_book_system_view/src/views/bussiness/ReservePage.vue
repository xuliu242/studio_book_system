<template>
  <div>
    <div style="margin-bottom: 20px">
      <el-button type="primary" @click="dialogFormVisible = true" disabled
        >+ 新建
      </el-button>
      <!--      搜索框-->

      <el-input
        style="width: 220px;margin-left: 20px"
        placeholder="请输入用户名"
        v-model="queryReserveCondition.syhUserName"
        prefix-icon="el-icon-search"
        type="text"
        clearable
      >
      </el-input>
      <el-input
        style="width: 220px;margin-left: 20px"
        placeholder="请输入工位名"
        v-model="queryReserveCondition.syhSitName"
        prefix-icon="el-icon-search"
        type="text"
        clearable
      >
      </el-input>

      <!--      <el-date-picker-->
      <!--        v-model="dateTime"-->
      <!--        type="datetime"-->
      <!--        value-format="yyyy-MM-dd HH:mm:ss"-->
      <!--        placeholder="选择日期时间">-->
      <!--      </el-date-picker>-->
      <el-button type="primary" plain @click="searchBtnClick">查询</el-button>
      <el-button type="success" plain @click="searchResetBtnClick"
        >重置
      </el-button>
    </div>

    <!--table-->
    <el-table
      ref="multipleTable"
      border
      :data="reserveList"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="date" label="序号" width="50">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="syhClassroomNumber" label="工作室编号" width="120">
      </el-table-column>
      <el-table-column prop="syhSitName" label="工位名" width="120">
      </el-table-column>
      <el-table-column
        prop="syhUserName"
        label="用户名"
        width="80"
      ></el-table-column>
      <el-table-column
        prop="syhUserNumber"
        label="用户账号"
        width="120"
      ></el-table-column>
      <el-table-column
        prop="syhReserveStartTime"
        label="预定时间"
      ></el-table-column>
      <el-table-column
        prop="syhReserveEndTime"
        label="结束时间"
      ></el-table-column>
      <el-table-column prop="syhReserveStatus" label="状态" width="100">
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.syhReserveStatus == 1">
            预定成功
          </el-tag>
          <el-tag type="info" v-if="scope.row.syhReserveStatus == 2"
            >预定结束
          </el-tag>
          <el-tag type="warning" v-if="scope.row.syhReserveStatus == 0"
            >预定失败
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button size="small" disabled>编辑 </el-button>

          <el-button
            @click="handleDeleteClick(scope.row)"
            type="danger"
            size="small"
            >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="bottom" style="margin-bottom: 0;margin-top: 10px">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryReserveCondition.pageNum"
        :page-sizes="[8, 10, 20]"
        :page-size="queryReserveCondition.pageSize"
        :background="true"
        :pager-count="5"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        class="pageControl"
      >
      </el-pagination>
    </div>
  </div>
</template>
<script>
export default {
  name: "",
  props: [""],
  data() {
    return {
      reserveList: [
        // {
        //   syhReserveId: null,
        //   syhUserId: null,
        //   syhSitId: null,
        //   syhReserveStartTime: null,
        //   syhReserveEndTime: null,
        //   syhUserName: null,
        //   syhUserNumber: null,
        //   syhClassroomNumber: null,
        //   syhSitName: null,
        //   syhReserveStatus: null
        // }
      ],
      reserveListForm: {
        syhReserveId: null,
        syhUserId: null,
        syhSitId: null,
        syhReserveStartTime: null,
        syhReserveEndTime: null,
        syhUserName: null,
        syhUserNumber: null,
        syhClassroomNumber: null,
        syhSitName: null,
        syhReserveStatus: null
      },
      queryReserveCondition: {
        pageNum: 1,
        pageSize: 8,
        syhUserName: null,
        syhSitName: null
      },
      total: 0
    };
  },
  mounted() {
    this.$emit("getMessage", ["业务管理", "预定管理"]);
    this.load();
  },
  methods: {
    load() {
      this.$http
        .post("/reserve/queryReserveCondition", this.queryReserveCondition)
        .then(res => {
          this.reserveList = res.data.data.result;
          this.total = res.data.data.total;
          console.log("res", res);
          console.log("this.reserveList", this.reserveList);
        });
    },
    searchBtnClick() {
      //根据条件查询
      // console.log('time change', this.dateTime)
      // this.queryReserveCondition.syhClassroomName=this.dateTime
      this.queryReserveCondition.pageNum = 1;
      console.log("queryReserveCondition", this.queryReserveCondition);
      this.$http
        .post("/reserve/queryReserveCondition", this.queryReserveCondition)
        .then(res => {
          this.reserveList = [];
          this.reserveList = res.data.data.result;
          this.total = res.data.data.total;
          this.pageNum = res.data.data.pages;
          console.log("res", res);
          console.log("this.reserveList", this.reserveList);
        });
    },
    searchResetBtnClick() {
      //重置搜索条件
      this.queryReserveCondition.pageNum = 1;
      this.queryReserveCondition.syhSitName = null;
      this.queryReserveCondition.syhUserName = null;

      this.load();
    },
    handleSelectionChange(val) {
      //多选
      // if (val.length>0){
      //   this.multipleDeleteButton=false;
      // }else {
      //   this.multipleDeleteButton=true;
      // }
      // this.multipleSelection = val;
      console.log("multipleSelection", this.multipleSelection);
    },
    handleSizeChange: function(size) {
      //选择 一页展示多少数据时 调用的方法
      this.queryReserveCondition.pageSize = size;
      console.log(size + "****");
      this.queryReserveCondition.pageNum = 1;
      this.load();
    },
    handleCurrentChange: function(currentPage) {
      //点击页面项 的函数响应
      this.queryReserveCondition.pageNum = currentPage;
      console.log(
        "this.currentPage = currentPage;",
        this.queryReserveCondition.pageNum
      );
      this.load();
    },
    handleDeleteClick(rows) {
      //删除教室
      console.log("delete rows", rows);
      var id = rows.syhReserveId;
      this.$confirm("此操作将永久删除该教室, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          console.log("postid", id);
          this.$http
            .get("/reserve/deleteReserve", { params: { reserveId: id } })
            .then(res => {
              console.log("res", res);
              this.load();
              if (res.data.code == 200) {
                //成功删除弹窗
                setTimeout(
                  this.$message({
                    type: "success",
                    message: "删除成功!"
                  }),
                  1000
                );
              } else {
                this.$message.error(res.data.message);
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    }
  }
};
</script>
<style scoped></style>
