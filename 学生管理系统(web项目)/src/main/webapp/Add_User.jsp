<%-- Created by IntelliJ IDEA. User: admin Date: 2021/12/9 Time: 16:04 To change
this template use File | Settings | File Templates. --%> <% HttpSession
requestSession = request.getSession(); %> <%@ page
contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<head>
		<title>用户注册</title>
		<link rel="stylesheet" href="css/Add_User.css" type="text/css" />
	</head>
	<body>
		<div class="main">
			<h1>欢迎注册</h1>
			<p class="login">${login}</p>
			<form action="${pageContext.request.contextPath}/user" method="post">
				<div class="top">
					<p>
						<input
							type="text"
							name="username"
							placeholder="请输入你要注册的用户名"
							class="text"
							value="${user_new}"
						/>
					</p>
					<p>
						<input
							type="password"
							name="password"
							placeholder="请输入密码"
							class="text"
							value="${password_new}"
						/>
					</p>
					<p>
						<input
							type="password"
							name="password_again"
							placeholder="请再次输入密码"
							class="text"
							value="${password_again}"
						/>
					</p>
					<div class="check">
						<input
							type="text"
							class="ver"
							name="CheckCode"
							placeholder="请输入验证码"
						/><img
							src="${pageContext.request.contextPath}/check"
							alt=""
							class="number"
							id="check"
						/>
						<a id="checkImg" href="#">看不清?换一张</a>
					</div>
				</div>
				<div class="button">
					<span>
						<a href="Login.jsp"
							><input type="button" value="取消" class="but"
						/></a>
						<input type="submit" value="注册" class="but" />
					</span>
				</div>
			</form>
		</div>
	</body>
	<script type="text/javascript">
		document.getElementById("checkImg").onclick = function () {
			document.getElementById("check").src =
				"${pageContext.request.contextPath}/check?" +
				new Date().getMilliseconds();
		};
	</script>
</html>
