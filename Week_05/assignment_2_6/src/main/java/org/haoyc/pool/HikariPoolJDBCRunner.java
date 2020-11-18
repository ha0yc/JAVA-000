package org.haoyc.pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class HikariPoolJDBCRunner {
    public static void main(String[] args) {
        HikariConfig config = new HikariConfig("/hikari.properties");
        HikariDataSource ds = new HikariDataSource(config);
        try ( Connection connection = ds.getConnection()){
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
