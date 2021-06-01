<template>
  <div>
    <div>
      <!--      新建/edit角色弹窗开始-->
      <el-button type="primary" @click="dialogFormVisible = true"
        >+ 新建</el-button
      >
      <!--      搜索框-->

      <el-input
        style="width: 220px;margin-left: 20px"
        placeholder="请输入工作室名"
        v-model="queryClassroomCondition.syhClassroomName"
        prefix-icon="el-icon-search"
        type="text"
        clearable
      >
      </el-input>
      <el-select
        v-model="queryClassroomCondition.syhClassroomType"
        style="width: 180px"
        placeholder="选择工作室类型"
      >
        <el-option
          v-for="item in classroomType"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>

      <!--      <el-date-picker-->
      <!--        v-model="dateTime"-->
      <!--        type="datetime"-->
      <!--        value-format="yyyy-MM-dd HH:mm:ss"-->
      <!--        placeholder="选择日期时间">-->
      <!--      </el-date-picker>-->
      <el-button type="primary" plain @click="searchBtnClick">查询</el-button>
      <el-button type="success" plain @click="searchResetBtnClick"
        >重置</el-button
      >

      <el-dialog
        title="编辑工作室信息"
        :visible.sync="dialogFormVisible"
        :close-on-click-modal="false"
        :show-close="true"
      >
        <el-form
          :model="classroomListForm"
          :rules="rules"
          ref="classroomListForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="工作室名称" prop="syhClassroomName">
            <el-input v-model="classroomListForm.syhClassroomName"></el-input>
          </el-form-item>
          <el-form-item label="工作室编号" prop="syhClassroomNumber">
            <el-input v-model="classroomListForm.syhClassroomNumber"></el-input>
          </el-form-item>

          <!--          <el-form-item label="角色类型" prop="lxRoleType">-->
          <!--            <el-input v-model="classroomListForm.lxRoleType"></el-input>-->
          <!--          </el-form-item>-->
          <el-form-item label="位置信息描述" prop="syhClassroomInfo">
            <el-input
              type="textarea"
              v-model="classroomListForm.syhClassroomInfo"
            ></el-input>
          </el-form-item>
          <el-form-item label="类型" prop="syhRoleStatus">
            <el-select
              v-model="classroomListForm.syhClassroomType"
              placeholder="请选择工作室类型"
            >
              <el-option
                v-for="item in classroomType"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('classroomListForm')"
              >保存</el-button
            >
            <el-button @click="resetForm('classroomListForm')">取消</el-button>
          </el-form-item>
        </el-form>
        <!--        <div slot="footer" class="dialog-footer">-->
        <!--          <el-button @click="dialogFormVisible = false">取 消</el-button>-->
        <!--          <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>-->
        <!--        </div>-->
      </el-dialog>
      <!--      新建角色弹窗end-->
    </div>
    <div style="margin-top: 20px"></div>
    <!--table-->
    <el-table
      ref="multipleTable"
      border
      :data="classroomList"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column prop="date" label="序号" width="80">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="syhClassroomNumber" label="工作室编号" width="180">
      </el-table-column>
      <el-table-column prop="syhClassroomName" label="工作室名称">
      </el-table-column>
      <el-table-column prop="syhClassroomInfo" label="位置"></el-table-column>
      <el-table-column prop="syhClassroomType" label="类型">
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.syhClassroomType == 1">
            大型教室
          </el-tag>
          <el-tag type="primary" v-if="scope.row.syhClassroomType == 2"
            >中型教室
          </el-tag>
          <el-tag type="warning" v-if="scope.row.syhClassroomType == 3"
            >小型教室
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="syhClassroomSitNumber" label="工位数量">
      </el-table-column>
      <el-table-column label="操作" width="220">
        <template slot-scope="scope">
          <el-button @click="updateClick(scope.row)" size="small"
            >编辑
          </el-button>

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
        :current-page="queryClassroomCondition.pageNum"
        :page-sizes="[8, 10, 20]"
        :page-size="queryClassroomCondition.pageSize"
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
      classroomList: [
        // {
        //   syhClassroomId: "",
        //   syhClassroomNumber: "",
        //   syhClassroomName: "",
        //   syhClassroomInfo: "",
        //   syhClassroomType: "",
        //   syhClassroomSitNumber: "",
        //   reserveAbleSitNum: ""
        // }
      ],
      classroomListForm: {
        syhClassroomId: "",
        syhClassroomNumber: "",
        syhClassroomName: "",
        syhClassroomInfo: "",
        syhClassroomType: "",
        syhClassroomSitNumber: 0
      },
      classroomListFormInit: {
        syhClassroomId: "",
        syhClassroomNumber: "",
        syhClassroomName: "",
        syhClassroomInfo: "",
        syhClassroomType: "",
        syhClassroomSitNumber: 0
      },
      queryClassroomCondition: {
        pageNum: 1,
        pageSize: 8,
        syhClassroomName: "",
        syhClassroomType: null
      },
      syhClassroomType: null,
      classroomType: [
        {
          value: "1",
          label: "大型教室"
        },
        {
          value: "2",
          label: "中型教室"
        },
        {
          value: "3",
          label: "小型教室"
        }
      ],
      total: 0,
      dialogFormVisible: false
    };
  },
  mounted() {
    this.$emit("getMessage", ["场地管理", "工作室管理"]);
    this.load();
  },
  methods: {
    load() {
      this.$http
        .post(
          "/classroom/queryClassroomCondition",
          this.queryClassroomCondition
        )
        .then(res => {
          this.classroomList = res.data.data.result;
          this.total = res.data.data.total;
          console.log("res", res);
          console.log("this.roleList", this.classroomList);
        });
    },
    searchBtnClick() {
      //根据条件查询
      // console.log('time change', this.dateTime)
      // this.queryClassroomCondition.syhClassroomName=this.dateTime
      this.queryClassroomCondition.pageNum = 1;

      console.log("queryClassroomCondition", this.queryClassroomCondition);
      this.$http
        .post(
          "/classroom/queryClassroomCondition",
          this.queryClassroomCondition
        )
        .then(res => {
          this.classroomList = [];
          this.classroomList = res.data.data.result;
          this.total = res.data.data.total;
          this.pageNum = res.data.data.pages;
          console.log("res", res);
          console.log("this.tableData", this.classroomList);
        });
    },
    searchResetBtnClick() {
      //重置搜索条件
      this.queryClassroomCondition.pageNum = 1;
      this.queryClassroomCondition.syhClassroomName = null;
      this.queryClassroomCondition.syhClassroomType = null;

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
      this.queryClassroomCondition.pageSize = size;
      console.log(size + "****");
      this.queryClassroomCondition.pageNum = 1;
      this.load();
    },
    handleCurrentChange: function(currentPage) {
      //点击页面项 的函数响应
      this.queryClassroomCondition.pageNum = currentPage;
      console.log(
        "this.currentPage = currentPage;",
        this.queryClassroomCondition.pageNum
      );
      this.load();
    },
    updateClick(rows) {
      console.log("updateClick", rows);
      this.classroomListForm = { ...rows };
      this.classroomListForm.syhClassroomType = "";
      console.log("updateClick,roleForm", this.classroomListForm);
      this.dialogFormVisible = true;
    },
    submitForm(classroomListForm) {
      //添加/更新工作室
      this.$refs[classroomListForm].validate(valid => {
        if (valid) {
          console.log("classroomListForm", this.classroomListForm);
          if (
            this.classroomListForm.syhClassroomId == null ||
            this.classroomListForm.syhClassroomId == ""
          ) {
            //此处id为空，为新增操作
            this.$http
              .post("/classroom/saveOrUpdateClassroom", this.classroomListForm)
              .then(res => {
                console.log("res", res);
                this.load();
                if (res.data.code == 200) {
                  this.$refs[classroomListForm].resetFields();
                  setTimeout(
                    this.$message({
                      message: "操作成功",
                      type: "success"
                    }),
                    1000
                  );
                } else {
                  this.$message.error(res.data.message);
                }
              });
          } else {
            //此处id不为空，为更新操作
            this.$http
              .post("/classroom/saveOrUpdateClassroom", this.classroomListForm)
              .then(res => {
                console.log("res", res);
                this.load();

                if (res.data.code == 200) {
                  this.$refs[classroomListForm].resetFields();
                  setTimeout(
                    this.$message({
                      message: "操作成功",
                      type: "success"
                    }),
                    1000
                  );
                } else {
                  this.$message.error(res.data.message);
                }
              });
          }
          this.closeDialog();
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    // dialog取消
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.closeDialog();
    },
    handleDeleteClick(rows) {
      //删除教室
      console.log("delete rows", rows);
      var id = rows.syhClassroomId;
      this.$confirm("此操作将永久删除该教室, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          console.log("postid", id);
          this.$http
            .get("/classroom/deleteClassroom", { params: { classroomId: id } })
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
    },
    closeDialog() {
      this.classroomListForm = { ...this.classroomListFormInit };
      this.dialogFormVisible = false;
    }
  }
};
</script>
<style scoped></style>
