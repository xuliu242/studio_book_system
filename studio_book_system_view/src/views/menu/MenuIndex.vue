<template>
  <div>
    <!-- 节点过滤 -->
    <el-row>
      <el-col :span="16">
        <el-button type="primary" icon="el-icon-plus" @click="openParentAdd"
          >父级</el-button
        >
        <el-input
          style="width: 220px;margin-left: 20px;margin-right: 20px"
          placeholder="请输入菜单名"
          v-model="queryMenuCondition.menuName"
          prefix-icon="el-icon-search"
          type="text"
          clearable
        >
        </el-input>
        <el-select
          v-model="queryMenuCondition.menuStatus"
          placeholder="选择菜单状态"
        >
          <el-option
            v-for="item in status"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
        <el-button type="primary" plain @click="searchBtnClick">查询</el-button>
        <el-button type="success" plain @click="searchResetBtnClick"
          >重置</el-button
        >
      </el-col>
    </el-row>

    <p>菜单权限树</p>
    <el-tree
      :data="menuList"
      accordion
      :auto-expand-parent="false"
      node-key="id"
      show-checkbox
      :default-expanded-keys="[]"
      :expand-on-click-node="false"
      :render-content="renderContent"
      :props="defaultProps"
      highlight-current
      ref="tree"
    ></el-tree>

    <!-- 节点新增弹出框 -->
    <el-dialog
      :title="addTitle"
      :visible.sync="addDialogVisible"
      @close="closeDialog"
      width="50%"
    >
      <span>
        <el-form
          size="mini"
          ref="menuForm"
          :model="menuForm"
          label-width="80px"
          :rules="rules"
        >
          <el-form-item label="菜单名称" prop="syhMenuName">
            <el-input v-model="menuForm.syhMenuName"></el-input>
          </el-form-item>
          <el-form-item label="URL" prop="syhMenuUrl">
            <el-input v-model="menuForm.syhMenuUrl"></el-input>
          </el-form-item>
          <el-form-item label="编码" prop="syhMenuOpcode">
            <el-input v-model="menuForm.syhMenuOpcode"></el-input>
          </el-form-item>
          <el-form-item label="描述" prop="syhMenuDetail">
            <el-input v-model="menuForm.syhMenuDetail"></el-input>
          </el-form-item>
          <el-form-item label="是否可用" prop="syhMenuStatus">
            <template>
              <el-radio v-model="menuForm.syhMenuStatus" label="1"
                >可用</el-radio
              >
              <el-radio v-model="menuForm.syhMenuStatus" label="0"
                >禁用</el-radio
              >
            </template>
          </el-form-item>
          <!--          <el-form-item label="是否隐藏" prop="menuHidden">-->
          <!--            <template>-->
          <!--              <el-radio v-model="menuForm.menuHidden" label="1">显示</el-radio>-->
          <!--              <el-radio v-model="menuForm.menuHidden" label="0">隐藏</el-radio>-->
          <!--            </template>-->
          <!--          </el-form-item>-->
          <el-form-item label="排序" prop="syhMenuQueue">
            <el-input-number
              v-model="menuForm.syhMenuQueue"
              :min="1"
              :max="10"
              label="描述文字"
            ></el-input-number>
          </el-form-item>
          <el-form-item label="类型" prop="syhMenuType">
            <template>
              <el-radio v-model="menuForm.syhMenuType" label="1">菜单</el-radio>
              <el-radio v-model="menuForm.syhMenuType" label="2">按钮</el-radio>
            </template>
          </el-form-item>
        </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('menuForm')"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "MenuIndex",
  data() {
    const data = [];
    return {
      menuList: [],
      addDialogVisible: false,
      editDialogVisible: false,
      editTitle: "",
      addTitle: "",
      queryMenuCondition: {
        menuName: "",
        menuStatus: "",
        pageSize: 10,
        pageNum: 1,
        sort: ""
      },
      menuForm: {
        syhMenuId: "",
        syhMenuType: "",
        syhMenuName: "",
        syhMenuParentId: "",
        syhMenuStatus: "",
        syhMenuQueue: "",
        syhMenuUrl: "",
        syhMenuOpcode: "",
        syhMenuDetail: ""
      },
      rules: {
        //表单验证规则
        syhMenuType: [
          { required: true, message: "请选择菜单类型", trigger: "blur" }
          // {min: 1, max: 3, message: '长度在 1 到 3 个字符', trigger: 'blur'}
        ],
        syhMenuName: [
          { required: true, message: "请输入菜单名", trigger: "blur" },
          { min: 1, max: 20, message: "长度在 1 到 20 个字符", trigger: "blur" }
        ],
        // menuParentId: [
        //   {required: true, message: '请选择父级菜单', trigger: 'blur'}
        // ],
        // menuUrl: [
        //   {required: true, message: '请输入菜单链接', trigger: 'blur'}
        // ],
        syhMenuOpcode: [
          { required: true, message: "请输入菜单操作码", trigger: "blur" }
        ],
        syhMenuStatus: [
          { required: true, message: "请选择菜单状态", trigger: "blur" }
        ],
        syhMenuQueue: [
          { required: true, message: "请输入排序", trigger: "blur" }
        ],
        syhMenuDetail: [
          { required: true, message: "请输入菜单描述", trigger: "blur" },
          {
            min: 1,
            max: 500,
            message: "长度在 1 到 500 个字符",
            trigger: "blur"
          }
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
      defaultProps: {
        children: "children",
        label: "syhMenuName"
      }
    };
  },
  mounted() {
    this.$emit("getMessage", ["用户管理", "菜单管理"]);
    this.load();
  },
  methods: {
    load() {
      this.$http
        .post("/menu/selectMenuByCondition", this.queryMenuCondition)
        .then(res => {
          this.menuList = res.data.data.result;
          this.total = res.data.data.total;
        });
    },
    //添加最高父级节点
    openParentAdd() {
      console.log("openParentAdd", this.addDialogVisible);
      this.addTitle = "添加第一父级";
      this.addDialogVisible = true;
      this.menuForm.syhMenuParentId = null;
    },
    //点击编辑节点
    async edit(data) {
      this.addTitle = "编辑：【" + data.syhMenuName + "】";
      console.log("data.menuId", data);
      const { data: res } = await this.$http.get("/menu/selectByMenuId", {
        params: { menuId: data.syhMenuId }
      });
      if (res.success) {
        this.menuForm = res.data.result;
        // this.menuForm.menuHidden='';
        this.addDialogVisible = true;
      } else {
        return this.$message.error("节点编辑失败:" + res.data.message);
      }
    },
    //关闭添加
    addClose() {
      this.$refs.addFormRef.clearValidate();
      this.menuForm = {};
    },
    editClose() {
      this.$refs.editFormRef.clearValidate();
      this.menuForm = {};
    },
    closeDialog() {
      console.log("closeDialog", this.menuForm);
      this.menuForm = {};
      this.editDialogVisible = false;
      this.addDialogVisible = false;
    },
    //打开添加框
    openAdd(data) {
      this.addTitle = "添加节点 ：当前【" + data.syhMenuName + "】";
      this.addDialogVisible = true;
      this.menuForm.syhMenuParentId = data.syhMenuId;
    },
    submitForm(menuForm) {
      //添加/更新菜单
      this.$refs[menuForm].validate(valid => {
        if (valid) {
          console.log("menuForm", this.menuForm);
          if (
            this.menuForm.syhMenuId == null ||
            this.menuForm.syhMenuId == ""
          ) {
            //此处id为空，为新增操作
            this.$http.post("/menu/insertMenu", this.menuForm).then(res => {
              console.log("res", res);
              this.load();

              if (res.data.code == 200) {
                this.$refs[menuForm].resetFields();
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
            this.$http.post("/menu/updateMenuById", this.menuForm).then(res => {
              console.log("res", res);
              this.load();

              if (res.data.code == 200) {
                this.$refs[menuForm].resetFields();
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
    handleDeleteClick(rows) {
      //删除菜单节点
      console.log("delete rows", rows);
      var id = rows;
      this.$confirm("此操作将永久删除该菜单和相应的子菜单, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http
            .get("/menu/deleteMenuById", { params: { menuId: id } })
            .then(res => {
              console.log("res", res);

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
              this.load();
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    searchBtnClick() {
      //根据条件查询
      this.queryMenuCondition.pageNum = 1;

      console.log("queryMenuCondition", this.queryMenuCondition);
      // console.log('searchBtnClisk', this.searchByRole, this.searchByNumber)
      // this.currentPage=1;
      // this.search = true

      this.$http
        .post("/menu/selectMenuByCondition", this.queryMenuCondition)
        .then(res => {
          this.menuList = [];
          this.menuList = res.data.data.result;
          console.log("res", res);
          console.log("this.tableData", this.menuList);
        });
    },
    searchResetBtnClick() {
      //重置搜索条件
      this.queryMenuCondition.pageNum = 1;
      this.queryMenuCondition.menuName = "";
      this.queryMenuCondition.menuStatus = "";

      this.load();
    },
    renderContent(h, { node, data, store }) {
      data = this.menuList;
      return (
        <span class="custom-tree-node">
          <span>
            <i class={data.icon}></i>&nbsp;&nbsp;&nbsp;{node.label}
            {node.data.syhMenuType == 1 || node.data.syhMenuType == 0 ? (
              <el-tag style="margin-left:20px;" effect="plain" size="mini">
                菜单
              </el-tag>
            ) : (
              <el-tag
                style="margin-left:20px;"
                type="warning"
                effect="plain"
                size="mini"
              >
                权限 [{node.data.syhMenuOpcode}]
              </el-tag>
            )}
          </span>
          <span>
            <el-button
              size="mini"
              type="text"
              on-click={() => {
                this.edit(node.data);
                return false;
              }}
            >
              <i class="el-icon-edit"></i>&nbsp;编辑
            </el-button>
            <el-button
              size="mini"
              type="text"
              on-click={() => {
                this.openAdd(node.data);
              }}
            >
              <i class="el-icon-plus"></i>&nbsp;增加
            </el-button>

            <el-button
              size="mini"
              type="text"
              on-click={() => this.handleDeleteClick(node.data.syhMenuId)}
            >
              <i class="el-icon-delete"></i>&nbsp;删除
            </el-button>
          </span>
          {node.data.syhMenuStatus === 1 ? (
            <el-tag
              style="margin-left:20px;"
              type="success"
              effect="plain"
              size="mini"
            >
              已启用
            </el-tag>
          ) : (
            <el-tag
              style="margin-left:20px;"
              type="warning"
              effect="plain"
              size="mini"
            >
              已禁用
            </el-tag>
          )}
        </span>
      );
    }
  }
};
</script>

<style scoped></style>
