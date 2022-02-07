// 请求模块
import axios from "axios";
//放到单独的文件，便于维护
const request = axios.create({
	//请求的基本路径
	baseURL: "http://192.168.1.9", //请求的基本路径
});

//请求拦截器
request.interceptors.request
	.use
	// 在发送请求之前做些什么,所有请求都会经过这里
	// config是当前请求相关的配置信息对象
	//config是可以修改的
	//然后我们就可以在请求出去时定制统一的业务功能
	//例如 设置统一的 ；token
	();
//响应拦截器

//导出请求方法
export default request;

//谁使用谁就要加载request模块
// import request from 'request.js'
// request.xxx
// request({
//     method: '',
//     url: ''
// })
