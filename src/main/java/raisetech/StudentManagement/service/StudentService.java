package raisetech.StudentManagement.service;

import java.time.LocalDateTime;
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

  public List<Student> searchStudentList() {
    return repository.search();
  }

  // isDeleted = false のみを検索するメソッドを追加
  public List<Student> searchActiveStudentList() {
    return repository.searchActiveStudents();
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
  public void registerStudent(StudentDetail studentDetail) {
    repository.saveStudent(studentDetail.getStudent());
    for (StudentsCourses studentsCourse : studentDetail.getStudentsCourses()) {
      // 学生IDを設定
      studentsCourse.setStudentId(Integer.parseInt(studentDetail.getStudent().getId()));

      // コースの開始日時と終了日時を設定
      studentsCourse.setStartDate(LocalDateTime.now());
      studentsCourse.setEndDate(LocalDateTime.now().plusYears(1));

      repository.saveCourse(studentsCourse);
    }
  }

  public Student findStudentById(String id) {
    return repository.findStudentById(id);
  }

  public List<StudentsCourses> findStudentsCoursesByStudentId(String id) {
    return repository.findStudentsCoursesByStudentId(id);
  }

  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    for (StudentsCourses studentsCourse : studentDetail.getStudentsCourses()) {
      // 学生IDを設定
      studentsCourse.setStudentId(Integer.parseInt(studentDetail.getStudent().getId()));

      repository.updateStudentsCourse(studentsCourse);
    }
  }

  public void updateStudentsCourse(StudentsCourses studentsCourse) {
    repository.updateStudentsCourse(studentsCourse);
  }
}