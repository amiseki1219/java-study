package raisetech.StudentManagement.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;

@Component
public class StudentConverter {

    public List<StudentDetail> convertStudentDetails(List<Student> students,
        List<StudentsCourses> studentsCourses) {
      List<StudentDetail> studentDetails = new ArrayList<>();
      students.stream()
          .filter(Objects::nonNull)  // ← 念のためnull弾く
          .forEach(student -> {
            // 🔻 ログ出力（ここ追加）
            System.out.println("=== Student Log ===");
            System.out.println("Student: " + student);
            System.out.println("Student ID: " + student.getId());

            // 🔻 nullチェック
            if (student.getId() == 0) {
              System.out.println("⚠ student.getId() が 0 だよ！");
            }

            StudentDetail studentDetail = new StudentDetail();
            studentDetail.setStudent(student);

            // コース紐付け
            List<StudentsCourses> convertStudentCourses = studentsCourses.stream()
                .filter(studentsCourse -> student.getId() == studentsCourse.getStudentId())
                .collect(Collectors.toList());

            studentDetail.setStudentsCourses(convertStudentCourses);
            studentDetails.add(studentDetail);
          });

      return studentDetails;
    }
  }

