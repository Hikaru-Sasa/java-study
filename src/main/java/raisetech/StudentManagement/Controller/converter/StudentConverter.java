package raisetech.StudentManagement.Controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.student;
import raisetech.StudentManagement.domain.StudentDtail;

@Component

public class StudentConverter {

  public List<StudentDtail> convertStudentDetails(List<student> students,
      List<StudentsCourses> studentsCourses) {
    List<StudentDtail> studentDtails = new ArrayList();
    students.forEach(student -> {
      StudentDtail studentDtail = new StudentDtail();
      studentDtail.setStudent(student);

      List<StudentsCourses> convertStudentCourses = studentsCourses.stream()
          .filter(studentsCourse -> student.getId().equals(studentsCourse.getStudentId()))
          .collect(Collectors.toList());
      studentDtail.setStudentsCourses(convertStudentCourses);
      studentDtails.add(studentDtail);
    });
    return studentDtails;
  }

}
