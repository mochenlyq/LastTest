package servlet;

import dao.ArticleDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteArticleServlet")
public class deleteArticleServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        String id= req.getParameter("id");
        ArticleDAO articleDAO = new ArticleDAO();
        try {
            articleDAO.deleteArticle(id);
            resp.sendRedirect(req.getContextPath()+"/listArticle.jsp?result=success");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.sendRedirect(req.getContextPath()+"/listArticle.jsp?result=lose");
        }
    }

}
