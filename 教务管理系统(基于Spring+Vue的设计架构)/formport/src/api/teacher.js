//教师端请求模块
import router from '../util/router'

//教师端根据班级名称查询班级作业完成情况
export function selectWorkByClassName(current,size,className) {
    return router({
        url:`/teacherPost/${current}/${size}/${className}`,
        method:'GET'
    })
}

//查询该班级指定作业的学生完成情况
export function selectWorkOKByClassName(current,size,className,workId) {
    return router({
        url:`/teacherPost/${current}/${size}/${className}/${workId}`,
        method:'GET'
    })
}

//教师端添加班级学生作业
export function addWork(obj) {
    return router({
        url:`/teacherPost`,
        method:'POST',
        data:obj
    })
}

//删除作业
export function deleteWork(obj) {
    return router({
        url:`/teacherPost`,
        method:'DELETE',
        data:obj
    })
}

//修改作业
export function editWork(obj) {
    return router({
        url:`/teacherPost`,
        method:`PUT`,
        data:obj
    })
}

//批改作业
export function editSource(obj) {
    return router({
        url:`/teacherPost`,
        method: `PATCH`,
        data:obj
    })
}

//查看所有班级,用于输入框提示
export function selectClassName(current,size) {
    return router({
        url:`adminPost/${current}/${size}?class`,
        method:'GET'
    })
}

