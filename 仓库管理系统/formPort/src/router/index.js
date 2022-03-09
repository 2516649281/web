import Vue from "vue";
import Router from "vue-router";
import BaseSelect from "../view/baseSelect";
import Login from "../view/login";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "login",
      component: Login
    },
    {
      path: "/base",
      name: "BaseSelect",
      component: BaseSelect
    }
  ]
});
