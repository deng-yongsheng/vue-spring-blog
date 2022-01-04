package edu.hue.jk.mappers;

import edu.hue.jk.models.User;
import org.apache.ibatis.annotations.*;

/**
 * 接口实现类
 */
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
    int del(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用用户id查找用户信息
     *
     * @param userid 用户id
     * @return
     */
    @Select("select * from user where id = #{userid}")
    User getUserById(Integer userid);
}
