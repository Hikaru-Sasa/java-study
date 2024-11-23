package raisetech.StudentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.domain.StudentDetail;
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

  public List<StudentsCourses> searchStudentsCoursesList() {
    return repository.searchCourses();
  }

  public void saveStudent(Student student) {
    repository.saveStudent(student);
  }

  public void saveStudentsCourse(StudentsCourses studentsCourse) {
    repository.saveCourse(studentsCourse);
  }

  @Transactional
  public void registeStudent(StudentDetail studentDetail) {
    repository.saveStudent(studentDetail.getStudent());
    repository.saveCourse(studentDetail.getStudentsCourse());
  }

  public Student findStudentById(String id) {
    return repository.findStudentById(id);
  }

  public List<StudentsCourses> findStudentsCoursesByStudentId(String id) {
    return repository.findStudentsCoursesByStudentId(id);
  }

  public void updateStudent(Student student) {
    repository.updateStudent(student);
  }

  public void updateStudentsCourse(StudentsCourses studentsCourse) {
    repository.updateStudentsCourse(studentsCourse);
  }


}
