package org.haoyc.assignment.test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.util.StopWatch;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class HikariPoolJDBCRunner {
    public static void main(String[] args) {
        DataSource ds = getDataSource();
        insertData(1000000, 500, 10, ds);
        return;
    }

    private static DataSource getDataSource() {
        HikariConfig config = new HikariConfig("/hikari.properties");
        return new HikariDataSource(config);
    }

    private static void insertData(int count, int commitSize, int threadSize, final DataSource ds) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ExecutorService pool = Executors.newFixedThreadPool(threadSize);
        int commitTimes = count / commitSize;
        final AtomicInteger atomicInteger = new AtomicInteger(0);

        for (int i = 0; i < commitTimes; i++) {
            pool.submit(new InsertTask(ds, atomicInteger, commitSize, i));
        }

        while (atomicInteger.get() != commitTimes) {
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        stopWatch.stop();
        double time = stopWatch.getTotalTimeSeconds();
        System.out.println("count:"+ count +", commitSize:"+ commitSize +", threadSize:" + threadSize +", has run for " + time + " seconds");
    }


    private static class InsertTask implements Runnable{
        public final DataSource dataSource;
        public final AtomicInteger atomicInteger;
        public final int commitSize;
        public final int index;

        public InsertTask(DataSource dataSource, AtomicInteger atomicInteger, int commitSize, int index) {
            this.dataSource = dataSource;
            this.atomicInteger = atomicInteger;
            this.commitSize = commitSize;
            this.index = index;
        }

        @Override
        public void run() {
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                connection = dataSource.getConnection();
                connection.setAutoCommit(false);
                statement = connection.prepareStatement("INSERT INTO user (user_name, mobile_no, user_id) VALUES (?, ?, ?)");
                int start = index * commitSize;
                for (int i = 0; i < commitSize; i++) {
                    statement.setString(1, "haoyc");
                    statement.setString(2, "haoyc" + i);
                    statement.setString(3, String.valueOf(start + i));
                    statement.addBatch();
                }
                statement.executeBatch();
                connection.commit();
                atomicInteger.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + " has inserted the " + index + " batch data");
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
