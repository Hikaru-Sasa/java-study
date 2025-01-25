package raisetech.StudentManagement.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.exception.ResourceNotFoundException;

import java.net.URI;
import java.util.List;
import raisetech.StudentManagement.service.StudentService;

/**
 * 受講生の検索や登録、更新などを行う REST API コントローラーの実装クラスです。
 */
@Validated
@RestController
public class StudentControllerImpl implements StudentController {

  private final StudentService studentService;

  @Autowired
  public StudentControllerImpl(StudentService studentService) {
    this.studentService = studentService;
  }

  @Override
  @GetMapping("/students")
  public List<StudentDetail> getStudentList() {
    List<StudentDetail> students = studentService.searchActiveStudentList();
    if (students.isEmpty()) {
      throw new ResourceNotFoundException("No students found.");
    }
    return students;
  }

  @Override
  @GetMapping("/students/{id}")
  public StudentDetail getStudent(@PathVariable @NotBlank @Pattern(regexp = "\\d+") String id) {
    try {
      return studentService.searchStudent(id);
    } catch (ResourceNotFoundException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: " + id, ex);
    }
  }

  @Override
  @GetMapping("/courses")
  public List<StudentsCourses> getStudentsCoursesList() {
    return studentService.searchStudentsCoursesList();
  }

  @Override
  @PostMapping("/students")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody @Valid StudentDetail studentDetail) {
    StudentDetail createdStudent = studentService.registerStudent(studentDetail);
    return ResponseEntity.created(URI.create("/students/" + createdStudent.getStudent().getId()))
        .body(createdStudent);
  }

  @Override
  @PutMapping("/students/{id}")
  public ResponseEntity<String> updateStudent(@PathVariable("id") String id, @RequestBody @Valid StudentDetail studentDetail) {
    try {
      studentDetail.getStudent().setId(id);
      studentService.updateStudent(studentDetail);
      return ResponseEntity.ok("Student updated successfully with ID: " + id);
    } catch (ResourceNotFoundException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with ID: " + id, ex);
    }
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
    return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
  }
}