import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import "../src/assets/css/global.css";
import axios from "axios";
import { Loading } from "element-ui";
import { Notification } from "element-ui";
Vue.prototype.$http = axios;

let loading;
//内存中正在请求的数量
let loadingNum = 0;
function startLoading() {
  if (loadingNum == 0) {
    loading = Loading.service({
      lock: true,
      text: "拼命加载中...",
      background: "rgba(255,255,255,0.5)"
    });
  }
  //请求数量加1
  loadingNum++;
}
function endLoading() {
  //请求数量减1
  loadingNum--;
  if (loadingNum <= 0) {
    loading.close();
  }
}

axios.defaults.timeout = 10000; // 请求超时
axios.defaults.baseURL = "/studio"; // api 即上面 vue.config.js 中配置的地址
//默认的contentType为json以及utf-8；
axios.defaults.headers = { "Content-Type": "application/json;charset=UTF-8" };
//请求拦截器
// axios.interceptors.request.use(
//   config => {
//     startLoading();
//     config.headers.ACCESS_TOKEN = LocalStorage.get(ACCESS_TOKEN);
//     return config;
//   },
//   error => {
//     return Promise.reject(error);
//   }
// );
//响应拦截器
// axios.interceptors.response.use(
//   function (response) {
//     endLoading();
//     console.log("response",response)
//     const res = response.data;
//     // if (res.success) {
//     //   return response;
//     // }
//     if (res.code == 501) {
//       LocalStorage.clearAll();
//       ElementUI.Notification({
//         title: '登录超时',
//         message: '登录超时，请重新登录！',
//         type: 'error'
//       });

//       return router.push("/login");
//     }
//     return response;
//   },
//   function (error) {
//     return Promise.reject(error)
//   }
// )

// router.beforeEach(function(to, from, next) {

//   if (to.meta.needLogin) {
//     //页面是否登录
//     if (sessionStorage.getItem("UserLoginName")) {
//       //本地存储中是否有token(uid)数据
//       next(); //表示已经登录
//     } else {
//       //next可以传递一个路由对象作为参数 表示需要跳转到的页面
//       next({
//         path: '/login',
//         query: {redirect: to.fullPath}
//         // name:'login'
//       });
//     }
//   } else {
//     //表示不需要登录
//     next(); //继续往后走
//   }
// });
Vue.config.productionTip = false;
Vue.use(ElementUI);
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
