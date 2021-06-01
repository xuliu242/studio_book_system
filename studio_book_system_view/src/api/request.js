import axios from "axios";

//创建单例
const instance = axios.create({
  // baseURL: "http://127.0.0.1:8088/studio",
  baseURL: "http://123.56.235.185:8088/studio",
  timeout: 5000
});

//拦截器 - 请求拦截
// instance.interceptors.request.use(config=>{
//   //放入token
//   let token=LocalStorage.get(ACCESS_TOKEN);
//   config.headers={
//     'ACCESS_TOKEN': token
//   }
//   return config;
// },error => {
//   return  Promise.reject(error);
// });

// //拦截器 - 响应拦截
// instance.interceptors.response.use(res=>{
//   return res;
// }, error => {
//   return  Promise.reject(error);
// })

//整体导出
export default instance;
