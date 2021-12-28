package edu.hue.jk.mappers;

import edu.hue.jk.models.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import javax.xml.transform.Result;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username} and password=#{password}")
    User login(String user,String password);


}
