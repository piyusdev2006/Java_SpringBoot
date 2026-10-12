package com.piyus.repository;

import com.piyus.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudnetRepository {


    String url = "jdbc:mysql://localhost:3306/student_jdbc_db";
    String user = "root";
    String password = "Mysql_2027";

    private Student mapRow(ResultSet resultSet) throws SQLException {

        Student student = new Student();

        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setAge(resultSet.getInt("age"));

        return student;
    }






    // re-writing the above method using try with rsources
    public void createStudent(Student student){

        // curating SQL queries
        String sql = """
                        INSERT INTO students (name, email, age) VALUES (?, ?, ?)
                        """;

        try (Connection connection =
                     DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement
                     = connection.prepareStatement(sql);
        )
        {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getAge());

            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected == 0) {
                System.out.println("Student has been created");
            }
            else {
                System.out.println("Student has NOT been created");
            }

        }
        catch (SQLException e){
            System.out.println("SQLException caught"+  e.getMessage());
        }
    }

    public void updateStudent(Student student, Long id){

        String sql = """
                        UPDATE students 
                        SET name=?, 
                            email=?, 
                            age=? 
                            WHERE id=?
                        """;

            try (
                    Connection connection
                            = DriverManager.getConnection(url, user, password);
                    PreparedStatement preparedStatement
                            = connection.prepareStatement(sql);
            ){

                preparedStatement.setString(1, student.getName());
                preparedStatement.setString(2, student.getEmail());
                preparedStatement.setInt(3, student.getAge());
                preparedStatement.setLong(4, id);


                // wrapping sql queries into statement
                int rowAffected = preparedStatement.executeUpdate();

                if(rowAffected == 1){
                    System.out.println("Update operation successfully");
                }
                else{
                    System.out.println("Student updation failed");
                }

            }
            catch (SQLException e){
                System.out.println("SQLException caught"+  e.getMessage());
            }
    }

    public void deleteStudent( Long id) {

        String sql = """
                        DELETE FROM students WHERE id=?
                        """;

        try (
                Connection conn
                        = DriverManager.getConnection(url, user, password);
                PreparedStatement preparedStatement
                        = conn.prepareStatement(sql)
        )
        {
            preparedStatement.setLong(1, id);
            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected == 1) {
                System.out.println("Delete operation successfully");
            } else {
                System.out.println("Student deletion failed");
            }

        } catch (SQLException e) {
            System.out.println("SQLException caught" + e.getMessage());
        }
    }



    public void getStudentById(Long id){

        String sql = """
                        SELECT id, name, email, age FROM students WHERE id=?
                        """;

        try (
                Connection conn
                        = DriverManager.getConnection(url, user, password);
                PreparedStatement preparedStatement
                        = conn.prepareStatement(sql);
                )
        {

            preparedStatement.setLong(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){

                if(resultSet.next()){
                    Student student = mapRow(resultSet);
                    System.out.println(student);
                }
            }

        } catch (SQLException e) {
            System.out.println("SQLException caught" + e.getMessage());
        }
    }

    public void getStudent(){

        String sql = """
                        SELECT id, name, email, age FROM students
                        """;
        try (
                Connection conn
                        = DriverManager.getConnection(url, user, password);
                PreparedStatement preparedStatement
                        = conn.prepareStatement(sql);
        )
        {


            try(ResultSet resultSet = preparedStatement.executeQuery()){
                List<Student> studentList = new ArrayList<>();

                while(resultSet.next()){
                    Student student = mapRow(resultSet);
                    studentList.add(student);
                    System.out.println(student);
                }
            }

        } catch (SQLException e) {
            System.out.println("SQLException caught" + e.getMessage());
        }
    }


    public void completeCRUD() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            String sql = "SELECT id, name, email, age " +
                    "FROM students where id = 7";

           boolean result = statement.execute(sql);

           if(result) {
               ResultSet resultSet = statement.getResultSet();
           }
           else {
               int rowAffected = statement.getUpdateCount();
           }

            connection.close();
        }
        catch(SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }

}
