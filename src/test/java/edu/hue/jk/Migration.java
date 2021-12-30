package edu.hue.jk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;

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

    public static Connection getConnection(String url, String driverName, String username, String password) throws ClassNotFoundException, SQLException {
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
        Connection tableconn = getConnection(url, driverName, username, password);
        Statement tablest = tableconn.createStatement();
        // 创建用户表
        st.executeUpdate("CREATE TABLE if not exists `user` (\n" +
                "\t`id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                "\t`username` CHAR(20) NOT NULL DEFAULT '' COLLATE 'utf8mb4_general_ci',\n" +
                "\t`password` TINYTEXT NOT NULL COLLATE 'utf8mb4_general_ci',\n" +
                "\t`usertype` ENUM('admin','user') NOT NULL DEFAULT 'user' COLLATE 'utf8mb4_general_ci',\n" +
                "\tPRIMARY KEY (`id`) USING BTREE,\n" +
                "\tUNIQUE INDEX `username` (`username`) USING BTREE\n" +
                ")\n" +
                "COLLATE='utf8mb4_general_ci'\n" +
                "ENGINE=InnoDB\n" +
                "AUTO_INCREMENT=3\n" +
                ";\n");
        // 创建文章表
        st.executeUpdate("CREATE TABLE if not exists `article` (\n" +
                "\t`id` CHAR(50) NOT NULL COMMENT '文章的uuid' COLLATE 'utf8mb4_general_ci',\n" +
                "\t`title` TINYTEXT NOT NULL COLLATE 'utf8mb4_general_ci',\n" +
                "\t`time` DATETIME NULL DEFAULT NULL,\n" +
                "\t`userid` INT(11) NOT NULL,\n" +
                "\tPRIMARY KEY (`id`) USING BTREE,\n" +
                "\tINDEX `FK__user` (`userid`) USING BTREE,\n" +
                "\tCONSTRAINT `FK__user` FOREIGN KEY (`userid`) REFERENCES `blog`.`user` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION\n" +
                ")\n" +
                "COLLATE='utf8mb4_general_ci'\n" +
                "ENGINE=InnoDB\n" +
                ";\n");
        // 创建评论表
        st.executeUpdate("CREATE TABLE if not exists `comment` (\n" +
                "\t`id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                "\t`userid` INT(11) NULL DEFAULT NULL,\n" +
                "\t`articleid` CHAR(50) NOT NULL COLLATE 'utf8mb4_general_ci',\n" +
                "\t`time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                "\t`content` TEXT NOT NULL COLLATE 'utf8mb4_general_ci',\n" +
                "\tPRIMARY KEY (`id`) USING BTREE,\n" +
                "\tINDEX `articleid` (`articleid`) USING BTREE\n" +
                ")\n" +
                "COLLATE='utf8mb4_general_ci'\n" +
                "ENGINE=InnoDB\n" +
                "AUTO_INCREMENT=2\n" +
                ";\n");
        // 插入一个默认用户
        st.executeUpdate("INSERT IGNORE INTO `user` (`id`, `username`, `password`, `usertype`) VALUES\n" +
                "\t(1, 'sheng', '123456', 'admin');");
        st.executeUpdate("INSERT IGNORE INTO `user` (`id`, `username`, `password`, `usertype`) VALUES\n" +
                "\t(2, 'xutao', '123456', 'user');");

        System.out.println("输出所有表");
        ResultSet show_tables = st.executeQuery("show tables");//获取某一个数据库下表的信息
        while (show_tables.next()) {
            String tablename = show_tables.getString("Tables_in_blog");
            System.out.println(tablename);

            ResultSet line_rs = tablest.executeQuery("desc " + tablename);
            System.out.println("Field\tType\tNull\tKey\tDefault\tExtra");
            while(line_rs.next()){
                System.out.print(line_rs.getString("Field")+"\t");
                System.out.print(line_rs.getString("Type")+"\t");
                System.out.print(line_rs.getString("Null")+"\t");
                System.out.print(line_rs.getString("Key")+"\t");
                System.out.print(line_rs.getString("Default")+"\t");
                System.out.print(line_rs.getString("Extra")+"\n");
            }
            System.out.println("==============================");
        }

    }
}
