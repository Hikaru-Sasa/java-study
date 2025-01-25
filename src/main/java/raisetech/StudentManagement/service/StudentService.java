package raisetech.StudentManagement.service;

import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.domain.StudentDetail;

import java.util.List;

public interface StudentService {
  List<Student> searchStudentList();

  List<StudentDetail> searchActiveStudentList();

  List<StudentsCourses> searchStudentsCoursesList();

  void saveStudent(Student student);

  void saveStudentsCourse(StudentsCourses studentsCourse);

  StudentDetail searchStudent(String id);

  StudentDetail registerStudent(StudentDetail studentDetail);

  Student findStudentById(String id);

  List<StudentsCourses> findStudentsCoursesByStudentId(String id);

  void updateStudent(StudentDetail studentDetail);

  void updateStudentsCourse(StudentsCourses studentsCourse);
}