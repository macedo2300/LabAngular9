package com.br.crud.pibape;

import com.br.crud.pibape.entity.User;
import com.br.crud.pibape.enums.ProfileEnum;
import com.br.crud.pibape.repositoty.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PibapeBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(PibapeBackApplication.class, args);
	}


	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			initUsers(userRepository);
		};

	}

	private void initUsers(UserRepository userRepository) {
		User admin = new User();
		admin.setEmail("admin@pibape.com.br");
		admin.setPassword("123456");
		//admin.setProfile(ProfileEnum.ROLE_ADMIN);

		User find = userRepository.findByEmail("admin@pibape.com.br");
		if (find == null) {
			userRepository.save(admin);
		}
	}

}
