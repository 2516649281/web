//学生端请求板块
import router from '../util/router'

//学生端根据姓名查询作业
export function selectWorkByName(current,size,studentId) {
    return router({
        url:`/studentPost/${current}/${size}/${studentId}?student`,
        method:`GET`
    })
}

//学生端根据班级查询作业
export function selectWorkByClass(current,size,className) {
    return router({
        url:`/studentPost/${current}/${size}/${className}?class`,
        method:`GET`
    })
}

//学生端上传作业
export function addWork(obj) {
    return router({
        url:`/studentPost`,
        method:`POST`,
        data:obj
    })
}

//学生端修改作业
export function editWork(obj) {
    return router({
        url:`/studentPost`,
        method:`PUT`,
        data:obj
    })
}

//学生端删除作业
export function delWork(obj) {
    return router({
        url:`/studentPost`,
        method:`DELETE`,
        data:obj
    })
}