package com.systemcheck;


import com.systemcheck.repository.UserRepository;
import com.systemcheck.util.ScriptRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootApplication

@Configuration
@RunWith(SpringRunner.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
