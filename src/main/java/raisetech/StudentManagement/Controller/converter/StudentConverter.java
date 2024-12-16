package raisetech.StudentManagement.Controller.converter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.domain.StudentDetail;

@Component
public class StudentConverter {

  public List<StudentDetail> convertStudentDetails(List<Student> students,
      List<StudentsCourses> studentsCourses) {

    if (Optional.ofNullable(students).isEmpty() || students.isEmpty() || Optional.ofNullable(studentsCourses).isEmpty() || studentsCourses.isEmpty()) {
      return Collections.emptyList();
    }

    return students.stream()
        .map(student -> {
          StudentDetail studentDetail = new StudentDetail();
          studentDetail.setStudent(student);
          List<StudentsCourses> convertStudentCourses = studentsCourses.stream()
              .filter(studentsCourse -> student.getId().equals(String.valueOf(studentsCourse.getStudentId())))
              .collect(Collectors.toList());
          studentDetail.setStudentsCourses(convertStudentCourses);
          return studentDetail;
        })
        .collect(Collectors.toList());
  }
}