package servlet;


import dao.ArticleDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteOfStuServlet")
public class deleteOfStuServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        String stuId = req.getParameter("stuId");
        ArticleDAO articleDAO = new ArticleDAO();
        try {
            articleDAO.deleteArticle(id);
            resp.sendRedirect(req.getContextPath()+"/studentArticle.jsp?result=success&id=" + stuId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.sendRedirect(req.getContextPath()+"/studentArticle.jsp?result=lose&id=" + stuId);
        }
    }

}
