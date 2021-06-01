<template>
  <div>
    <div>
      <!--      新建用户弹窗开始-->
      <el-button type="primary" @click="dialogFormVisible = true"
        >+ 新建
      </el-button>
      <!--      搜索框-->

      <el-input
        style="width: 180px;margin-left: 20px"
        placeholder="请输入用户名"
        v-model="queryUserCondition.userName"
        prefix-icon="el-icon-search"
        type="text"
        clearable
      >
      </el-input>
      <el-select
        v-model="queryUserCondition.status"
        style="width: 180px"
        placeholder="选择用户状态"
      >
        <el-option
          v-for="item in status"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
      <el-date-picker
        v-model="dateTime"
        type="datetimerange"
        :shortcuts="shortcuts"
        style="width: 400px"
        range-separator="至"
        value-format="yyyy-MM-dd HH:mm:ss"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        onchange="timechange"
        align="right"
      >
      </el-date-picker>
      <el-button type="primary" plain @click="searchBtnClick">查询</el-button>
      <el-button type="success" plain @click="searchResetBtnClick"
        >重置
      </el-button>
      <!--      多选删除按钮-->
      <el-button
        type="danger"
        plain
        @click="multipleDelete"
        :disabled="multipleDeleteButton"
        >批量删除
      </el-button>
      <el-button type="success" plain @click="toggleSelection"
        >取消多选
      </el-button>
      <!--新建用户弹窗开始-->
      <el-dialog
        title="新建用户"
        :visible.sync="dialogFormVisible"
        :close-on-click-modal="false"
        :show-close="true"
      >
        <el-form :model="userform" :rules="rules" ref="userform">
          <el-form-item
            label="账号"
            :label-width="formLabelWidth"
            prop="syhUserNumber"
          >
            <el-input
              v-model="userform.syhUserNumber"
              autocomplete="off"
              style="width: 30%"
              :disabled="userform.syhUserId == null"
            ></el-input>
          </el-form-item>
          <el-form-item
            label="姓名"
            :label-width="formLabelWidth"
            prop="syhUserName"
          >
            <el-input
              v-model="userform.syhUserName"
              autocomplete="off"
              style="width: 30%"
            ></el-input>
          </el-form-item>
          <el-form-item
            label="密码"
            prop="syhPassword"
            :label-width="formLabelWidth"
          >
            <el-input
              type="password"
              v-model="userform.syhPassword"
              autocomplete="off"
              style="width: 30%"
            ></el-input>
          </el-form-item>
          <el-form-item
            label="确认密码"
            prop="userPassCheck"
            :label-width="formLabelWidth"
          >
            <el-input
              type="password"
              v-model="userform.userPassCheck"
              autocomplete="off"
              style="width: 30%"
            ></el-input>
          </el-form-item>
          <el-form-item
            label="性别"
            :label-width="formLabelWidth"
            prop="syhSex"
          >
            <el-select v-model="userform.syhSex" placeholder="请选择性别">
              <el-option label="男" value="1"></el-option>
              <el-option label="女" value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="电话"
            :label-width="formLabelWidth"
            prop="syhPhone"
          >
            <el-input
              v-model.number="userform.syhPhone"
              autocomplete="off"
              style="width: 30%"
            ></el-input>
          </el-form-item>
          <el-form-item
            label="邮箱"
            :label-width="formLabelWidth"
            prop="syhEmail"
          >
            <el-input
              v-model="userform.syhEmail"
              autocomplete="off"
              style="width: 30%"
            ></el-input>
          </el-form-item>
          <el-form-item
            label="状态"
            :label-width="formLabelWidth"
            prop="syhStatus"
          >
            <el-select v-model="userform.syhStatus" placeholder="请选择状态">
              <!--              <el-option label="区域一" value="shanghai"></el-option>-->
              <!--              <el-option label="区域二" value="beijing"></el-option>-->
              <el-option
                v-for="item in status"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="resetForm('userform')">取 消</el-button>
          <el-button type="primary" @click="submitForm('userform')"
            >确 定
          </el-button>
        </div>
      </el-dialog>
      <!--      新建用户弹窗end-->
    </div>
    <div style="margin: 20px 0;"></div>
    <!--user-->
    <el-table
      ref="multipleTable"
      border
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="40" :selectable="selectInit">
      </el-table-column>
      <el-table-column prop="date" label="序号" width="50">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="syhUserNumber" label="账号" width="130">
      </el-table-column>
      <el-table-column prop="syhUserName" label="姓名" width="130">
      </el-table-column>
      <el-table-column prop="syhSex" label="性别" width="60">
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.syhSex == 1">男</el-tag>
          <el-tag type="danger" v-if="scope.row.syhSex == 0">女</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="syhPhone" label="电话" width="130">
      </el-table-column>
      <el-table-column prop="syhEmail" label="邮箱"></el-table-column>
      <el-table-column prop="syhRegisterTime" label="创建时间">
      </el-table-column>
      <el-table-column prop="syhStatus" label="状态" width="65">
        <template slot-scope="scope">
          <!--          <el-tag   v-if="scope.row.userStatus==1">正常</el-tag>-->
          <el-switch
            v-model="scope.row.syhStatus == 1"
            :disabled="scope.row.syhUserName == 'admin'"
            @change="changUserStatus(scope.row)"
          ></el-switch>
          <!--          <el-tag type="danger"  v-if="scope.row.userStatus==0">已停用</el-tag>-->
        </template>
      </el-table-column>

      <el-table-column label="操作" width="240">
        <template slot-scope="scope">
          <el-button
            @click="updateClick(scope.row)"
            size="small"
            :disabled="scope.row.syhUserName == 'admin'"
            >编辑
          </el-button>
          <el-button
            type="danger"
            @click="deleteClick(scope.row)"
            size="small"
            :disabled="scope.row.syhUserName == 'admin'"
            >删除
          </el-button>
          <el-button
            type="warning"
            @click="assignClick(scope.row)"
            size="small"
            :disabled="scope.row.syhUserName == 'admin'"
            >分配角色
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分配角色对话框 -->
    <el-dialog
      center
      title="分配角色"
      :visible.sync="assignDialogVisible"
      width="49%"
    >
      <span>
        <template>
          <el-transfer
            filterable
            :titles="['未拥有', '已拥有']"
            :button-texts="['到左边', '到右边']"
            v-model="roleIds"
            :data="roles"
          ></el-transfer>
        </template>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="assignDialogVisible = false" class="el-icon-close"
          >取消分配</el-button
        >
        <el-button @click="doAssignRoles" type="primary">确定分配</el-button>
      </span>
    </el-dialog>

    <div class="bottom" style="margin-bottom: 0;margin-top: 10px">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryUserCondition.pageNum"
        :page-sizes="[8, 10, 20]"
        :page-size="queryUserCondition.pageSize"
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
  name: "UserIndex",
  data() {
    const validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.userform.userPassCheck !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    const validateCheckPass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.userform.syhPassword) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      //角色列表
      // currentPage:1,
      total: 0,
      // pageNum:1,
      roles: [],
      roleIds: [],
      searchByNumber: "",
      search: false,
      assignUserId: 0,
      //查询条件
      queryUserCondition: {
        loginName: "",
        userName: "",
        status: "",
        startTime: "",
        stopTime: "",
        pageNum: 1,
        pageSize: 10
      },

      roleList: [],

      value: "",
      input: "",
      //user列表
      tableData: [],
      dialogFormVisible: false,
      multipleSelection: [], //多选
      multipleDeleteButton: true,
      userform: {
        //用于添加用户
        syhUserId: "",
        syhUserNumber: "",
        syhPassword: "",
        syhUserName: "",
        syhPhone: "",
        syhEmail: "",
        syhSex: "",
        syhStatus: "",
        userPassCheck: ""
      },
      userformInit: {
        //初始化
        syhUserId: "",
        syhUserNumber: "",
        syhPassword: "",
        syhUserName: "",
        syhPhone: "",
        syhEmail: "",
        syhSex: "",
        syhStatus: ""
        // userHidden: ""
      },
      formLabelWidth: "120px",
      //时间选择
      shortcuts: [
        {
          text: "最近一周",
          value: (() => {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            return [start, end];
          })()
        },
        {
          text: "最近一个月",
          value: (() => {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            return [start, end];
          })()
        },
        {
          text: "最近三个月",
          value: (() => {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            return [start, end];
          })()
        }
      ],
      dateTime: "",
      //新增用户表单验证规则
      rules: {
        syhPassword: [
          {
            required: true,
            message: "请填写密码",
            trigger: "blur"
          },
          {
            min: 6,
            max: 20,
            message: "长度在 6 到 20 个字符",
            trigger: "blur"
          }
        ],
        userPassCheck: [
          {
            required: true,
            validator: validateCheckPass,
            trigger: "blur"
          }
        ],
        syhUserNumber: [
          {
            required: true,
            message: "请填写登录名",
            trigger: "blur"
          },
          {
            min: 6,
            max: 20,
            message: "长度在 6 到 20 个字符",
            trigger: "blur"
          }
        ],
        syhUserName: [
          {
            required: true,
            message: "请填写姓名",
            trigger: "blur"
          },
          {
            min: 2,
            max: 50,
            message: "长度在 2 到 50 个字符",
            trigger: "blur"
          }
        ],
        syhPhone: [
          {
            required: true,
            message: "请填写电话",
            trigger: "blur"
          },
          {
            type: "number",
            message: "电话必须为数字值"
          }
        ],
        syhEmail: [
          {
            required: true,
            message: "请填写邮箱",
            trigger: "blur"
          },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ],
        syhSex: [
          {
            required: true,
            message: "请填写性别",
            trigger: "blur"
          }
        ],
        // userHidden: [{ required: true, message: "请填写", trigger: "blur" }],
        syhStatus: [
          {
            required: true,
            message: "请选择用户状态",
            trigger: "blur"
          }
        ]
      },
      gender: [
        {
          value: "1",
          label: "男"
        },
        {
          value: "0",
          label: "女"
        }
      ],
      status: [
        {
          value: "1",
          label: "正常"
        },
        {
          value: "0",
          label: "已锁定"
        }
      ],
      assignDialogVisible: false
    };

    //return 外面
  },
  mounted() {
    this.$emit("getMessage", ["人员管理", "用户管理"]);
    this.load();
  },

  methods: {
    load() {
      this.$http
        .post("/user/selectUserByCondition", this.queryUserCondition)
        .then(res => {
          console.log("this.selectUserByCondition", res);
          this.tableData = res.data.data.result;
          this.total = res.data.data.total;
          this.pageNum = res.data.data.pages;
          console.log("this.tableData", res);
          console.log("this.tableData", this.tableData);
        });
    },
    timechange() {
      console.log("time change", this.dateTime);
    },
    selectInit(row, index) {
      if (row.syhUserId == 1) {
        //判断条件
        return false; //不可勾选
      } else {
        return true; //可勾选
      }
    },
    handleSizeChange: function(size) {
      //选择 一页展示多少数据时 调用的方法
      this.queryUserCondition.pageSize = size;
      console.log(size + "****");
      this.queryUserCondition.pageNum = 1;
      this.load();
    },
    handleCurrentChange: function(currentPage) {
      //点击页面项 的函数响应
      this.queryUserCondition.pageNum = currentPage;
      console.log(
        "this.currentPage = currentPage;",
        this.queryUserCondition.pageNum
      );
      this.load();
    },
    searchBtnClick() {
      //根据条件查询
      console.log("time change", this.dateTime);
      this.queryUserCondition.startTime = this.dateTime[0];
      this.queryUserCondition.stopTime = this.dateTime[1];
      this.queryUserCondition.pageNum = 1;

      console.log("queryUserCondition", this.queryUserCondition);
      // this.currentPage=1;
      this.search = true;

      this.$http
        .post("/user/selectUserByCondition", this.queryUserCondition)
        .then(res => {
          this.tableData = [];
          this.tableData = res.data.data.result;
          this.total = res.data.data.total;
          this.pageNum = res.data.data.pages;
          console.log("res", res);
          console.log("this.tableData", this.tableData);
        });
    },
    searchResetBtnClick() {
      //重置搜索条件
      this.queryUserCondition.startTime = "";
      this.queryUserCondition.stopTime = "";
      this.queryUserCondition.status = "";
      this.queryUserCondition.pageNum = 1;
      this.queryUserCondition.userName = "";
      this.queryUserCondition.loginName = "";
      this.dateTime = "";

      this.load();
    },
    async changUserStatus(row) {
      console.log("changUserStatus", row);
      let userStatus = "";
      if (row.syhStatus === 1) {
        userStatus = 0;
      } else {
        userStatus = 1;
      }
      const userId = row.syhUserId;
      this.$http
        .post("/user/updateUserStatusById", {
          userId: userId,
          userStatus: userStatus
        })
        .then(res => {
          if (res.data.code == 200) {
            //操作成功
            if (row.syhStatus == "1") {
              this.$message({
                type: "success",
                message: "用户状态禁用成功!"
              });
            } else {
              this.$message({
                type: "success",
                message: "用户状态启用成功!"
              });
            }
            row.syhStatus = userStatus;
          } else {
            this.$message({
              type: "error",
              message: "状态更改失败!"
            });
          }
          console.log("res", res);
        });
    },
    openDialog(userform) {
      //打开弹窗
      this.dialogFormVisible = true;
      this.$refs[userform].resetFields();
    },
    //提交表单
    submitForm(userform) {
      this.$refs[userform].validate(valid => {
        //判断
        if (valid) {
          if (this.userform != null) {
            //判断是否为更新操作
            if (
              this.userform.syhUserId == null ||
              this.userform.syhUserId == ""
            ) {
              //此处id为空，为新增操作
              console.log(
                'this.userform.userId==null||this.userform.userId==""',
                this.userform
              );
              this.$http.post("/user/insertUser", this.userform).then(res => {
                console.log("res", res);
                if (res.data.code == 200) {
                  this.$refs[userform].resetFields();
                  this.open("添加用户", "添加成功");
                } else {
                  this.$refs[userform].resetFields();
                  this.open("添加用户", res.data.message);
                }
              });
            } else {
              //此处id不为空，为更新操作
              console.log("userform!=null", this.userform);
              this.$http
                .post("/user/updateUserById", this.userform)
                .then(res => {
                  console.log("res", res);
                  if (res.data.code == 200) {
                    this.$refs[userform].resetFields();
                    this.open("编辑用户", "修改成功");
                  }
                });
            }
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(userform) {
      console.log("userformInit", this.userformInit);

      this.userform = { ...this.userformInit };
      console.log("resetForm", this.userform);
      this.$refs[userform].resetFields();
      this.dialogFormVisible = false;
    },
    handleClose() {
      console.log("handleClose");
      this.$nextTick(() => {
        this.$refs.userform.resetFields(); // CheckedArr 一个选中的数组element Ui的东西，关键是看$nextTick的用法
      });
    },
    open(title, message) {
      this.$alert(message, title, {
        confirmButtonText: "确定",
        callback: action => {
          this.load();
          this.dialogFormVisible = false;
          // this.$message({
          //   type: 'info',
          //   message: `action: ${ action }`
          // });
        }
      });
    },
    toggleSelection() {
      this.$refs.multipleTable.clearSelection();
      this.multipleDeleteButton = true;
    },
    handleSelectionChange(val) {
      //多选
      if (val.length > 0) {
        this.multipleDeleteButton = false;
      } else {
        this.multipleDeleteButton = true;
      }
      this.multipleSelection = val;
      console.log("multipleSelection", this.multipleSelection);
      console.log("val", val);
    },
    multipleDelete() {
      //批量删除
      this.$confirm("此操作将永久删除所选用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http
            .post("/user/deleteUserByIdMultiple", {
              userList: this.multipleSelection
            })
            .then(res => {
              console.log("res", res);
              if (res.data.code == 200) {
                //成功删除弹窗
                this.$message({
                  type: "success",
                  message: "删除成功!"
                });
                this.load();
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
    updateClick(row) {
      console.log(row);
      this.userform = { ...row };
      console.log("updateClick", this.userform);
      this.dialogFormVisible = true;
    },
    assignClick(row) {
      //分配角色按钮
      this.assignDialogVisible = true;
      this.assignUserId = row.syhUserId;
      this.$http
        .get("/role/selectAllRoles", { params: { userId: this.assignUserId } })
        .then(res => {
          this.roleIds = res.data.data.roleIds;
          this.roles = res.data.data.roles;
          console.log("res", res);
        });
    },
    doAssignRoles() {
      console.log("this.roleIds", this.roleIds);
      this.$http
        .post("/userRole/doAssignRoles", {
          userId: this.assignUserId,
          roleIds: this.roleIds
        })
        .then(res => {
          if (res.data.code == 200) {
            //操作成功
            this.$message({
              type: "success",
              message: "分配成功!"
            });
          } else {
            this.$message({
              type: "error",
              message: "分配失败!"
            });
          }
          this.assignDialogVisible = false;
          this.assignUserId = 0;
          this.roleIds = [];
          console.log("res", res);
        });
    },
    deleteClick(row) {
      //删除按钮
      var id = row.syhUserId;
      this.$confirm("此操作将永久删除该用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http
            .get("/user/deleteUserById", { params: { userId: id } })
            .then(res => {
              console.log("res", res);
              if (res.data.code == 200) {
                //成功删除弹窗
                this.$message({
                  type: "success",
                  message: "删除成功!"
                });
                this.load();
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
