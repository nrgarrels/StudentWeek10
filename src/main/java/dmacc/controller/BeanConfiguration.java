package dmacc.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dmacc.beans.StudentData;


@Configuration
public class BeanConfiguration {

	@Bean
	public StudentData student() {
		StudentData bean = new StudentData();
		bean.setFirstName("Nick");
		bean.setLastName("Garrels");
		return bean;
	}
}
