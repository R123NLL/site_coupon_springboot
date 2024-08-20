package src.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import src.springboot.test.Test;

@SpringBootApplication
public class MyApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);

    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            Test test = ctx.getBean(Test.class);
//            test.showcase();
//        };
//    }

}