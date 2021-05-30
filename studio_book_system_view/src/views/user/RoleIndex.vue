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
        placeholder="请输入角色名"
        v-model="queryRoleCondition.roleName"
        prefix-icon="el-icon-search"
        type="text"
        clearable
      >
      </el-input>

      <el-date-picker
        v-model="dateTime"
        type="datetime"
        value-format="yyyy-MM-dd HH:mm:ss"
        placeholder="选择日期时间"
      >
      </el-date-picker>
      <el-button type="primary" plain @click="searchBtnClick">查询</el-button>
      <el-button type="success" plain @click="searchResetBtnClick"
        >重置</el-button
      >
      <!--      多选删除按钮-->
      <el-button
        type="danger"
        plain
        @click="multipleDelete"
        :disabled="multipleDeleteButton"
        >批量删除</el-button
      >
      <el-button type="success" plain @click="toggleSelection"
        >取消多选</el-button
      >

      <el-dialog
        title="新建角色"
        :visible.sync="dialogFormVisible"
        :close-on-click-modal="false"
        :show-close="true"
      >
        <el-form
          :model="roleForm"
          :rules="rules"
          ref="roleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="角色名称" prop="syhRoleName">
            <el-input v-model="roleForm.syhRoleName"></el-input>
          </el-form-item>

          <!--          <el-form-item label="角色类型" prop="lxRoleType">-->
          <!--            <el-input v-model="roleForm.lxRoleType"></el-input>-->
          <!--          </el-form-item>-->
          <el-form-item label="角色描述" prop="syhRoleDetail">
            <el-input
              type="textarea"
              v-model="roleForm.syhRoleDetail"
            ></el-input>
          </el-form-item>
          <el-form-item label="角色状态" prop="syhRoleStatus">
            <el-select
              v-model="roleForm.syhRoleStatus"
              placeholder="请选择角色状态"
            >
              <el-option
                v-for="item in status"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('roleForm')"
              >保存</el-button
            >
            <el-button @click="resetForm('roleForm')">取消</el-button>
          </el-form-item>
        </el-form>
        <!--        <div slot="footer" class="dialog-footer">-->
        <!--          <el-button @click="dialogFormVisible = false">取 消</el-button>-->
        <!--          <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>-->
        <!--        </div>-->
      </el-dialog>
      <!--      新建角色弹窗end-->
      <!--      角色授权弹框开始-->

      <el-dialog
        title="角色授权"
        :visible.sync="grantDialogVisible"
        width="30%"
        center
      >
        <span>
          <el-tree
            ref="tree"
            :data="menuList"
            @node-change="handleNodeClick"
            show-checkbox
            node-key="syhMenuId"
            default-expand-all
            :props="{ children: 'children', label: 'syhMenuName' }"
          >
          </el-tree>
        </span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="grantDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitMenuForm">确 定</el-button>
        </span>
      </el-dialog>
      <!--  角色授权弹框end    -->
    </div>
    <div style="margin: 20px 0;"></div>
    <!--table-->
    <el-table
      ref="multipleTable"
      border
      :data="roleList"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" :selectable="selectInit">
      </el-table-column>
      <el-table-column prop="date" label="序号" width="80">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="syhRoleName" label="角色名称" width="180">
      </el-table-column>
      <el-table-column prop="syhRoleDetail" label="角色描述"> </el-table-column>
      <el-table-column prop="syhRoleCreateTime" label="创建时间">
      </el-table-column>
      <el-table-column prop="syhRoleStatus" label="角色状态">
        <template slot-scope="scope">
          <!--          <el-tag type="success" v-if="scope.row.syhRoleStatus==1">已启用</el-tag>-->
          <!--          <el-tag type="danger" v-if="scope.row.syhRoleStatus==0">已停用</el-tag>-->
          <el-switch
            v-model="scope.row.syhRoleStatus === 1"
            :disabled="scope.row.syhRoleName === 'admin'"
            @change="changeRoleStatus(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="220">
        <template slot-scope="scope">
          <el-button
            @click="updateClick(scope.row)"
            size="small"
            :disabled="scope.row.syhRoleName == 'admin'"
            >编辑</el-button
          >
          <el-button
            @click="handleRoleClick(scope.row)"
            type="warning"
            size="small"
            >授权</el-button
          >
          <el-button
            @click="handleDeleteClick(scope.row)"
            type="danger"
            size="small"
            :disabled="scope.row.syhRoleName == 'admin'"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="bottom" style="margin-bottom: 0;margin-top: 10px">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryRoleCondition.pageNum"
        :page-sizes="[8, 10, 20]"
        :page-size="queryRoleCondition.pageSize"
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
  data() {
    return {
      roleList: [
        {
          syhRoleId: "",
          syhRoleName: "",
          syhRoleDetail: "",
          syhRoleCreateTime: "",
          syhRoleStatus: ""
        }
      ],
      //查询条件
      queryRoleCondition: {
        roleName: "",
        roleCreateTime: "",
        pageNum: 1,
        pageSize: 10
      },
      roleForm: {
        syhRoleId: "",
        syhRoleName: "",
        syhRoleDetail: "",
        syhRoleCreateTime: "",
        syhRoleStatus: ""
      },
      roleFormInit: {
        syhRoleId: "",
        syhRoleName: "",
        syhRoleDetail: "",
        syhRoleCreateTime: "",
        syhRoleStatus: ""
      },
      RoleMenuForm: {
        //角色权限表单
        id: "",
        lxMenuId: [],
        lxRoleId: ""
      },

      options: [
        {
          value: "选项1",
          label: "黄金糕"
        },
        {
          value: "选项2",
          label: "双皮奶"
        }
      ],
      total: 0,
      dateTime: "",
      value: "",
      input: "",
      //批量删除按钮
      multipleDeleteButton: true,
      multipleSelection: [], //多选
      // 默认选中选中节点Id值的数组
      centerDialogVisible: false, //授权弹框
      grantDialogVisible: false, //授权弹出框
      menuIds: [],
      grantRoleId: "",

      defaultProps: {
        children: "children",
        label: "menuName"
      },
      rules: {
        //表单验证规则
        syhRoleName: [
          { required: true, message: "请输入角色名称", trigger: "blur" },
          { min: 1, max: 50, message: "长度在 1 到 50个字符", trigger: "blur" }
        ],
        syhRoleDetail: [
          { required: true, message: "请输入角色描述", trigger: "blur" }
        ],
        // roleCreateTime: [
        //   {required: true, message: '请选择角色状态', trigger: 'blur'}
        // ],
        syhRoleStatus: [
          { required: true, message: "请选择角色状态", trigger: "blur" }
        ]
      },
      status: [
        {
          value: "1",
          label: "已启用"
        },
        {
          value: "0",
          label: "已锁定"
        }
      ],
      dialogFormVisible: false,

      formLabelWidth: "120px",
      menuList: [
        // {
        //   syhMenuId: 100,
        //   syhMenuName: "系统管理",
        //   syhMenuType: "1",
        //   syhMenuParentId: null,
        //   syhMenuStatus: 1,
        //   syhMenuUrl: null,
        //   syhMenuOpcode: null,
        //   syhMenuDetail: "系统管理",
        //   syhMenuQueue: 1,
        //   children: [
        //     {
        //       syhMenuId: 1,
        //       syhMenuName: "人员管理",
        //       syhMenuType: "1",
        //       syhMenuParentId: 100,
        //       syhMenuStatus: 1,
        //       syhMenuUrl: "/user/userindex",
        //       syhMenuOpcode: null,
        //       syhMenuDetail: "人员管理",
        //       syhMenuQueue: 1,
        //       children: null
        //     }
        //   ]
        // }
      ]
    };
  },
  mounted() {
    this.$emit("getMessage", ["用户管理", "角色管理"]);
    this.load();
  },

  methods: {
    load() {
      this.$http
        .post("/role/selectRoleByCondition", this.queryRoleCondition)
        .then(res => {
          this.roleList = res.data.data.result;
          this.total = res.data.data.total;
          console.log("res", res);
          console.log("this.roleList", this.roleList);
        });
      // this.$http.post('/menu/node').then(res => {
      //   this.menuList = res.data.data;
      //   console.log("res", res)
      //   console.log("this.menuList", this.menuList)
      // });
    }, //load结束
    selectInit(row, index) {
      if (row.syhRoleId == 1) {
        //判断条件
        return false; //不可勾选
      } else {
        return true; //可勾选
      }
    },
    handleSizeChange: function(size) {
      //选择 一页展示多少数据时 调用的方法
      this.queryRoleCondition.pageSize = size;
      console.log(size + "****");
      this.queryRoleCondition.pageNum = 1;
      this.load();
    },
    handleCurrentChange: function(currentPage) {
      //点击页面项 的函数响应
      this.queryRoleCondition.pageNum = currentPage;
      console.log(
        "this.currentPage = currentPage;",
        this.queryRoleCondition.pageNum
      );
      this.load();
    },
    searchBtnClick() {
      //根据条件查询
      console.log("time change", this.dateTime);
      this.queryRoleCondition.roleCreateTime = this.dateTime;
      this.queryRoleCondition.pageNum = 1;

      console.log("queryRoleCondition", this.queryRoleCondition);
      this.$http
        .post("/role/selectRoleByCondition", this.queryRoleCondition)
        .then(res => {
          this.roleList = [];
          this.roleList = res.data.data.result;
          this.total = res.data.data.total;
          this.pageNum = res.data.data.pages;
          console.log("res", res);
          console.log("this.tableData", this.roleList);
        });
    },
    searchResetBtnClick() {
      //重置搜索条件
      this.queryRoleCondition.pageNum = 1;
      this.queryRoleCondition.roleName = "";
      this.queryRoleCondition.roleCreateTime = "";

      this.load();
    },
    changeRoleStatus(row) {
      console.log("changeRoleStatus", row);
      let roleHidden = null;
      if (row.syhRoleStatus == 1) {
        roleHidden = 0;
      } else {
        roleHidden = 1;
      }
      const roleId = row.syhRoleId;
      this.$http
        .post("/role/updateRoleStatusById", {
          roleId: roleId,
          roleHidden: roleHidden
        })
        .then(res => {
          if (res.data.code == 200) {
            //操作成功
            if (row.syhRoleStatus == 1) {
              this.$message({
                type: "success",
                message: "角色状态禁用成功!"
              });
            } else {
              this.$message({
                type: "success",
                message: "角色状态启用成功!"
              });
            }
            row.syhRoleStatus = roleHidden;
          } else {
            this.$message({
              type: "error",
              message: "状态更改失败!"
            });
          }
          console.log("res", res);
        });
    },
    handleNodeClick() {
      //获取菜单权限
      let res = this.$refs.tree.getCheckedNodes();
      console.log(res);
      this.RoleMenuForm.lxMenuList = res;
    },
    closeDialog() {
      this.roleForm = { ...this.roleFormInit };
      this.dialogFormVisible = false;
    },
    updateClick(rows) {
      console.log("updateClick", rows);
      this.roleForm = { ...rows };
      this.roleForm.syhRoleStatus = "";
      console.log("updateClick,roleForm", this.roleForm);
      this.dialogFormVisible = true;
    },
    submitForm(roleForm) {
      //添加/更新角色
      this.$refs[roleForm].validate(valid => {
        if (valid) {
          console.log("roleForm", this.roleForm);
          if (
            this.roleForm.syhRoleId == null ||
            this.roleForm.syhRoleId == ""
          ) {
            //此处id为空，为新增操作
            this.$http.post("/role/insertRole", this.roleForm).then(res => {
              console.log("res", res);
              this.load();
              if (res.data.code == 200) {
                this.$refs[roleForm].resetFields();
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
            this.$http.post("/role/updateRoleById", this.roleForm).then(res => {
              console.log("res", res);
              this.load();

              if (res.data.code == 200) {
                this.$refs[roleForm].resetFields();
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
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.closeDialog();
    },
    handleClose() {
      console.log("handleClose");
      this.$nextTick(() => {
        console.log("handleClose,resetFields");
        this.$refs.roleForm.resetFields(); // CheckedArr 一个选中的数组element Ui的东西，关键是看$nextTick的用法
      });
    },
    handleRoleClick(rows) {
      //点击打开授权窗口
      this.grantDialogVisible = true;
      this.menuList = [];
      console.log("rows", rows);
      this.grantRoleId = rows.syhRoleId;
      this.$http
        .post("/roleMenu/selectByRoleId", this.grantRoleId)
        .then(res => {
          console.log("res", res);
          if (res.data.code == 200) {
            this.$message({
              message: "查询成功",
              type: "success"
            });
            this.menuList = res.data.data.menuList;
            //默认选中的树的数据
            let that = this;
            setTimeout(function() {
              res.data.data.menuIds.forEach(value => {
                that.$refs.tree.setChecked(value, true, false);
              });
            }, 100);
          } else {
            this.$message.error(res.data.message);
          }
        });
    },
    submitMenuForm() {
      //提交已选择的菜单列表
      const mids = [].concat(
        this.$refs.tree.getCheckedKeys(),
        this.$refs.tree.getHalfCheckedKeys()
      );
      this.$http
        .post("/roleMenu/insertRoleMenu", {
          roleId: this.grantRoleId,
          menuIds: mids
        })
        .then(res => {
          console.log("/rolemenu/addOrUpdate", res);
          if (res.data.code == 200) {
            this.$message({
              message: "操作成功",
              type: "success"
            });
          } else {
            this.$message.error(res.data.message);
          }
          this.grantDialogVisible = false;
        });
    },
    handleDeleteClick(rows) {
      //删除角色
      console.log("delete rows", rows);
      var id = rows.syhRoleId;
      this.$confirm("此操作将永久删除该角色, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          console.log("postid", id);
          this.$http
            .get("/role/deleteRoleById", { params: { roleId: id } })
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
    },
    multipleDelete() {
      //批量删除
      this.$confirm("此操作将永久删除所选角色, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http
            .post("/role/deleteRoleByIdMultiple", {
              roleList: this.multipleSelection
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

    handleSubCat(value) {
      // 获取value值
      console.log(value);
      // 获取label 值
      console.log(this.$refs["menuSelected"].getCheckedNodes()[0].label);
    }
  }
};
</script>

<style scoped></style>
