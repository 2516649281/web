<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/11/23
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加学生</title>
    <link rel="stylesheet" href="css/Add_Student.css" type="text/css">
</head>
<body>
<form
        action="${pageContext.request.contextPath}/add"
        method="post"
        class="main"
>
    <p>请输入添加的学生编号:<input type="text" name="id" class="txt"/>
        请输入添加的学生姓名:<input type="text" name="name" class="txt"/>
    </p>
    <p>
        请输入添加的学生年龄:<input type="text" name="age" class="txt"/>
        请输入添加的学生性别:<input type="text" name="sex" class="txt"/>
    </p>
    <p>
        请输入添加的学生居住地:<input type="text" name="address" class="txt"/>
    </p>
    <p><input type="submit" value="提交" class="but"/>
        <a href="index.jsp"><input type="button" value="取消" class="but"/></a></p>
</form>
</body>

</html>
