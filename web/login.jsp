<%--
  Created by IntelliJ IDEA.
  User: 林勇全
  Date: 2021/12/19
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <script type="text/javascript">
        var result = "<%=request.getParameter("result")%>";
        switch (result) {
            case "yes":
                alert("登录成功");
                break;
            case "no":
                alert("登录失败");
                break;
            case "lose":
                alert("注册失败");
                break;
            case "success":
                alert("注册成功");
                break;
        }
    </script>
</head>
<body>
<FORM action="${pageContext.request.contextPath}/loginServlet">
    学号：<input name="id" type="text"  >
    姓名：<input name="name" type="text" >
    <input type="submit" value="登录">
</FORM>
<button onclick="window.location.href = '${pageContext.request.contextPath}/listStudent.jsp'" >查询所有学生</button>
<button onclick="window.location.href = '${pageContext.request.contextPath}/listArticle.jsp'" >查询所有论文</button>
<button onclick="window.location.href = '${pageContext.request.contextPath}/addStudent.jsp'" >注册</button>
</body>
</html>
