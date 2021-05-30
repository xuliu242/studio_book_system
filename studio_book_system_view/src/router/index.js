import Vue from "vue";
import VueRouter from "vue-router";
import Main from "@/views/Main";
import UserIndex from "@/views/user/UserIndex";
import Login from "@/views/Login";
// import UserTeacher from '@/views/user/UserTeacher'
// import UserStudent from '@/views/user/UserStudent'
// import ClassIndex from '@/views/class/ClassIndex'
// import CheckWorkIndex from '@/views/checkwork/CheckWorkIndex'
// import CheckWorkResult from '@/views/checkwork/CheckWorkResult'
// import SignInfoIndex from '@/views/checkwork/SignInfoIndex'
// import WechatBindIndex from '@/views/system/WechatBindIndex'
import RoleIndex from "@/views/user/RoleIndex";
import MenuIndex from "@/views/menu/MenuIndex";
import Home from "@/views/Home";
import ClassroomPage from "@/views/classroom/ClassroomPage";
import ClasssitPage from "@/views/classroom/ClasssitPage";
import ReservePage from "@/views/bussiness/ReservePage";

Vue.use(VueRouter);
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err);
};
const routes = [
  {
    path: "/",
    redirect: "/login"
  },
  {
    path: "/login",
    component: Login
  },
  {
    path: "/main",
    component: Main,
    meta: {
      needLogin: true //需要加校检判断的路由
    },
    children: [
      {
        path: "/user/userindex",
        component: UserIndex,
        meta: {
          needLogin: true //需要加校检判断的路由
        }
      },
      {
        path: "/user/roleindex",
        component: RoleIndex,
        meta: {
          needLogin: true //需要加校检判断的路由
        }
      },
      {
        path: "/menu/menuindex",
        component: MenuIndex,
        meta: {
          needLogin: true //需要加校检判断的路由
        }
      },
      {
        path: "/classroom/classroomPage",
        component: ClassroomPage,
        meta: {
          needLogin: true //需要加校检判断的路由
        }
      },
      {
        path: "/classroom/classsitPage",
        component: ClasssitPage,
        meta: {
          needLogin: true //需要加校检判断的路由
        }
      },
      {
        path: "/bussiness/reservePage",
        component: ReservePage,
        meta: {
          needLogin: true //需要加校检判断的路由
        }
      },
      // {
      //   path:'/user/teacherindex',
      //   component:UserTeacher,
      //   meta: {
      //     needLogin: true //需要加校检判断的路由
      //   },
      // },
      // {
      //   path:'/user/studentindex',
      //   component:UserStudent,
      //   meta: {
      //     needLogin: true //需要加校检判断的路由
      //   },
      // },
      // {
      //   path:'/class/classindex',
      //   component:ClassIndex,
      //   meta: {
      //     needLogin: true //需要加校检判断的路由
      //   },
      // },
      // {
      //   path:'/system/wechatBindIndex',
      //   component:WechatBindIndex,
      //   meta: {
      //     needLogin: true //需要加校检判断的路由
      //   },
      // },
      // {
      //   path:'/checkwork/checkworkIndex',
      //   component:CheckWorkIndex,
      //   meta: {
      //     needLogin: true //需要加校检判断的路由
      //   },
      // },
      // {
      //   path:'/checkwork/checkworkResult',
      //   name: '/checkwork/checkworkResult',
      //   component:CheckWorkResult,
      //   meta: {
      //     needLogin: true //需要加校检判断的路由
      //   },
      // },
      // {
      //   path:'/checkwork/signInfoIndex',
      //   name: '/checkwork/signInfoIndex',
      //   component:SignInfoIndex,
      //   meta: {
      //     needLogin: true //需要加校检判断的路由
      //   },
      // },
      {
        path: "/home",
        name: "/home",
        component: Home,
        meta: {
          needLogin: true //需要加校检判断的路由
        }
      }
    ]
  }
];

const router = new VueRouter({
  mode: "history",
  routes
});

export default router;
