package raisetech.StudentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.repository.StudentRepository;

@Service

public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList(){
    return repository.search();
  }

  // 10歳以上19歳以下を抽出
  public List<Student> searchStudentListByAge() {
    return repository.searchByAge(10, 19);
  }

  public List<StudentsCourses> searchStudentsCoursesList() {
    return repository.searchCourses();
  }

  // コースの数学を抽出
  public List<StudentsCourses> searchStudentsCoursesListByCourseName() {
    return repository.searchCoursesByCourseName("数学");
  }

  public void saveStudent(Student student) {
    repository.saveStudent(student);
  }

  public void saveStudentsCourse(StudentsCourses studentsCourse) {
    repository.saveCourse(studentsCourse);
  }

}
