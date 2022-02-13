package servlet;
import dao.BaseDAO;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MultipartConfig
@WebServlet("/showServlet")
public class showServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseDAO baseDAO = BaseDAO.getInstance();
        req.setCharacterEncoding("utf-8");
        String sql = "select * from t_student_article where id='" + req.getParameter("id") + "'";
        try {
            PreparedStatement preparedStatement = baseDAO.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            InputStream in = resultSet.getBinaryStream("picture");
            ServletOutputStream soutStream = resp.getOutputStream();
            if (in.equals(null)) {
                soutStream.println("图片无法显示 ！<br>");
            }
            else {
                // 定义字节流缓冲数组
                byte[] buffer = new byte[1024];
                // 循环输出字节流, 为空时，read()返回 -1
                while (in.read(buffer) != -1) {
                    soutStream.write(buffer);
                }
                // 输入完毕，清楚缓冲
                soutStream.flush();
                soutStream.close();
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
