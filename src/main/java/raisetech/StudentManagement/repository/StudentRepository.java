package raisetech.StudentManagement.repository;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.Student;

/**
 * 受講生情報を扱うリポジトリ
 * <p>
 * 全権検索や単一条件での検索、コース情報の検索が行えるクラス
 */
@Mapper
public interface StudentRepository {

  /**
   * 全権検索します
   *
   * @return 全権検索した受講生情報の一覧
   */
  @Select("SELECT * FROM students")
  List<Student> search();

  // isDeleted = false の学生を検索するメソッドを追加
  @Select("SELECT * FROM students WHERE is_deleted = false")
  List<Student> searchActiveStudents();

  @Select("SELECT * FROM students WHERE age BETWEEN #{minAge} AND #{maxAge}")
  List<Student> searchByAge(int minAge, int maxAge);

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchCourses();

  @Select("SELECT * FROM students_courses WHERE course_name = #{courseName}")
  List<StudentsCourses> searchCoursesByCourseName(String courseName);


  @Select("SELECT s.id, s.name, s.name_kana, s.nickname, s.email, s.address, s.age, s.gender, s.remarks, sc.course_name, sc.start_date, sc.end_date "
      + "FROM students s "
      + "LEFT JOIN students_courses sc ON s.id = sc.student_id")
  List<Map<String, Object>> searchStudentDetails();


  @Insert("INSERT INTO students(name,name_kana,nickname,email,address,age,gender,remarks,is_deleted)"
      + "VALUES (#{name}, #{nameKana}, #{nickname}, #{email}, #{address}, #{age}, #{gender}, #{remark}, false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void saveStudent(Student student);



  @Insert("INSERT INTO students_courses (student_id, course_name, start_date, end_date) "
      + "VALUES (#{studentId}, #{courseName}, #{startDate}, #{endDate})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void saveCourse(StudentsCourses studentsCourse);



  @Select("SELECT * FROM students WHERE id = #{id}")
  Student findStudentById(String id);

  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentsCourses> findStudentsCoursesByStudentId(String studentId);



  @Update(
      "UPDATE students SET name = #{name}, name_kana = #{nameKana}, nickname = #{nickname}, email = #{email}, address = #{address}, age = #{age}, gender = #{gender}, remarks = #{remark}, is_deleted = #{isDeleted} WHERE id = #{id}")
  void updateStudent(Student student);

  @Update(
      "UPDATE students_courses SET course_name = #{courseName},WHERE id = #{id}")
  void updateStudentsCourse(StudentsCourses studentsCourse);
}