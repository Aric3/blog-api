package org.akboom.blogapi;

import org.akboom.blogapi.dao.mapper.CommentMapper;
import org.akboom.blogapi.service.ArticleService;
import org.akboom.blogapi.util.SnowFlakeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class BlogApiApplicationTests {


    @Test
    void snowFlakeTest() {
        System.out.println(SnowFlakeUtils.nextId());
    }

    @Test
    void randomAvatarTest() {
         String[] avatars = {"Artboard.svg","daimaomao.svg","dajumao.svg","heimao.svg","heimaojingchang.svg",
                "jiafeimao.svg","jumao.svg","lanbaimao.svg","lanmao.svg","lihuamao.svg","mao.svg",
                "maotouying.svg","mianyinmao.svg","nainiumao.svg","PITAO-jumao.svg","sanhuamao.svg",
                "shizimao.svg","xiangsu_mao.svg"};
         for (int i = 0;i < 10000; i++) {
             int index = new Random().nextInt(avatars.length);
             System.out.println(avatars[index]);
         }
    }
}
