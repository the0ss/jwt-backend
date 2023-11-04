package com.theoss.jwtbackend;

import static com.theoss.jwtbackend.entity.Role.ADMIN;
import static com.theoss.jwtbackend.entity.Role.USER;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.theoss.jwtbackend.entity.RegisterRequest;
import com.theoss.jwtbackend.service.AuthService;

@SpringBootApplication
public class JwtBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtBackendApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.email("admin@mail.com")
					.firstname("Admin")
					.lastname("Admin")
					.password("password")
					.roles(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getToken());

			var manager = RegisterRequest.builder()
					.email("user@mail.com")
					.firstname("User")
					.lastname("User")
					.password("password")
					.roles(USER)
					.build();
			System.out.println("User token: " + service.register(manager).getToken());

		};
	}

}
