package com.blog.app;

import com.blog.app.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void repoTest() {
        String className = this.userRepository.getClass().getName();
        String packageName = this.userRepository.getClass().getPackageName();
        System.out.println("Class Name =" + className);
        System.out.println("Package Name =" + packageName);
    }

}
