package raisetech.StudentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {

    repository.search();
    return repository.search();
  }

  public List<Student> searchStudentAge(){
    //課題
    //絞り込みをする。年齢が30代の人を抽出する
    //抽出したリストをコントローラーに返す
    repository.ageSearch();
    return repository.ageSearch();

  }
  public List<StudentsCourses> searchStudentsJava(){
    //課題
    //絞り込み検索でjavaコースのコース情報のみを抽出する
    //抽出したリストをコントローラーに返す
    repository.searchStudentsJavaCourse();
    return repository.searchStudentsJavaCourse();

  }
  public List<StudentsCourses> searchStudentsCoursesList() {
    repository.searchStudentsCourses();
    return repository.searchStudentsCourses();

  }
}
