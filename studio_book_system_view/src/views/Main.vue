<template>
  <el-container class="main_container">
    <el-header>
      <!--      Logo-->
      <div class="left_box">
        <img src="../assets/img/logo.png" />
        <span>开放工作室预订管理系统</span>
      </div>

      <div class="right_box">
        <el-dropdown @command="handleCommand">
          <!-- <img src="../assets/img/qimeng.jpg" /> -->
          <img src="../assets/img/login.jpg" />
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item icon="el-icon-house" command="home"
              >首页</el-dropdown-item
            >
            <el-dropdown-item icon="el-icon-setting" command="editPwd"
              >修改密码</el-dropdown-item
            >

            <el-dropdown-item icon="el-icon-switch-button" command="logout"
              >退出</el-dropdown-item
            >
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>
      <el-dialog
        title="修改密码"
        :visible.sync="dialogFormVisible"
        :close-on-click-modal="false"
        :show-close="true"
      >
        <el-form
          :model="ruleForm"
          status-icon
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="旧密码" prop="oldPwd">
            <el-input
              type="password"
              v-model="ruleForm.oldPwd"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="pass">
            <el-input
              type="password"
              v-model="ruleForm.pass"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPass">
            <el-input
              type="password"
              v-model="ruleForm.checkPass"
              autocomplete="off"
            ></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')"
              >提交</el-button
            >
            <el-button @click="resetForm('ruleForm')">取消</el-button>
          </el-form-item>
        </el-form>
        <!--        <div slot="footer" class="dialog-footer">-->
        <!--          <el-button @click="dialogFormVisible = false">取 消</el-button>-->
        <!--          <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>-->
        <!--        </div>-->
      </el-dialog>
      <el-aside width="200px">
        <el-menu
          v-for="item in menuList"
          :key="item.syhMenuId"
          class="el-menu-vertical-demo"
          mode="vertical"
          :router="true"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
        >
          <div v-if="item.children.length == 0">
            <el-menu-item
              :index="item.syhMenuUrl"
              :disabled="item.syhMenuStatus === 0"
            >
              <!--                <router-link :to="{path:itemChildren.lxLink}">{{ itemChildren.lxMenuName }}</router-link>-->
              {{ item.syhMenuName }}
            </el-menu-item>
          </div>

          <div v-if="item.children.length > 0">
            <!--            <el-menu-item :index="item.menuUrl" :disabled="item.menuStatus=='0'">-->

            <!--            </el-menu-item>-->
            <el-submenu
              :index="item.syhMenuQueue"
              :disabled="item.syhMenuStatus === 0"
            >
              <template slot="title">{{ item.syhMenuDetail }}</template>
              <el-menu-item-group
                v-for="(itemChildren, index) in item.children"
                :key="index"
              >
                <el-menu-item
                  :index="itemChildren.syhMenuUrl"
                  :disabled="itemChildren.syhMenuStatus === 0"
                >
                  <!--                <router-link :to="{path:itemChildren.lxLink}">{{ itemChildren.lxMenuName }}</router-link>-->
                  {{ itemChildren.syhMenuName }}
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </div>
        </el-menu>
      </el-aside>

      <el-main>
        <!--面包屑-->
        <el-breadcrumb
          separator-class="el-icon-arrow-right"
          style="height: 30px"
        >
          <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
          <div v-if="faMenuName != '首页'">
            <el-breadcrumb-item>{{ faMenuName }}</el-breadcrumb-item>
            <el-breadcrumb-item>{{ subMenuName }}</el-breadcrumb-item>
          </div>
        </el-breadcrumb>
        <!--卡片-->
        <el-card class="box-card">
          <div v-if="faMenuName == ''">
            <Home></Home>
          </div>
          <router-view @getMessage="showMsg">></router-view>
        </el-card>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Home from "@/views/Home";
