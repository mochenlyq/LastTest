package servlet;


import dao.StudentDAO;
import target.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentDAO studentDAO = new StudentDAO();
        req.setCharacterEncoding("utf-8");
        Student student = new Student();
        student.setId(req.getParameter("id"));
        student.setName(req.getParameter("name"));
        try {
            if(studentDAO.insertStudent(student)){
                resp.sendRedirect(req.getContextPath()+"/student.jsp?id="+student.getId());
            }
            else{
                resp.sendRedirect(req.getContextPath()+"/login.jsp?result=no");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
