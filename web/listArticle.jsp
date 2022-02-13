<%@ page import="dao.ArticleDAO" %>
<%@ page import="target.Article" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 林勇全
  Date: 2021/12/22
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>论文列表</title>
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
        <th>id</th><th>学号</th><th>标题</th><th>内容</th><th>上传日期</th><th>文章图片</th>
    </tr>
    </thead>
    <tbody>
    <%
        ArticleDAO articleDAO = new ArticleDAO();
        ArrayList<Article> articles = articleDAO.getArticles();
        for(Article article : articles){
    %>
    <tr >
        <td><%=article.getId()%></td>
        <td><%=article.getStudentId()%></td>
        <td><%=article.getTitle()%></td>
        <td><%=article.getContent()%></td>
        <td><%=article.getPubTime()%></td>
        <td><img style="width:50px;height:50px" src="${pageContext.request.contextPath}/showServlet?id=<%=article.getId()%>"></td>
        <td>
            <button onclick="window.location.href =
                    '${pageContext.request.contextPath}/deleteArticleServlet?id=<%=article.getId()%>'">删除</button>
        </td>
    </tr><%}%>
    </tbody>
</table>
<button onclick="window.location.href = '${pageContext.request.contextPath}/login.jsp'" >返回登录界面</button>
</body>
</html>
