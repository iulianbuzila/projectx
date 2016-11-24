package com.projectx.cwm;

import com.projectx.cwm.domain.User;
import com.projectx.cwm.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@SpringBootApplication
public class CwmApplication {
    public static void main(String[] args) {
        SpringApplication.run(CwmApplication.class, args);
    }

    //	@Bean
//	CommandLineRunner init(UserRepository userRepository){
//		return (evt) -> Arrays.asList(
//				"jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
//				.forEach(
//						a -> {
//							User account = userRepository.save(new User(a,
//									"password", "ADMIN"));
//						});
//	}
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("*").allowedOrigins("*");
            }
        };
    }
}
