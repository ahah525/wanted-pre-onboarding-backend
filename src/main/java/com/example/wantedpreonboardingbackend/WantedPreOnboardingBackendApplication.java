package com.example.wantedpreonboardingbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing	// @CreatedDate, @LastModifiedDate 활성화
public class WantedPreOnboardingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WantedPreOnboardingBackendApplication.class, args);
	}

}
