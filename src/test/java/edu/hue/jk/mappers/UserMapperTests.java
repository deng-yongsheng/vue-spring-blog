package edu.hue.jk.mappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void test() {
        /**
         * 用户登录
         * 功能测试
         */
        System.out.println(userMapper.login("徐涛", "123456"));
        /**
         * 用户注册
         * 功能测试
         */
        System.out.println(userMapper.register("王五", "123456"));
        /**
         * 根据用户名查找用户信息
         * 功能测试
         */
        System.out.println(userMapper.getUserByName("徐涛"));
        /**
         * 删除用户
         * 功能测试
         */
        System.out.println(userMapper.del("王五", "123456"));
        /**
         * 根据用户id查找用户信息
         * 功能测试
         */
        System.out.println(userMapper.getUserById(2));
    }
}
