package cn.superhh.blogpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.concurrent.ScheduledExecutorService;

@SpringBootApplication
@ServletComponentScan
public class BlogproApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BlogproApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogproApplication.class, args);
    }

}
