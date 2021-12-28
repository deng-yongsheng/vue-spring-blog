package edu.hue.jk.mappers;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录测试
     */
    public void login_test(){
        System.out.println(userMapper.login("sheng","123456"));
    }
}
