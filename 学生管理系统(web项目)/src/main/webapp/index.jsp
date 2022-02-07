<%@ page import="dao.pojo.Student" %>
<%@ page import="dao.pojo.StudentDemo" %><%--
Created by IntelliJ IDEA. User: 春风能解释 Date: 2021/11/20 Time: 14:32 To
change this template use File | Settings | File Templates. --%>
<%@ page
        contentType="text/html;charset=UTF-8" language="java" %>
<% HttpSession
        sessionAll = request.getSession();
    Object add = sessionAll.getAttribute("add");
    Object del = sessionAll.getAttribute("del");
    Object edit =
            sessionAll.getAttribute("edit"); %>
<html>
<head>
    <title>学生管理系统</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="css/index.css"/>
</head>
<body>
<div class="main">
    <%--左边--%>
    <div id="left">
        <div class="left_main">
            <div class="left_img">
                <img src="img/学生.png" class="img" width="140px" height="140px"/>
            </div>
            <h1 class="text-center">欢迎你,${user_login}</h1>
            <div class="left_but">
                <a href="Edit_Student.jsp"
                ><input type="button" value="修改学生" class="btn"
                /></a>
                <a href="Add_Student.jsp"
                ><input type="button" value="添加学生" class="btn"
                /></a>
                <a href="Delete_Student.jsp"
                ><input type="button" value="删除学生" class="btn"
                /></a>
                <form
                        action="${pageContext.request.contextPath}/exit"
                        method="post"
                >
                    <input type="submit" value="退出系统" class="btn" name="exit"/>
                </form>
            </div>
        </div>
    </div>
    <%--右边表格--%>
    <div id="right">
				<span id="spanFirstt"
                ><input type="button" value="第一页" class="ye_butt"
                /></span>
        <span id="spanPret"
        ><input type="button" value="上一页" class="ye_butt"
        /></span>
        <span id="spanNextt"
        ><input type="button" value="下一页" class="ye_butt"
        /></span>
        <span id="spanLastt"
        ><input type="button" value="最后一页" class="ye_butt"
        /></span>
        第<span id="spanPageNumt"></span>页/共
        <span id="spanTotalPaget"></span>页
        <table class="right_table" border="1px" id="tablelsw" align="center">
            <% int cont = 18;
                for (Student i : StudentDemo.SelectAllStudent()) {
                    if (cont % 18 == 0) { %>
            <th colspan="5">学生表</th>
            <tr>
                <th width="10%">编号</th>
                <th width="30%">姓名</th>
                <th width="10%">年龄</th>
                <th width="10%">性别</th>
                <th width="40%">居住地</th>
            </tr>
            <% }
                cont++; %>
            <tr>
                <td><%=i.getId()%>
                </td>
                <td><%=i.getName()%>
                </td>
                <td><%=i.getAge()%>岁</td>
                <td><%=i.getSex()%>
                </td>
                <td><%=i.getAddress()%>
                </td>
            </tr>
            <%} %>
        </table>
        <span id="spanFirst"
        ><input type="button" value="第一页" class="ye_butt"
        /></span>
        <span id="spanPre"
        ><input type="button" value="上一页" class="ye_butt"
        /></span>
        <span id="spanNext"
        ><input type="button" value="下一页" class="ye_butt"
        /></span>
        <span id="spanLast"
        ><input type="button" value="最后一页" class="ye_butt"
        /></span>
        第<span id="spanPageNum"></span>页/共 <span id="spanTotalPage"></span>页
    </div>
</div>
</body>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
<% if (add != null) { %>
<script type="text/javascript">
    alert("已将<%=add%>号学生添加至表格中");
</script>
<% sessionAll.setAttribute("add", null);
} else if (del != null) { %>
<script type="text/javascript">
    alert("已将<%=del%>号学生移出表格");
</script>
<% sessionAll.setAttribute("del", null);
} else if (edit != null) { %>
<script type="text/javascript">
    alert("已修改<%=edit%>号学生信息");
</script>
<% sessionAll.setAttribute("edit", null);
} %>
</html>
