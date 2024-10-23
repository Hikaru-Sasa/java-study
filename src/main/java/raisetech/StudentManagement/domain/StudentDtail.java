package raisetech.StudentManagement.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.student;
import raisetech.StudentManagement.repository.StudentRepository;


@Getter
@Setter
public class StudentDtail {

  private student student;
  private List<StudentsCourses> studentsCourses;

}
