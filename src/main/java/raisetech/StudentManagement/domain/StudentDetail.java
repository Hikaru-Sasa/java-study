package raisetech.StudentManagement.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.Student;

@Getter
@Setter
public class StudentDetail {

  private Student student;
  private List<StudentsCourses> studentsCourses;

  public Student getStudent() {
    return student;
  }

  @Override
  public String toString() {
    return "StudentDetail{" +
        "student=" + student +
        ", studentsCourses=" + studentsCourses +
        '}';
  }
}