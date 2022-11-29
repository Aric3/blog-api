package org.akboom.blogapi;

import org.akboom.blogapi.dao.mapper.CommentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApiApplicationTests {

    @Autowired
    CommentMapper commentMapper;

    @Test
    void name() {

        System.out.println(commentMapper.selectUserVoByAuthorId(1));
    }
}
