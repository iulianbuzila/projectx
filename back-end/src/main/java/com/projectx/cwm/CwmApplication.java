package com.projectx.cwm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CwmApplication {
//	@Bean
//	CommandLineRunner init(UserRepository userRepository){
//		return (evt) -> Arrays.asList(
//				"jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
//				.forEach(
//						a -> {
//							User account = userRepository.save(new User(a,
//									"password", "USER"));
//						});
//	}
	public static void main(String[] args) {
		SpringApplication.run(CwmApplication.class, args);
	}
}
