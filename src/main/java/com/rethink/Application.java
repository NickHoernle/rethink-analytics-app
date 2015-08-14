package com.rethink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
    
	@Bean
	public FilterRegistrationBean corsFilterBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CORSFilter corsFilter = new CORSFilter();
		registrationBean.setFilter(corsFilter);
		registrationBean.setOrder(-1);
		return registrationBean;
	}
}