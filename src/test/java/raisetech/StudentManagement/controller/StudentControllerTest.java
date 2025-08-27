package raisetech.StudentManagement.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;




@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @SuppressWarnings("removal")
  @MockBean
  private StudentService service;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


  @Test
  void 受講生詳細の一覧検索が実行できて空のリストが返ってくること() throws Exception {
    mockMvc.perform(get("/studentList"))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudentList();
  }
  @Test
  void 指定IDの受講生詳細が取得できること() throws Exception {
     int testId = 1;
     StudentDetail dummy = new StudentDetail();
     dummy.setStudent(new Student());

     when(service.searchStudent(testId)).thenReturn(dummy);

    mockMvc.perform(get("/student/{id}", testId))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudent(testId);
  }
  @Test
  void 受講生情報の登録ができること() throws Exception {
    Student testStudent = new Student();

    testStudent.setName("金木研");               // @NotBlank
    testStudent.setKanaName("カネキケン");       // @NotBlank
    testStudent.setEmail("test@example.com");    // @Email
    testStudent.setNickname("眼帯の喰種");       // 任意ならそのまま
    testStudent.setArea("東京");                 // 任意ならそのまま
    testStudent.setSex("男性");

    StudentDetail request = new StudentDetail();
    request.setStudent(testStudent);

    mockMvc.perform(post("/registerStudent")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(request)))
        .andExpect(status().isOk());

    verify(service, times(1)).registerStudent(any(StudentDetail.class));
  }
  @Test
  void 受講生情報の更新ができること() throws Exception {
    StudentDetail request = new StudentDetail();
    request.setStudent(new Student());

    mockMvc.perform(put("/updateStudent")
        .contentType("application/json")
        .content(new ObjectMapper().writeValueAsString(request)))
        .andExpect(status().isOk());

    verify(service, times(1)).updateStudent(any(StudentDetail.class));
  }



  @Test
  void 受講生詳細の受講生まで適切な値を入力した時に入力チェックに異常が発生しないこと(){
    Student student = new Student();
    student.setName("金木研");
    student.setKanaName("カネキケン");
    student.setNickname("眼帯の喰種");
    student.setEmail("test@example.com");
    student.setArea("東京");
    student.setSex("男性");
    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(0);
  }




}