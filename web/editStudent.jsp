<%@ page import="dao.StudentDAO" %>
<%@ page import="target.Student" %><%--
  Created by IntelliJ IDEA.
  User: 林勇全
  Date: 2021/12/21
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改学生信息界面</title>
</head>
<body>
<%
    String id = request.getParameter("id");
    StudentDAO studentDAO = new StudentDAO();
    Student student = studentDAO.getStudent(id);
%>
<FORM action="${pageContext.request.contextPath}/editServlet?id=<%=student.getId()%>" method="post">
    姓名：<input name="name" type="text" value="<%=student.getName()%>">
    学校：<input name="school" type="text" value="<%=student.getSchool()%>">
    <input type="submit" value="修改">
</FORM>
<button onclick="window.location.href = '${pageContext.request.contextPath}/student.jsp?id=<%=student.getId()%>'" >返回学生界面</button>
</body>
</html>
