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
@WebServlet("/editArticleServlet")
public class editArticleServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Article article = new Article();
        article.setId(req.getParameter("id"));
        article.setStudentId(req.getParameter("stuId"));
        System.out.println(req.getParameter("id"));
        System.out.println(req.getParameter("stuId"));
        article.setTitle(req.getParameter("title"));
        article.setContent(req.getParameter("content"));
        article.setPubTime(req.getParameter("pubTime"));
        Part part = req.getPart("image");
        InputStream in = part.getInputStream();
        article.setPicture(in);
        ArticleDAO articleDAO = new ArticleDAO();
        try {
            int i = articleDAO.editAfticle(article);
            if(i > 0){
                resp.sendRedirect(req.getContextPath()+"/studentArticle.jsp?result=editSuccess&id=" + article.getStudentId());
            }
            else resp.sendRedirect(req.getContextPath()+"/studentArticle.jsp?result=editLose&id=" + article.getStudentId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.sendRedirect(req.getContextPath()+"/studentArticle.jsp?result=editLose&id=" + article.getStudentId());
        }
    }

}
