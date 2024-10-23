package raisetech.StudentManagement.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.Controller.converter.StudentConverter;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.student;
import raisetech.StudentManagement.domain.StudentDtail;
import raisetech.StudentManagement.service.StudentService;

@RestController

public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service,StudentConverter converter)
  {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/students")
  public List<StudentDtail> getStudentList() {
    List<student> students = service.searchStudentList();
    List<StudentsCourses> studentsCourses = service.searchStudentsCoursesList();

    return converter.convertStudentDetails(students, studentsCourses);
  }

  @GetMapping("/studentsAge10")
  public List<student> getStudentListByAge() {
    return service.searchStudentListByAge();
  }

  @GetMapping("/students_courses")
  public List<StudentsCourses> getStudentsCoursesList() {
    return service.searchStudentsCoursesList();
  }

  @GetMapping("/students_coursesMathematics")
  public List<StudentsCourses> getStudentsCoursesListByCourseName() {
    return service.searchStudentsCoursesListByCourseName();
  }

}
