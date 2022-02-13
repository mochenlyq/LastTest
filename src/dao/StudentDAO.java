package dao;

import target.Student;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO {

    private BaseDAO baseDAO;

    public StudentDAO() throws ServletException, IOException {
        baseDAO = BaseDAO.getInstance();
    }

    //根据学生id提取该学生的所以信息存入一个Student类并返回
    public Student getStudent(String id) throws SQLException {
        Student student = new Student();
        String sql = "select * from t_student where id=" + id;
        PreparedStatement preparedStatement = baseDAO.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        student.setId(resultSet.getString("id"));
        student.setName(resultSet.getString("name"));
        student.setSchool(resultSet.getString("school"));
        return student;
    }

    //获得所以学生的信息存入一个数组并返回
    public ArrayList<Student> getStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<Student>();
        String sql = "select * from t_student";
        PreparedStatement preparedStatement = baseDAO.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Student student = new Student();
            student.setId(resultSet.getString("id"));
            student.setName(resultSet.getString("name"));
            student.setSchool(resultSet.getString("school"));
            students.add(student);
        }
        return students;
    }

    //接受一个Student类并检查数据库中是否存在该id
    public boolean insertStudent(Student stu) throws SQLException {
        ArrayList<Student> students = getStudents();
        for(Student student : students){
            if(stu.getId().equals(student.getId()) && stu.getName().equals(student.getName())){
                return true;
            }
        }
        return false;
    }

    //接受一个id连带该id发表的文章内容一并删除
    public void deleteStudent(String id) throws ServletException, IOException, SQLException {
        ArticleDAO articleDAO = new ArticleDAO();
        articleDAO.deleteArticle(id);
        String sql = "delete from t_student where id=?";
        PreparedStatement preparedStatement = baseDAO.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, id);
        int count = preparedStatement.executeUpdate();
    }

    //接受一个Student类并插入数据库
    public int addStudent(Student student) throws SQLException {
        String sql = "insert into t_student values (?,?,?)";
        PreparedStatement preparedStatement = baseDAO.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, student.getId());
        preparedStatement.setString(2, student.getName());
        preparedStatement.setString(3, student.getSchool());
        int count = preparedStatement.executeUpdate();
        if(count > 0){
            System.out.println("成功");
            return 1;
        }
        else return 0;
    }

    //接受一个Student类并修改对应id的内容
    public int editStudent(Student student) throws SQLException {
        String sql = "update t_student set name=?,school=? where id=?";
        PreparedStatement preparedStatement = baseDAO.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, student.getName());
        preparedStatement.setString(2, student.getSchool());
        preparedStatement.setString(3, student.getId());
        int count = preparedStatement.executeUpdate();
        if(count > 0){
            return 1;
        }
        else return 0;
    }

    public ArrayList<String> getStudentId(String name) throws SQLException {
        String sql = "select * from t_student where name like '%" + name + "%'";
        ArrayList<String> ids = new ArrayList<String>();
        PreparedStatement preparedStatement = baseDAO.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            String n = resultSet.getString("id");
            ids.add(n);
        }
        return ids;
    }


}
