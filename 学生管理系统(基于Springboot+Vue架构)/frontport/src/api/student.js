//学生请求模块
import axios from "axios";
import request from "@/uitl/request";

//查询
export function selectAllStudentPage(current, size, ClassName) {
    return request({
        url: '/student/'+current+'/'+size+'/'+ClassName,
        method: "GET"
    })
}

//添加
export function addStudent(obj) {
    return request({
        url:'/student',
        method:'POST',
        data:obj
    })
}

//删除
export function delStudent(id) {
    return request({
        url:'/student/'+id,
        method:'DELETE',
    })
}

//编辑
export function editStudent(obj) {
    return request({
        url:'/student/',
        method:'PUT',
        data:obj
    })
}

