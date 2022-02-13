<%@ page import="java.util.ArrayList" %>
<%@ page import="target.Student" %>
<%@ page import="dao.StudentDAO" %><%--
  Created by IntelliJ IDEA.
  User: 林勇全
  Date: 2021/12/20
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生列表</title>
    <script type="text/javascript">
        var result = "<%=request.getParameter("result")%>";
        switch (result) {
            case "lose":
                alert("删除失败");
                break;
            case "success":
                alert("删除成功");
                break;
        }
    </script>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>学号</th><th>姓名</th><th>学校</th>
    </tr>
    </thead>
    <tbody>
<%
    StudentDAO studentDAO = new StudentDAO();
    ArrayList<Student> students = studentDAO.getStudents();
    for(Student student : students){
%>
<tr >
    <td><%=student.getId()%></td>
    <td><%=student.getName()%></td>
    <td><%=student.getSchool()%></td>
    <td>
        <button onclick="window.location.href =
                '${pageContext.request.contextPath}/deleteServlet?id=<%=student.getId()%>'">删除</button>
    </td>
</tr><%}%>
    </tbody>
</table>
<button onclick="window.location.href = '${pageContext.request.contextPath}/login.jsp'" >返回登录界面</button>
</body>
</html>
