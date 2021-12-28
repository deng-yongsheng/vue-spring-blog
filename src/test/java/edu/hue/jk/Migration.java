package edu.hue.jk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Migration {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverName;

    public static Connection getConnection(url, driverName, username, password) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, username, password);
        return conn;
    }


    @Test
    /**
     * 迁移数据库
     */
    public void migration() throws SQLException, ClassNotFoundException {
        Connection conn = getConnection(url, driverName, username, password);
        Statement st = conn.createStatement();
        st.executeUpdate("create database blog if not exists");
        st.executeUpdate("use blog");


    }
}
