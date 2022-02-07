//学生请求模块
import axios from "axios";
import request from "@/uitl/request";

//查询所有班级
export function selectAllClass() {
    return request({
        url:"/class",
        method:"GET"
    })
}