package servlet;

import dao.ArticleDAO;
import dao.StudentDAO;
import target.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/selectServlet")
public class selectServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        StudentDAO studentDAO = new StudentDAO();
        ArticleDAO articleDAO = new ArticleDAO();
        try {
            ArrayList<String> stuIds = studentDAO.getStudentId(name);
            ArrayList<Article> articles = new ArrayList<Article>();
            for(String s : stuIds){
                ArrayList<Article> as = articleDAO.getArticle(s);
                System.out.println(as.size());
                for(Article a : as){
                    articles.add(a);
                }
            }
            System.out.println(articles.size());
            req.setAttribute("articles", articles);
            req.getRequestDispatcher("/selectResult.jsp?id=" + id).forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
