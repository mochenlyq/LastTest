package dao;

import target.Article;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleDAO {

    private BaseDAO baseDAO;

    public ArticleDAO() throws ServletException, IOException {
        baseDAO = BaseDAO.getInstance();
    }

    public int addArticle(Article article) throws SQLException, IOException {
        String sql = "insert into t_student_article values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = baseDAO.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, article.getId());
        preparedStatement.setString(2, article.getStudentId());
        preparedStatement.setString(3, article.getTitle());
        preparedStatement.setString(4, article.getContent());
        preparedStatement.setBlob(5, article.getPicture(), article.getPicture().available());
        preparedStatement.setString(6, article.getPubTime());
        int count = preparedStatement.executeUpdate();
        if(count > 0){
            System.out.println("上传成功");
            preparedStatement.close();
            return 1;
        }
        else {
            preparedStatement.close();
            System.out.println("上传失败");
            return 0;
        }
    }

    //
    public void deleteArticle(String id) throws SQLException {
        String sql = "delete from t_student_article where id=?";
        PreparedStatement preparedStatement = baseDAO.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, id);
        int count = preparedStatement.executeUpdate();
        if(count>0){
            preparedStatement.close();
            System.out.println("论文删除成功");
        }else {
            preparedStatement.close();
            System.out.println("论文删除失败");
        }
    }

    public void deleteStudentArticle(String studentId) throws SQLException {
        String sql = "delete from t_student_article where studentId=?";
        PreparedStatement preparedStatement = baseDAO.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, studentId);
        int count = preparedStatement.executeUpdate();
        if(count>0){
            preparedStatement.close();
            System.out.println("论文删除成功");
        }else {
            preparedStatement.close();
            System.out.println("论文删除失败");
        }

    }

    public ArrayList<Article> getArticles() throws SQLException {
        ArrayList<Article> articles = new ArrayList<Article>();
        String sql = "select * from t_student_article";
        PreparedStatement preparedStatement = baseDAO.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Article article = new Article();
            article.setId(resultSet.getString("id"));
            article.setStudentId(resultSet.getString("studentId"));
            article.setTitle(resultSet.getString("title"));
            article.setContent(resultSet.getString("content"));
            article.setPubTime(resultSet.getString("pubTime"));
            articles.add(article);
        }
        resultSet.close();
        preparedStatement.close();
        return articles;
    }

    public ArrayList<Article> getArticle(String studentId) throws SQLException {
        ArrayList<Article> articles = new ArrayList<Article>();
        String sql = "select * from t_student_article where studentId='" + studentId + "'";
        PreparedStatement preparedStatement = baseDAO.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Article article = new Article();
            article.setId(resultSet.getString("id"));
            article.setStudentId(resultSet.getString("studentId"));
            article.setTitle(resultSet.getString("title"));
            article.setContent(resultSet.getString("content"));
            article.setPubTime(resultSet.getString("pubTime"));
            articles.add(article);
        }
        resultSet.close();
        preparedStatement.close();
        return articles;
    }

    public Article get(String id) throws SQLException {
        Article article = new Article();
        String sql = "select * from t_student_article where id='" + id +"'";
        System.out.println(sql);
        PreparedStatement preparedStatement = baseDAO.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        article.setId(resultSet.getString("id"));
        article.setStudentId(resultSet.getString("studentId"));
        article.setTitle(resultSet.getString("title"));
        article.setContent(resultSet.getString("content"));
        article.setPubTime(resultSet.getString("pubTime"));
        resultSet.close();
        preparedStatement.close();
        return article;
    }

    public int editAfticle(Article article) throws SQLException, IOException {
        String sql = "update t_student_article set title=?,content=?,picture=?,pubTime=? where id='"+article.getId()+"'";
        System.out.println(sql);
        PreparedStatement preparedStatement = baseDAO.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, article.getTitle());
        preparedStatement.setString(2, article.getContent());
        preparedStatement.setBlob(3, article.getPicture(), article.getPicture().available());
        preparedStatement.setString(4, article.getPubTime());
        int count = preparedStatement.executeUpdate();
        if(count > 0){
            System.out.println("修改成功");
            preparedStatement.close();
            return 1;
        }
        else {
            preparedStatement.close();
            System.out.println("修改失败");
            return 0;
        }
    }

}
