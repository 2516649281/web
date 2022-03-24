package com.chunfeng;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BehindPortApplicationTests {

//    @Autowired
//    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        /*
            redisTemplate.opsForValue();字符串操作
            redisTemplate.opsForList();list操作
            redisTemplate.opsForSet();set操作
            redisTemplate.opsForHash();Hash操作
         */
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();//获取连接对象
//        connection.flushAll();
//        redisTemplate.opsForValue().set("name","him");
//        System.out.println(redisTemplate.opsForValue().get("name"));
    }

}
