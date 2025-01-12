// StudentRepository.java
package raisetech.StudentManagement.repository;

import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくrepositoryです。
 * <p>
 * 全権検索や単一条件での検索、コース情報の検索、登録、更新、削除が行える。
 */
@Mapper
public interface StudentRepository {

  /**
   * 全権検索します。
   *
   * @return 全権検索した受講生情報の一覧
   */
  List<Student> findAllStudents();

  /**
   * 削除されていない受講生を検索します。
   *
   * @return 削除されていない受講生情報の一覧
   */
  List<Student> findActiveStudents();

  /**
   * 指定された年齢範囲の受講生を検索します。
   *
   * @param minAge 最小年齢
   * @param maxAge 最大年齢
   * @return 指定された年齢範囲の受講生情報の一覧
   */
  List<Student> findByAge(@Param("minAge") int minAge, @Param("maxAge") int maxAge);

  /**
   * 全ての受講コースを検索します。
   *
   * @return 全ての受講コース情報の一覧
   */
  List<StudentsCourses> findAllCourses();

  /**
   * 指定されたコース名の受講コースを検索します。
   *
   * @param courseName コース名
   * @return 指定されたコース名の受講コース情報の一覧
   */
  List<StudentsCourses> findByCourseName(String courseName);

  /**
   * 受講生情報と受講コース情報を結合して検索します。
   *
   * @return 受講生情報と受講コース情報を結合した一覧
   */
  List<Map<String, Object>> findStudentDetails();

  /**
   * 新しい受講生を登録します。
   *
   * @param student 登録する受講生情報
   */
  void saveStudent(Student student);

  /**
   * 新しい受講コースを登録します。
   *
   * @param studentsCourse 登録する受講コース情報
   */
  void saveCourse(StudentsCourses studentsCourse);

  /**
   * 指定された ID の受講生を検索します。
   *
   * @param id 受講生 ID
   * @return 指定された ID の受講生情報
   */
  Student findStudentById(String id);

  /**
   * 指定された受講生 ID の受講コースを検索します。
   *
   * @param studentId 受講生 ID
   * @return 指定された受講生 ID の受講コース情報の一覧
   */
  List<StudentsCourses> findStudentsCoursesByStudentId(String studentId);

  /**
   * 受講生情報を更新します。
   *
   * @param student 更新する受講生情報
   */
  void updateStudent(Student student);

  /**
   * 受講コース情報を更新します。
   *
   * @param studentsCourse 更新する受講コース情報
   */
  void updateStudentsCourse(StudentsCourses studentsCourse);

  /**
   * 指定された ID の受講コースを削除します。
   *
   * @param id 削除する受講コースの ID
   */
  void deleteStudentsCourse(int id);
}