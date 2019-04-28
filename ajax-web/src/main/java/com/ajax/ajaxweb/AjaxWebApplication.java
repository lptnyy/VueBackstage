package com.ajax.ajaxweb;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan("com")
@MapperScan(value = "com.ajax.ajaxweb.mapper")
public class AjaxWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AjaxWebApplication.class, args);
    }

    //设置ajax跨域请求
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer(){

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}

