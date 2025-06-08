package raisetech.StudentManagement;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

  private String name = "Ami Seki";
  private String age = "26";
  private Map<String,String> student;

  public static void main(String[] args) {
    SpringApplication.run(StudentManagementApplication.class, args);
  }

  @GetMapping("/studentInfo")
  public String getStudentInfo() {
    return name + " " + age + "歳";
  }

  @PostMapping("/studentInfo")
  public void setStudentInfo(String name,String age){
    this.name = name;
    this.age = age;
  }

  @PostMapping("/studentName")//nameだけ変更したい
  public void updateStudentName(String name){
    this.name = name;

  }
  @PostMapping("/register")
  public String registerStudent(@RequestParam String name, @RequestParam String age){
    if (this.student == null){
      this.student = new HashMap<>();
    }
    student .put(this.name,this.age);
    return "登録完了：" + name + "(" + age + "歳)";
  }
  @PostMapping("/updateStudent")
  public String updateStudentAge(@RequestParam String name,@RequestParam String newAge){
    if(student == null || !student.containsKey(name)){
      return name + "はまだ登録できていません";
    }
    student.put(name,newAge);
    return name + "の年齢を" + newAge + "に更新しました";
  }
  @GetMapping("/students")
  public Map<String,String> getAllstudent(){
    if(student == null){
      return Map.of();
    }
    return student;
  }
}

