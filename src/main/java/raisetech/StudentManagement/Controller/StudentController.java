package raisetech.StudentManagement.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.student;
import raisetech.StudentManagement.service.StudentService;

@RestController

public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/students")
  public List<student> getStudentList() {
    // リクエストの加工処理
    return service.searchStudentList();
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
