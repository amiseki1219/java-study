package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;

/**
 * 受講生情報を扱うリポジトリ。
 *
 * 全件検索や単一条件での検索が行えるクラスです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 全件検索します。
   *
   * @return 全件検索した受講生情報の一覧
   */

  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentsCourses();

  @Insert("INSERT INTO students (id, name, kana_name, nickname, email, area, age, remark, is_deleted)"+
      "VALUES (#{id}, #{name}, #{kanaName}, #{nickname}, #{email}, #{area}, #{age}, #{sex}, #{remark}, #{isDeleted})")
  void insertStudent(Student student);

  @Insert("INSERT INTO students_courses (id, student_id, course_name, course_start_at, course_end_at) " +
      "VALUES (#{id}, #{studentId}, #{courseName}, #{courseStartAt}, #{courseEndAt})")
  void insertStudentCourse(StudentsCourses course);

}

