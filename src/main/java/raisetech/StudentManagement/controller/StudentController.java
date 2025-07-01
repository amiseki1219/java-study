package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.repository.StudentRepository;
import raisetech.StudentManagement.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/studentList")
  public List<Student> getStudentList(){
    return service.searchStudentList();
  }

  @GetMapping("/studentCourseList")
  public List<StudentsCourses> getStudentsCoursesList(){
    return service.searchStudentsCoursesList();
  }

  @GetMapping("/studentList/age")
  public List<Student> searchStudentAge(){
    return service.searchStudentAge();
  }

  @GetMapping("/studentCourseList/java")
  public List<StudentsCourses> searchStudentsJava(){
    return service.searchStudentsJava();
  }
}
