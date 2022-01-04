package edu.hue.jk.mappers;

import edu.hue.jk.models.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Select("select * from user where username=#{username} and password=#{password}")
    User login(@Param("username") String username, @Param("password") String password);

    /**
     * 用户注册
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Insert("insert into user(username,password) values(#{username},#{password})")
    int register(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查找用户信息
     *
     * @param username 用户名
     * @return
     */
    @Select("select * from user where username = #{username}")
    User getUserByName(String username);

    /**
     * 删除用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Delete("delete from user where username = #{username} and password = #{password}")
    int del(String username, String password);

//    /*
//    修改用户信息
//     */
//    //仅修改用户名
//    @Update("update user set username = #{username}")
//    int updateusername();
//    //仅修改用户密码
//    @Update("update user set password = #{password}")
//    int updatepassword();
//    //同时修改用户名和用户密码
//    @Update("update user set username = #{username} and password = #{password}")
//    int updateNameandPwd();
//    //修改用户权限
//    @Update("update user set usertype=#{usertype} where username=#{username}")
//    int updateconfigure(String username, String usertype);
}
