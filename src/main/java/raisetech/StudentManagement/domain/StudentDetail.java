// StudentDetail.java
package raisetech.StudentManagement.domain;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 受講生情報と受講コース情報をまとめて保持するクラス。
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetail {

  @Valid
  /**
   * 受講生情報
   */
  private Student student;

  @Valid
  /**
   * 受講コース情報
   */
  private List<StudentsCourses> studentsCourses;


  @Override
  public String toString() {
    return "StudentDetail{" +
        "student=" + student +
        ", studentsCourses=" + studentsCourses +
        '}';
  }
}