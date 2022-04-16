package com.jaures.scolariteservice;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
class Student {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private Date birthDate;
}

@RepositoryRestResource
interface StudentRepository extends JpaRepository<Student,Long>{
	public List<Student> findByNameContains(@Param(value="mc")String mc);
	
}





@SpringBootApplication
public class ScolariteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScolariteServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(StudentRepository studentRepository , RepositoryRestConfiguration restConfiguration) {
		return args -> {
			restConfiguration.exposeIdsFor(Student.class);
			studentRepository.save(new Student(null,"Jaures","jauresbeinjamin@gmail.com",new Date()));
			studentRepository.save(new Student(null,"ive","ive@gmail.com",new Date()));
			studentRepository.save(new Student(null,"Hugo","hugo@gmail.com",new Date()));
			studentRepository.save(new Student(null,"Audrey","audrey@gmail.com",new Date()));
            studentRepository.findAll().forEach(System.out::println);
		};
	}

}
