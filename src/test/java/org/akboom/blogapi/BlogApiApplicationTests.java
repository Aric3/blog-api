package org.akboom.blogapi;

import org.akboom.blogapi.dao.mapper.CommentMapper;
import org.akboom.blogapi.util.SnowFlakeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApiApplicationTests {

    @Test
    void snowFlakeTest() {
        System.out.println(SnowFlakeUtils.nextId());
    }
}
