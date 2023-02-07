package org.zerock.api01.todo.mapper;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TimeMapperTests {

    @Autowired(required = false)
    TimeMapper timeMapper;

    @Test
    public void testTime1() {

        System.out.println(timeMapper);

        System.out.println(timeMapper.getTime());

    }

    @Test
    public void testTime2(){

        System.out.println(timeMapper);

        System.out.println(timeMapper.getTime2());
    }
}
