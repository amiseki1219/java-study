package raisetech.StudentManagement.controller;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.service.StudentService;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
 class StudentControllerApiExtraTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


  @Autowired
  MockMvc mockMvc;
  @MockitoBean
  StudentService service;

  @Test
  void 指定IDが存在しない場合は404が返ること() throws Exception{
    when(service.searchStudent(999))
        .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND,"student not found"));

    mockMvc.perform(get("/student/{id}",999))
        .andExpect(status().isNotFound());
  }

  @Test
  void 不正なJSONを送ったら400が返ること() throws Exception{
    String brokenJson = "{\"student\":{\"name\":";

    mockMvc.perform(post("/registerStudent")
        .contentType(MediaType.APPLICATION_JSON)
        .content(brokenJson))
        .andExpect(status().isBadRequest());

  }

  @Test
  void Studentの入力チェック_不正値ならバリデーション違反が発生する(){
    Student s = new Student();
    s.setName(" "); //@NotBlank違反
    s.setEmail("bad-email"); //@Email違反

    var violations = validator.validate(s);

    assertThat(violations).extracting(v -> v.getPropertyPath().toString())
        .contains("name","email");


  }



}
