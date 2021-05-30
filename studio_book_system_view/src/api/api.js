import axios from "axios";
import request from "./request";
//登录
export const doLogin = params => request.post("/login", params);
//获取用户菜单
export const selectMenuByUserId = params =>
  request.get("/roleMenu/selectMenuByUserId", { params });

export const getUserInfo = params =>
  request.get("/user/getUserInfo", { params });
