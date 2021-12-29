package edu.hue.jk.mappers;

import edu.hue.jk.models.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /*
    用户登录模块
     */
    @Select("select * from user where username=#{username} and password=#{password}")
    User login(@Param("username") String username, @Param("password") String password);
    /*
    用户注册模块
     */
    @Insert("insert into user(username,password) values(#{username},#{password})")
    int register(String username, String password);

    /*
     用户权限提升，修改用户信息，删除用户
     */
    //根据用户名查找用户信息
    @Select("select * from user where username = #{username}")
    User getUserByName(String username);


}
