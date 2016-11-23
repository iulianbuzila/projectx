package com.projectx.cwm;

import com.projectx.cwm.domain.User;
import com.projectx.cwm.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class CwmApplication {
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
	public static void main(String[] args) {
		SpringApplication.run(CwmApplication.class, args);
	}
}
