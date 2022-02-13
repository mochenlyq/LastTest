package dao;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;

public class BaseDAO {

    private final static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    private final static String URL = "jdbc:mysql://localhost:3306/sys?useSSL=false&allowPublicKeyRetrieval=true";

    private final static String USERNAME = "root";

    private final static String PASSWORD = "123456";

    private static BaseDAO baseDAO;

    private Connection connection;

    //获得单例类
    public static BaseDAO getInstance() throws ServletException, IOException {
        if(baseDAO == null){
            baseDAO = new BaseDAO();
        }
        return baseDAO;
    }

    //连接MySQL
    private BaseDAO() throws ServletException, IOException{
        try {
            Class.forName(DRIVER_CLASS_NAME).newInstance();
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException ce) {
            ce.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
