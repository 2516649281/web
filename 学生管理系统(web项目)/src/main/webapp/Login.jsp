<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/12/6L
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>用户登录</title>
    <link rel="stylesheet" type="text/css" href="css/Login.css"/>
</head>
<body>
<div class="main">
    <h1>用户登录</h1>
    <p class="login">${login}</p>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <p>
            <input type="text" name="user" class="text" placeholder="UserName"/>
        </p>
        <p>
            <input
                    type="password"
                    name="password"
                    class="text"
                    placeholder="PassWord"
            />
        </p>
        <p>
            <a href="Add_User.jsp"
            ><input type="button" value="注册" class="but"
            /></a>
            <input type="submit" value="登录" class="but"/>
        </p>
        <p class="password_No">
            <a href="" class="password_a">忘记密码?</a>
        </p>
    </form>
</div>
</body>
</html>
