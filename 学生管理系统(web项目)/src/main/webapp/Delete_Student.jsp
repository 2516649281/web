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
    <title>删除学生</title>
    <link rel="stylesheet" href="css/Delete_Student.css" type="text/css"/>
</head>
<body>
<form
        action="${pageContext.request.contextPath}/del"
        method="post"
        class="main"
>
    <p class="text">
        请输入删除的学生编号:<input type="text" name="id" class="txt"/>
    </p>
    <p>
        <input type="submit" value="提交" class="but"/>
        <a href="index.jsp">
            <input type="button" value="取消" class="but"/>
        </a>
    </p>
</form>
</body>
</html>
