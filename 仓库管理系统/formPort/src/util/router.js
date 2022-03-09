// 请求模块
import axios from "axios";
const request = axios.create({
  //后台springboot访问路径
  // baseURL: "http://192.168.80.128:8080"
  baseURL: "http://localhost:8080"
});

export default request;
