package com.manuels.principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = IUserDao.class)
public class ManuelsLocucionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManuelsLocucionApplication.class, args);
	}
}