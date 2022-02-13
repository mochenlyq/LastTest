package servlet;

import dao.ArticleDAO;
import dao.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        String id= req.getParameter("id");
        StudentDAO studentDAO = new StudentDAO();
        ArticleDAO articleDAO = new ArticleDAO();
        try {
            articleDAO.deleteStudentArticle(id);
            studentDAO.deleteStudent(id);
            resp.sendRedirect(req.getContextPath()+"/listStudent.jsp?result=success");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.sendRedirect(req.getContextPath()+"/listStudent.jsp?result=lose");
        }
    }
}
