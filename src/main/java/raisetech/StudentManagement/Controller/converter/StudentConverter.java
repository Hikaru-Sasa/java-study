// StudentConverter.java
package raisetech.StudentManagement.Controller.converter;

import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.domain.StudentDetail;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Student オブジェクトと StudentsCourses オブジェクトを StudentDetail オブジェクトに変換するクラス。
 */
@Component
public class StudentConverter {

  /**
   * StudentDetail オブジェクトを受け取り、StudentDetail オブジェクトのリストに変換します。
   *
   * @param studentDetails 受講生のリスト
   * @return StudentDetail オブジェクトのリスト
   */
  public List<StudentDetail> convertStudentDetails(List<StudentDetail> studentDetails) {

    // 受講生リストまたは受講コースリストが null または空の場合、空のリストを返します。
    if (Objects.isNull(studentDetails) || studentDetails.isEmpty()) {
      return Collections.emptyList();
    }

    // 受講生リストをストリームに変換し、各受講生に対して StudentDetail オブジェクトを作成します。
    return studentDetails.stream()
        .map(studentDetail -> {
          // StudentDetail オブジェクトを作成します。
          studentDetail.setStudent(studentDetail.getStudent()); // 受講生情報を設定します。

          // 受講生 ID に一致する受講コースをフィルタリングしてリストを作成します。
          List<StudentsCourses> convertStudentCourses = studentDetail.getStudentsCourses().stream()
              .filter(studentsCourse -> Integer.parseInt(studentDetail.getStudent().getId()) == (studentsCourse.getStudentId()))
              .collect(Collectors.toList());
          studentDetail.setStudentsCourses(convertStudentCourses); // 受講コースリストを設定します。
          return studentDetail; // StudentDetail オブジェクトを返します。
        })
        .collect(Collectors.toList()); // StudentDetail オブジェクトのリストに変換します。
  }

  public List<StudentDetail> convertStudentDetails(List<Student> studentList, List<StudentsCourses> studentsCourses) {
    if (Objects.isNull(studentList) || studentList.isEmpty() || Objects.isNull(studentsCourses) || studentsCourses.isEmpty()) {
      return Collections.emptyList();
    }

    return studentList.stream()
        .map(student -> {
          StudentDetail studentDetail = new StudentDetail();
          studentDetail.setStudent(student);

          List<StudentsCourses> filteredCourses = studentsCourses.stream()
              .filter(course -> Integer.parseInt(student.getId()) == course.getStudentId())
              .collect(Collectors.toList());
          studentDetail.setStudentsCourses(filteredCourses);

          return studentDetail;
        })
        .collect(Collectors.toList());
  }
}