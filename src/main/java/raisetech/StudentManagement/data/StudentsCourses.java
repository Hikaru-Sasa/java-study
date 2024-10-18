package raisetech.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsCourses {
  private Integer id;
  private Integer studentId;
  private String courseName;
  private String startDate;
  private String endDate;
}