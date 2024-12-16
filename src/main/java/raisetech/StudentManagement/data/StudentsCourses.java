package raisetech.StudentManagement.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsCourses {

  private int id;
  private int studentId;
  private String courseName;
  private LocalDateTime startDate;
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