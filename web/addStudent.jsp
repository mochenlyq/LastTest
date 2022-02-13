<%--
  Created by IntelliJ IDEA.
  User: 林勇全
  Date: 2021/12/21
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增学生信息 注册页面</title>
</head>
<body>
<FORM action="${pageContext.request.contextPath}/addServlet">
    学号：<input name="id" type="text"  >
    姓名：<input name="name" type="text" >
    学校：<input name="school" type="text" >
    <input type="submit" value="注册">
</FORM>
</body>
</html>
