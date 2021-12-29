package edu.hue.jk.mappers;

import edu.hue.jk.models.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{user} and password=#{password}")
    User login(String user,String password);

    @Insert("insert into user(username,password) values(#{username},#{password})")
    int rigister(User user);

}
