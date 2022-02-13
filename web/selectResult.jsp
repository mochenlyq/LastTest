<%@ page import="java.util.ArrayList" %>
<%@ page import="target.Article" %><%--
  Created by IntelliJ IDEA.
  User: 林勇全
  Date: 2021/12/22
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询结果</title>
</head>
<body>
<%
    ArrayList<Article> articlelist = (ArrayList<Article>)request.getAttribute("articles");
    String id = request.getParameter("id");
%>
<table>
    <thead>
    <tr>
        <th>id</th><th>学号</th><th>标题</th><th>内容</th><th>上传日期</th><th>图片</th>
    </tr>
    </thead>
    <tbody>
    <%
        for(Article article : articlelist){
    %>
    <tr >
        <td><%=article.getId()%></td>
        <td><%=article.getStudentId()%></td>
        <td><%=article.getTitle()%></td>
        <td><%=article.getContent()%></td>
        <td><%=article.getPubTime()%></td>
        <td><img style="width:50px;height:50px" src="${pageContext.request.contextPath}/showServlet?id=<%=article.getId()%>"></td>
    </tr><%}%>
    </tbody>
</table>
<button onclick="window.location.href = '${pageContext.request.contextPath}/student.jsp?id=<%=id%>'" >返回学生界面</button>
</body>
</html>
