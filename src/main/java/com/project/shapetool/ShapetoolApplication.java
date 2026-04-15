package com.project.shapetool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShapetoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShapetoolApplication.class, args);
		System.out.println("========================================");
		System.out.println("ShapeTool SOAP Web Service Started!");
		System.out.println("WSDL available at: http://localhost:8080/ws/shapetool.wsdl");
		System.out.println("========================================");
	}

}