import { selectMenuByUserId } from "@/api/api";
export default {
  name: "Main",
  components: { Home },
  data() {
    var checkAge = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入旧密码"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      dialogFormVisible: false,
      faMenuName: "",
      subMenuName: "",
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
      ],
      ruleForm: {
        pass: "",
        checkPass: "",
        oldPwd: ""
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: "blur" },
          { min: 6, max: 20, message: "长度在 6 到 20 个字符", trigger: "blur" }
        ],
        checkPass: [
          { validator: validatePass2, trigger: "blur" },
          { min: 6, max: 20, message: "长度在 6 到 20 个字符", trigger: "blur" }
        ],
        oldPwd: [
          { validator: checkAge, trigger: "blur" },
          { min: 6, max: 20, message: "长度在 6 到 20 个字符", trigger: "blur" }
        ]
      }
    };
  },
  mounted() {
    var userId = sessionStorage.getItem("UserLoginName");
    selectMenuByUserId({ userId: userId }).then(res => {
      this.menuList = res.data.data.result;
      console.log("res", res);
      console.log("this.menuList", this.menuList);
    });
  },
  methods: {
    showMsg(val) {
      this.faMenuName = val[0];
      this.subMenuName = val[1];
    },
    handleCommand(command) {
      if (command == "logout") {
        this.logout();
      } else if (command == "editPwd") {
        this.updatePassword();
      }
    },
    updatePassword() {
      this.dialogFormVisible = true;
    },
    submitForm(ruleForm) {
      this.$refs[ruleForm].validate(valid => {
        if (valid) {
          var lxUserId = sessionStorage.getItem("LoginName");
          this.$http
            .post("/user/updatePassword", {
              oldPwd: this.ruleForm.oldPwd,
              newPwd: this.ruleForm.pass,
              lxUserId: lxUserId
            })
            .then(res => {
              console.log("/user/updatePassword", res);
              var message = res.data.message;
              if (res.data.state == 200) {
                //成功删除弹窗
                this.$message({
                  type: "success",
                  message: message
                });
                this.messageNotify();
                sessionStorage.removeItem("UserLoginName");
                LocalStorage.clearAll();
                this.$router.push("/login");
              } else {
                this.$message.error(message);
              }
              this.$nextTick(() => {
                console.log("handleClose,resetFields");
                this.$refs.ruleForm.resetFields(); // CheckedArr 一个选中的数组element Ui的东西，关键是看$nextTick的用法
                this.dialogFormVisible = false;
              });
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(ruleForm) {
      this.$refs[ruleForm].resetFields();
      this.dialogFormVisible = false;
    },
    messageNotify() {
      //消息通知
      this.$notify({
        title: "提示",
        message: "密码已修改，请重新登录",
        type: "warning",
        duration: 4500
      });
    },
    logout() {
      console.log("loginout");
      this.$confirm("该操作将退出账号, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          LocalStorage.clearAll();
          sessionStorage.removeItem("UserLoginName");
          this.$router.push("/login");
          this.$message({
            type: "success",
            message: "退出成功!"
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消"
          });
        });
    }
  }
};
</script>

<style lang="less" scoped>
/*设置整个容器的高度*/
.main_container {
  height: 100%;
}

/*头部布局*/
.el-header {
  background-color: rgba(29, 27, 27, 0.87);
  color: #333;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  color: #ffffff;
  align-items: center;
  font-size: 20px;

  /*左边logo和标题*/

  .left_box {
    display: flex;
    align-items: center;

    /*logo*/

    img {
      width: 70px;
      height: 70px;
      margin: 0px 0px 10px 15px;
    }

    /*标题*/

    span {
      margin-left: 15px;
    }
  }

  /*右边的登录头像*/

  .right_box {
    .el-dropdown > img {
      width: 60px;
      height: 60px;
      border-radius: 50%;
      background-color: #ffffff;
      margin: 0px 15px 0px 0px;
      background-size: contain;
    }
  }
}

/*侧边栏*/
.el-aside {
  background-color: rgba(29, 27, 27, 0.87);
  color: #333;

  .el-menu {
    border-right: none;
  }

  /*展开/收起*/

  .toggle_box {
    background-color: chartreuse;
    font-size: 15px;
    font-weight: bold;
    line-height: 24px;
    color: #ffffff;
    letter-spacing: 0.2em;
    text-align: center;
    cursor: pointer;
  }
}

/*内容主体*/
.el-main {
  background-color: #e9eef3;
}

/*下拉菜单的样式*/
.el-dropdown-link {
  cursor: pointer;
  color: #722ed1;
}
a {
  text-decoration: none;
  color: #ffffff;
}
</style>
