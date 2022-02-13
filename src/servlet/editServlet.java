package servlet;

import dao.StudentDAO;
import target.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet("/editServlet")
public class editServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        Student student = new Student();
        student.setId(req.getParameter("id"));
        student.setName(req.getParameter("name"));
        student.setSchool(req.getParameter("school"));
        StudentDAO studentDAO = new StudentDAO();
        try {
            int i = studentDAO.editStudent(student);
            if(i > 0){
                resp.sendRedirect(req.getContextPath()+"/student.jsp?result=editSuccess&id=" + student.getId());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.sendRedirect(req.getContextPath()+"/student.jsp?result=editLose&id=" + student.getId());
        }
    }

}
