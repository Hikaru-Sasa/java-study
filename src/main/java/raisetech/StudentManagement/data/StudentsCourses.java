// StudentsCourses.java
package raisetech.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 受講生が受講するコースを表すエンティティクラス。
 */
@Getter
@Setter
public class StudentsCourses {

  /**
   * コース ID
   */
  private int id;
  /**
   * 受講生 ID
   */
  private int studentId;
  /**
   * コース名
   */
  private String courseName;
  /**
   * 開始日
   */
  private LocalDateTime startDate;
  /**
   * 終了日
   */
  private LocalDateTime endDate;


  @Override
  public String toString() {
    return "StudentsCourses{" +
        "id=" + id +
        ", studentId=" + studentId +
        ", courseName='" + courseName + '\'' +
        ", startDate='" + startDate + '\'' +
        ", endDate='" + endDate + '\'' +
        '}';
  }
}