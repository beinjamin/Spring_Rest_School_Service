package com.jaures.scolariteservice;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

@Projection(name="p1",types = Student.class)
interface StudentProjection{
	public String getEmail();
	public String getName();
	
}

@RepositoryRestResource
interface StudentRepository extends JpaRepository<Student,Long>{
	@RestResource(path ="/ByName")
	public List<Student> findByNameContains(@Param(value="mc")String mc);
	
}
@RestController
@RequestMapping("/api")
class ScolariteRestController{
	@Autowired
	private StudentRepository studentRepository;
	@GetMapping("/students")
	public List<Student> students(){
		return studentRepository.findAll();
		
	}
	@GetMapping("/students/{id}")
	public Student getOne(@PathVariable(name="id") Long id){
		return studentRepository.findById(id).get();
		
	}
	@PostMapping("/studentsss")
	public Student save(@RequestBody Student student) {
		return studentRepository.save(student);
	}
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
			studentRepository.save(new Student(null,"Jaures","jauresbeinjamin@gmail.com",new Date()));
			studentRepository.save(new Student(null,"ive","ive@gmail.com",new Date()));
			studentRepository.save(new Student(null,"Hugo","hugo@gmail.com",new Date()));
			studentRepository.save(new Student(null,"Audrey","audrey@gmail.com",new Date()));
			studentRepository.save(new Student(null,"sebastien","jauresbeinjamin@gmail.com",new Date()));
			studentRepository.save(new Student(null,"ive","ive@gmail.com",new Date()));
			studentRepository.save(new Student(null,"Hugo","hugo@gmail.com",new Date()));
			studentRepository.save(new Student(null,"Audrey","audrey@gmail.com",new Date()));
            studentRepository.findAll().forEach(System.out::println);
		};
	}

}
