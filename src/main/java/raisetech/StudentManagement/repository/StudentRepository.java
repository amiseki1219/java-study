package raisetech.StudentManagement.repository;

import java.util.List;
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

  /**
   *
   * @return 年齢が30歳以上の受講生の情報を返す
   */

  @Select("SELECT * FROM students WHERE age >= 30")
  List<Student> ageSearch();

  /**
   *
   * @return javaコースの受講生情報を返す
   */


  @Select("SELECT * FROM students_courses WHERE course_name = 'java入門コース'")
  List<StudentsCourses> searchStudentsJavaCourse();

}
