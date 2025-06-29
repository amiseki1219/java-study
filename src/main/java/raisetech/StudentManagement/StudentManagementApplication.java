package raisetech.StudentManagement;

import java.util.List;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan("raisetech.StudentManagement")
@RestController
public class StudentManagementApplication {

	@Autowired
	private StudentRepository repository;
	@Autowired
	private StudentCourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@GetMapping("/studentList")
	public List<Student> getStudentList(){
		return repository.search();
	}

	@GetMapping("/studentCourseList")
	public List<StudentCourse> getCourseList(){
		return courseRepository.findAll();
	}

}
