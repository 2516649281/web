<%-- Created by IntelliJ IDEA. User: admin Date: 2021/11/23 Time: 17:12 To
change this template use File | Settings | File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>修改学生</title>
    <link rel="stylesheet" type="text/css" href="css/Edit_Student.css"/>
</head>
<body>
<form
        action="${pageContext.request.contextPath}/edit"
        method="post"
        class="main"
>
    <p>请输入修改的学生编号:<input type="text" name="id" class="txt" placeholder="必填"/>
        请输入修改的学生姓名:<input type="text" name="name" class="txt" placeholder="选填"/></p>
    <p>请输入修改的学生年龄:<input type="text" name="age" class="txt" placeholder="选填"/>
        请输入修改的学生性别:<input type="text" name="sex" class="txt" placeholder="选填"/>
    </p>
    请输入修改的学生居住地:<input type="text" name="address" class="txt" placeholder="选填"/>
    </p>
    <p><input type="submit" value="提交" class="but"/>
        <a href="index.jsp"><input type="button" value="取消" class="but"/></a></p>
</form>
</body>
</html>
