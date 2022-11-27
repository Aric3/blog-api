package org.akboom.blogapi;

import org.akboom.blogapi.dao.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApiApplicationTests {
    @Autowired
    UserMapper mapper;
    @Test
    void contextLoads() {
    }

    @Test
    void testDataBase() {
        mapper.selectTest();
    }
}
