package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.Student;

/**
 * 受講生情報を扱うリポジトリ
 *
 * 全権検索や単一条件での検索、コース情報の検索が行えるクラス
 */

@Mapper

public interface StudentRepository {

  /**
   * 全権検索します
   *
   * @return　全権検索した受講生情報の一覧
   */

  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM students WHERE age BETWEEN #{minAge} AND #{maxAge}")
  List<Student> searchByAge(int minAge, int maxAge);

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchCourses();

  @Select("SELECT * FROM students_courses WHERE course_name = #{courseName}")
  List<StudentsCourses> searchCoursesByCourseName(String courseName);


}
