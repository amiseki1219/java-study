package raisetech.StudentManagement.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.sun.source.tree.ModuleTree.ModuleKind;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;

  private StudentService sut;

  @BeforeEach
  void before(){
    sut= new StudentService(repository, converter);
  }

  @Test
  void 受講生詳細の一覧検索_リポジトリとコンバーターの処理が適切に呼び出されていること(){

    List<Student>studentList = new ArrayList<>();
    List<StudentsCourses> studentCourseList = new ArrayList<>();

    Mockito.when(repository.search()).thenReturn(studentList);
    Mockito.when(repository.searchStudentCourseList()).thenReturn(studentCourseList);

    sut.searchStudentList();
    
    verify(repository, times(1)).search();
    verify(repository, times(1)).searchStudentCourseList();
    verify(converter, times(1)).convertStudentDetails(studentList,studentCourseList);

  }

  @Test
  void 受講生詳細の検索_受講生とコース情報が取得できること() {
    int studentId = 1;
    Student dummyStudent = new Student();

    dummyStudent.setId(studentId);
    dummyStudent.setName("山田太郎");
    List<StudentsCourses> dummyCourseList = new ArrayList<>();
    StudentsCourses dummyCourse = new StudentsCourses();
    dummyCourse.setCourseName("Javaコース");
    dummyCourseList.add(dummyCourse);

    Mockito.when(repository.searchStudent(studentId)).thenReturn(dummyStudent);
    Mockito.when(repository.searchStudentCourse(studentId)).thenReturn(dummyCourseList);

    StudentDetail result = sut.searchStudent(studentId);

    Assertions.assertEquals(dummyStudent, result.getStudent(),"受講生情報が一致していること");
    Assertions.assertEquals(dummyCourseList, result.getStudentCourseList(),"コース情報が一致していること");

    verify(repository, times(1)).searchStudent(studentId);
    verify(repository, times(1)).searchStudentCourse(studentId);

  }
  @Test
  void 受講生詳細の登録_受講生とコースが登録されて返ってくること(){
    Student dummyStudent = new Student();
    dummyStudent.setId(10);
    dummyStudent.setName("あみまる");

    StudentsCourses course1 = new StudentsCourses();
    course1.setCourseName("Javaコース");

    StudentsCourses course2 = new StudentsCourses();
    course2.setCourseName("AWSコース");

    List<StudentsCourses> coursesList = List.of(course1,course2);

    StudentDetail studentDetail = new StudentDetail(dummyStudent,coursesList);

    StudentDetail result = sut.registerStudent(studentDetail);

    verify(repository, times(1)).registerStudent(dummyStudent);
    verify(repository, times(1)).registerStudentCourse(course1);
    verify(repository, times(1)).registerStudentCourse(course2);
    
  }
  @Test
  void 受講生詳細の更新_受講生とコースが正しく更新されること(){
    Student dummyStudent = new Student();
    dummyStudent.setId(777);
    dummyStudent.setName("宇宙たろう");

    StudentsCourses course1 = new StudentsCourses();
    course1.setCourseName("Web制作コース");

    StudentsCourses course2 = new StudentsCourses();
    course2.setCourseName("マーケティングコース");

    List<StudentsCourses> coursesList = List.of(course1,course2);

    StudentDetail studentDetail = new StudentDetail(dummyStudent,coursesList);

    sut.updateStudent(studentDetail);

    verify(repository, times(1)).updateStudent(dummyStudent);
    verify(repository, times(1)).updateStudentCourses(course1);
    verify(repository, times(1)).updateStudentCourses(course2);

  }


}