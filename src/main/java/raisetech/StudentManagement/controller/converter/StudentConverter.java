package raisetech.StudentManagement.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;

/**
 * 受講生詳を受講生や受講生コース情報、もしくはその逆の変換を行うコンバーターです。
 */
@Component
public class StudentConverter {

  /**
   * 受講生の紐づく受講生コース情報をマッピングする。
   * 受講生コース情報は受講生に対して複数存在するのでループを回して受講生詳細情報を組み立てる。
   *
   * @param studentList 受講生一覧
   * @param studentCourseList 受講生コース情報のリスト
   * @return 受講生詳細情報のリスト
   */
    public List<StudentDetail> convertStudentDetails(List<Student> studentList,
        List<StudentsCourses> studentCourseList) {
      List<StudentDetail> studentDetails = new ArrayList<>();
      studentList.stream()
          .forEach(student -> {
            StudentDetail studentDetail = new StudentDetail();
            studentDetail.setStudent(student);

            List<StudentsCourses> convertStudentCourseList = studentCourseList.stream()
                .filter(studentsCourse -> student.getId() == studentsCourse.getStudentId())
                .collect(Collectors.toList());

            studentDetail.setStudentCourseList(convertStudentCourseList);
            studentDetails.add(studentDetail);
          });

      return studentDetails;
    }
  }

