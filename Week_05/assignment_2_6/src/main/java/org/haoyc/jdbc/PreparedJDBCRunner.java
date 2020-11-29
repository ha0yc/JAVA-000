package org.haoyc.jdbc;

import java.sql.*;

public class PreparedJDBCRunner {
    public static void main(String[] args) {
        try ( Connection connection = DriverManager.getConnection("jdbc://localhost:3306/test","root", "root")){
            connection.setAutoCommit(false);
            try(PreparedStatement statement = connection.prepareStatement("INSERT INTO students (id, name) VALUES (?, ?)")){
                for (int i = 0; i < 10; i++) {
                    statement.setInt(1, i);
                    statement.setString(2, "haoyc" + i);
                    statement.addBatch();
                }
                statement.executeBatch();
            }
            connection.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
