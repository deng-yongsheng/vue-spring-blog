package edu.hue.jk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Migration {
    @Value("${spring.datasource.url}")
    private String url;

    @Test
    public void migration(){
        System.out.println(url);
    }
}
