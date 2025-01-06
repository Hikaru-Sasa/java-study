package raisetech.StudentManagement.repository;

import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
  @Select("SELECT * FROM students")
  List<Student> findAllStudents();

  /**
   * 削除されていない受講生を検索します。
   *
   * @return 削除されていない受講生情報の一覧
   */
  @Select("SELECT * FROM students WHERE is_deleted = false")
  List<Student> findActiveStudents();

  /**
   * 指定された年齢範囲の受講生を検索します。
   *
   * @param minAge 最小年齢
   * @param maxAge 最大年齢
   * @return 指定された年齢範囲の受講生情報の一覧
   */
  @Select("SELECT * FROM students WHERE age BETWEEN #{minAge} AND #{maxAge}")
  List<Student> findByAge(int minAge, int maxAge);

  /**
   * 全ての受講コースを検索します。
   *
   * @return 全ての受講コース情報の一覧
   */
  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> findAllCourses();

  /**
   * 指定されたコース名の受講コースを検索します。
   *
   * @param courseName コース名
   * @return 指定されたコース名の受講コース情報の一覧
   */
  @Select("SELECT * FROM students_courses WHERE course_name = #{courseName}")
  List<StudentsCourses> findByCourseName(String courseName);

  /**
   * 受講生情報と受講コース情報を結合して検索します。
   *
   * @return 受講生情報と受講コース情報を結合した一覧
   */
  @Select("SELECT s.id, s.name, s.name_kana, s.nickname, s.email, s.address, s.age, s.gender, s.remarks, sc.course_name, sc.start_date, sc.end_date "
      + "FROM students s "
      + "LEFT JOIN students_courses sc ON s.id = sc.student_id")
  List<Map<String, Object>> findStudentDetails();

  /**
   * 新しい受講生を登録します。
   *
   * @param student 登録する受講生情報
   */
  @Insert("INSERT INTO students(name,name_kana,nickname,email,address,age,gender,remarks,is_deleted)"
      + "VALUES (#{name}, #{nameKana}, #{nickname}, #{email}, #{address}, #{age}, #{gender}, #{remark}, false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void saveStudent(Student student);

  /**
   * 新しい受講コースを登録します。
   *
   * @param studentsCourse 登録する受講コース情報
   */
  @Insert("INSERT INTO students_courses (student_id, course_name, start_date, end_date) "
      + "VALUES (#{studentId}, #{courseName}, #{startDate}, #{endDate})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void saveCourse(StudentsCourses studentsCourse);

  /**
   * 指定された ID の受講生を検索します。
   *
   * @param id 受講生 ID
   * @return 指定された ID の受講生情報
   */
  @Select("SELECT * FROM students WHERE id = #{id}")
  Student findStudentById(String id);

  /**
   * 指定された受講生 ID の受講コースを検索します。
   *
   * @param studentId 受講生 ID
   * @return 指定された受講生 ID の受講コース情報の一覧
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentsCourses> findStudentsCoursesByStudentId(String studentId);

  /**
   * 受講生情報を更新します。
   *
   * @param student 更新する受講生情報
   */
  @Update(
      "UPDATE students SET name = #{name}, name_kana = #{nameKana}, nickname = #{nickname}, email = #{email}, address = #{address}, age = #{age}, gender = #{gender}, remarks = #{remark}, is_deleted = #{isDeleted} WHERE id = #{id}")
  void updateStudent(Student student);

  /**
   * 受講コース情報を更新します。
   *
   * @param studentsCourse 更新する受講コース情報
   */
  @Update("UPDATE students_courses SET course_name = #{courseName} WHERE id = #{id}")
  void updateStudentsCourse(StudentsCourses studentsCourse);

  /**
   * 指定された ID の受講コースを削除します。
   *
   * @param id 削除する受講コースの ID
   */
  @Delete("DELETE FROM students_courses WHERE id = #{id}")
  void deleteStudentsCourse(int id);
}