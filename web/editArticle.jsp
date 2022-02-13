<%@ page import="target.Article" %>
<%@ page import="dao.ArticleDAO" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: 林勇全
  Date: 2021/12/22
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<html>
<head>
    <title>修改文章内容</title>
    <script type="text/javascript">
        var result = "<%=request.getParameter("result")%>";
        switch (result) {
            case "editLose":
                alert("修改失败");
                break;
            case "editSuccess":
                alert("修改成功");
                break;
        }
    </script>
</head>
<body>
<%
    String id = request.getParameter("id");
    String stuId = request.getParameter("stuId");
    ArticleDAO articleDAO = new ArticleDAO();
    Article article = null;
    try {
        article = articleDAO.get(id);
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
%>
<FORM action="${pageContext.request.contextPath}/editArticleServlet?id=<%=id%>&stuId=<%=stuId%>" method="post" enctype="multipart/form-data">
    文章标题：<input name="title" type="text" value="<%=article.getTitle()%>"><br>
    文章内容：<input name="content" type="text" value="<%=article.getContent()%>"><br>
    文章日期：<input name="pubTime" class="Wdate" type="text" onClick="WdatePicker()"><br>
    文章配图：<input type="file" accept="image/*" name="image"><br>
    <input type="submit" value="上传">
</FORM>
</body>
</html>
