package org.haoyc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class OriginalJDBCRunner {
    public static void main(String[] args) {
        try ( Connection connection = DriverManager.getConnection("jdbc://localhost:3306/test", "root", "root")){
            try(Statement statement = connection.createStatement()){
                try (ResultSet resultSet = statement.executeQuery("select name from student where id=1")){
                    while (resultSet.next()) {
                        //crud同理
                        System.out.println(resultSet.getString("name"));
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
