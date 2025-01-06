// StudentService.java
package raisetech.StudentManagement.service;

import raisetech.StudentManagement.Controller.converter.StudentConverter;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 受講生情報に関するビジネスロジックを提供するサービス。
 */
@Service
public class StudentService {

  private final StudentRepository repository;
  private final StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * 全ての受講生情報を検索します。
   * 全件検索を行うので、条件指定は行いません。
   *
   * @return 全ての受講生情報の一覧
   */
  public List<Student> searchStudentList()
  {
    // 全ての受講生情報をリポジトリから取得します。
    return repository.findAllStudents();
  }

  /**
   * 削除されていない受講生のみを検索します。
   *
   * @return 削除されていない受講生情報の一覧
   */
  public List<StudentDetail> searchActiveStudentList() {
    List<Student> studentList = repository.findActiveStudents();
    List<StudentsCourses> studentsCourses = repository.findAllCourses();
    // 削除されていない受講生をリポジトリから取得します。
    return converter.convertStudentDetails(studentList, studentsCourses);
  }

  /**
   * 全ての受講コース情報を検索します。
   *
   * @return 全ての受講コース情報の一覧
   */
  public List<StudentsCourses> searchStudentsCoursesList() {
    // 全ての受講コース情報をリポジトリから取得します。
    return repository.findAllCourses();
  }

  /**
   * 受講生情報を保存します。
   *
   * @param student 保存する受講生情報
   */
  public void saveStudent(Student student) {
    // 受講生情報をリポジトリに保存します。
    repository.saveStudent(student);
  }

  /**
   * 受講コース情報を保存します。
   *
   * @param studentsCourse 保存する受講コース情報
   */
  public void saveStudentsCourse(StudentsCourses studentsCourse) {
    // 受講コース情報をリポジトリに保存します。
    repository.saveCourse(studentsCourse);
  }

  /**
   * 指定された ID の受講生情報と受講コース情報を検索します。
   * IDに紐づく受講生情報を取得したあと、その受講生に紐づく受講生コース情報を取得して設定します。
   * @param id 受講生 ID
   * @return 受講生情報と受講コース情報を含む StudentDetail オブジェクト
   */
  public StudentDetail searchStudent(String id) {
    // 指定された ID の受講生情報をリポジトリから取得します。
    Student student = repository.findStudentById(id);
    // 指定された ID の受講コース情報をリポジトリから取得します。
    List<StudentsCourses> studentsCourses = repository.findStudentsCoursesByStudentId(id);
    // StudentDetail オブジェクトを作成し、受講生情報と受講コース情報を設定します。
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentsCourses(studentsCourses);
    // StudentDetail オブジェクトを返します。
    return studentDetail;
  }

  /**
   * 新しい受講生を登録します。
   *
   * @param studentDetail 登録する受講生情報と受講コース情報を含む StudentDetail オブジェクト
   * @return 登録された受講生情報と受講コース情報を含む StudentDetail オブジェクト
   */
  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    // 受講生情報をリポジトリに保存します。
    repository.saveStudent(studentDetail.getStudent());
    // 受講コース情報リストをループ処理します。
    for (StudentsCourses studentsCourse : studentDetail.getStudentsCourses()) {
      // 受講生 ID を設定します。
      studentsCourse.setStudentId(Integer.parseInt(studentDetail.getStudent().getId()));
      // コースの開始日時を設定します。
      studentsCourse.setStartDate(LocalDateTime.now());
      // コースの終了日時を設定します。
      studentsCourse.setEndDate(LocalDateTime.now().plusYears(1));
      // 受講コース情報をリポジトリに保存します。
      repository.saveCourse(studentsCourse);
    }
    // 登録された受講生情報と受講コース情報を含む StudentDetail オブジェクトを返します。
    return studentDetail;
  }

  /**
   * 指定された ID の受講生情報を検索します。
   *
   * @param id 受講生 ID
   * @return 指定された ID の受講生情報
   */
  public Student findStudentById(String id) {
    // 指定された ID の受講生情報をリポジトリから取得します。
    return repository.findStudentById(id);
  }

  /**
   * 指定された受講生 ID の受講コース情報を検索します。
   *
   * @param id 受講生 ID
   * @return 指定された受講生 ID の受講コース情報の一覧
   */
  public List<StudentsCourses> findStudentsCoursesByStudentId(String id) {
    // 指定された受講生 ID の受講コース情報をリポジトリから取得します。
    return repository.findStudentsCoursesByStudentId(id);
  }

  /**
   * 受講生情報を更新します。
   *
   * @param studentDetail 更新する受講生情報と受講コース情報を含む StudentDetail オブジェクト
   */
  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    // 受講生情報をリポジトリに更新します。
    repository.updateStudent(studentDetail.getStudent());

    // 既存のコース情報をすべて削除します。
    List<StudentsCourses> existingCourses = repository.findStudentsCoursesByStudentId(studentDetail.getStudent().getId());
    for (StudentsCourses existingCourse : existingCourses) {
      repository.deleteStudentsCourse(existingCourse.getId());
    }

    // 新しいコース情報を追加します。
    for (StudentsCourses studentsCourse : studentDetail.getStudentsCourses()) {
      studentsCourse.setStudentId(Integer.parseInt(studentDetail.getStudent().getId()));
      repository.saveCourse(studentsCourse);
    }
  }

  /**
   * 受講コース情報を更新します。
   *
   * @param studentsCourse 更新する受講コース情報
   */
  public void updateStudentsCourse(StudentsCourses studentsCourse) {
    // 受講コース情報をリポジトリに更新します。
    repository.updateStudentsCourse(studentsCourse);
  }
}