package com.jaures.scolariteservice;

import javax.persistence.GenerationType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@Entity
class Student {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private Date birthDate;
}





@SpringBootApplication
public class ScolariteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScolariteServiceApplication.class, args);
	}

}
