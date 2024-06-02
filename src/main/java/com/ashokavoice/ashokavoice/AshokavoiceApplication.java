package com.ashokavoice.ashokavoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.ashokavoice.ashokavoice.configuration.SecurityConfig;

@SpringBootApplication
@Import(SecurityConfig.class)
public class AshokavoiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AshokavoiceApplication.class, args);
	}

}
