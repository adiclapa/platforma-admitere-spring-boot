package com.code.springboot;


import com.code.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.code.service.MinIOService;

@SpringBootApplication
@ComponentScan(basePackages = "com.code")
@EntityScan("com.code.model")
@EnableJpaRepositories(basePackages = "com.code.repository")
@EnableConfigurationProperties(RsaKeyProperties.class)
public class SpringbootApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }{

    }


}
