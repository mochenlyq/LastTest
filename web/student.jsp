<%@ page import="target.Student" %>
<%@ page import="dao.StudentDAO" %><%--
  Created by IntelliJ IDEA.
  User: 林勇全
  Date: 2021/12/21
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生界面</title>
    <script type="text/javascript">
        var result = "<%=request.getParameter("result")%>";
        switch (result) {
            case "editLose":
                alert("修改失败");
                break;
            case "editSuccess":
                alert("修改成功");
                break;
            case "lose":
                alert("上传失败");
                break;
            case "success":
                alert("上传成功");
                break;
        }
    </script>
</head>
<body>
<%
    String id = request.getParameter("id");
    StudentDAO studentDAO = new StudentDAO();
    Student student = studentDAO.getStudent(id);
%>
<table>
    <thead>
    <tr>
        <th>学号</th><th>姓名</th><th>学校</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><%=student.getId()%></td>
        <td><%=student.getName()%></td>
        <td><%=student.getSchool()%></td>
    </tr>
    </tbody>
</table>
<button onclick="window.location.href = '${pageContext.request.contextPath}/editStudent.jsp?id=<%=student.getId()%>'" >修改个人信息</button>
<button onclick="window.location.href = '${pageContext.request.contextPath}/addArticle.jsp?id=<%=student.getId()%>'" >发表文章界面</button>
<button onclick="window.location.href = '${pageContext.request.contextPath}/studentArticle.jsp?id=<%=student.getId()%>'" >个人发表文章界面</button>
<button onclick="window.location.href = '${pageContext.request.contextPath}/login.jsp'" >返回登录界面</button><hr>

<FORM action="${pageContext.request.contextPath}/selectServlet?id=<%=student.getId()%>" method="post">
    输入想查询文章作者的姓名：<input name="name" type="text">
    <input type="submit" value="查询">
</FORM>
</body>
</html>
