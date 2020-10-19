package com.duanzm.springbootproject;

import com.duanzm.springbootproject.pojo.Dog;
import com.duanzm.springbootproject.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootProjectApplicationTests {

    @Autowired
    private Dog dog;

    @Autowired
    private Person person;

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        System.out.println(dog);
        System.out.println(person);
    }
}