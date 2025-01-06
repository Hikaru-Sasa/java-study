// StudentDetail.java
package raisetech.StudentManagement.domain;

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
public class StudentDetail {

  /**S
   * 受講生情報
   */
  private Student student;
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