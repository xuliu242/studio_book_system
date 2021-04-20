import Vue from "vue";
import Router from "vue-router";
const roleTable = () => import("../components/Main/roleTable.vue");
const userTable = () => import("../components/Main/userTable.vue");

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      redirect: "/roleTable"
    },
    {
      path: "/roleTable",
      component: roleTable
    },
    {
      path: "/userTable",
      component: userTable
    }
  ]
});
