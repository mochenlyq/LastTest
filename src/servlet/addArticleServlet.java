package servlet;

import dao.ArticleDAO;
import target.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@MultipartConfig
@WebServlet("/addArticleServlet")
public class addArticleServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        Article article = new Article();
        Part part = req.getPart("image");
        InputStream in = part.getInputStream();
        article.setId(req.getParameter("id"));
        article.setStudentId(req.getParameter("studentId"));
        article.setTitle(req.getParameter("title"));
        article.setContent(req.getParameter("content"));
        article.setPubTime(req.getParameter("pubTime"));
        article.setPicture(in);
        ArticleDAO articleDAO = new ArticleDAO();
        try {
            int i = articleDAO.addArticle(article);
            if(i > 0){
                resp.sendRedirect(req.getContextPath()+"/student.jsp?result=success&id=" + req.getParameter("studentId"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.sendRedirect(req.getContextPath()+"/student.jsp?result=lose&id=" + req.getParameter("studentId"));
        }

    }
}
