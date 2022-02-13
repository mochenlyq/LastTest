<%@ page import="target.Student" %>
<%@ page import="dao.StudentDAO" %><%--
  Created by IntelliJ IDEA.
  User: 林勇全
  Date: 2021/12/21
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<html>
<head>
    <title>发表文章界面</title>
</head>
<body>
<%
    String id = request.getParameter("id");
    StudentDAO studentDAO = new StudentDAO();
    Student student = studentDAO.getStudent(id);
%>
<FORM action="${pageContext.request.contextPath}/addArticleServlet?studentId=<%=student.getId()%>" method="post" enctype="multipart/form-data">
    文章id：<input name="id" type="text"><br>
    文章标题：<input name="title" type="text"><br>
    文章内容：<input name="content" type="text"><br>
    文章日期：<input name="pubTime" class="Wdate" type="text" onClick="WdatePicker()"><br>
    文章配图：<input type="file" accept="image/*" name="image"><br>
    <input type="submit" value="上传">
</FORM>
<button onclick="window.location.href = '${pageContext.request.contextPath}/student.jsp?id=<%=student.getId()%>'" >返回学生界面</button>
</body>
</html>
