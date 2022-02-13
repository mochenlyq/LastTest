<%@ page import="dao.ArticleDAO" %>
<%@ page import="target.Article" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: 林勇全
  Date: 2021/12/22
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人发表的文章</title>
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
        <th>id</th><th>标题</th><th>内容</th><th>上传日期</th><th>图片</th>
    </tr>
    </thead>
    <tbody>
    <%
        String stuid = request.getParameter("id");
        ArticleDAO articleDAO = new ArticleDAO();
        ArrayList<Article> articles = null;
        try {
            articles = articleDAO.getArticle(stuid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for(Article article : articles){
    %>
    <tr >
        <td><%=article.getId()%></td>
        <td><%=article.getTitle()%></td>
        <td><%=article.getContent()%></td>
        <td><%=article.getPubTime()%></td>
        <td><img style="width:50px;height:50px" src="${pageContext.request.contextPath}/showServlet?id=<%=article.getId()%>"></td>
        <td>
            <button onclick="window.location.href =
                    '${pageContext.request.contextPath}/editArticle.jsp?id=<%=article.getId()%>&stuId=<%=article.getStudentId()%>'">修改</button>
        </td>
        <td>
            <button onclick="window.location.href =
                    '${pageContext.request.contextPath}/deleteOfStuServlet?id=<%=article.getId()%>&stuId=<%=article.getStudentId()%>'">删除</button>
        </td>
    </tr><%}%>
    </tbody>
</table>
<button onclick="window.location.href = '${pageContext.request.contextPath}/student.jsp?id=<%=stuid%>'" >返回学生界面</button>
</body>
</html>
