package com.estc.mediatech;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Mediatech1Application {

	public static void main(String[] args) {
		SpringApplication.run(Mediatech1Application.class, args);}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
