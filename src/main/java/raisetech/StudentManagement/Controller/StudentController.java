// StudentController.java
package raisetech.StudentManagement.Controller;

import raisetech.StudentManagement.Controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 受講生の検索や登録、更新などを行う REST API として実行されるコントローラー。
 */
@RestController
public class StudentController {

  private final StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * アクティブな受講生リストを取得します。
   *
   * @return アクティブな受講生リスト
   */
  @GetMapping("/studentsList")
  public List<StudentDetail> getStudentList() {
    return service.searchActiveStudentList();
  }

  /**
   * 指定された ID の受講生情報を取得します。
   *
   * @param id 受講生 ID
   * @return 受講生情報
   */
  @GetMapping("/student/{id}")
  public StudentDetail getStudent(@PathVariable String id) {
    // 指定された ID の受講生情報を取得します。
    return service.searchStudent(id);
  }

  /**
   * 受講コースリストを取得します。
   *
   * @return 受講コースリスト
   */
  @GetMapping("/studentsCourseList")
  public List<StudentsCourses> getStudentsCoursesList() {
    // 受講コースリストを取得します。
    return service.searchStudentsCoursesList();
  }

  /**
   * 新規受講生登録画面を表示します。
   *
   * @param model モデル
   * @return 新規受講生登録画面
   */
  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    // 新規 StudentDetail オブジェクトを作成します。
    StudentDetail studentDetail = new StudentDetail();
    // 空の StudentsCourses オブジェクトのリストを設定します。
    studentDetail.setStudentsCourses(List.of(new StudentsCourses()));
    // モデルに StudentDetail オブジェクトを追加します。
    model.addAttribute("studentDetail", studentDetail);
    // 新規受講生登録画面を返します。
    return "registerStudent";
  }

  /**
   * 新規受講生を登録します。
   *
   * @param studentDetail 受講生情報
   * @return 登録結果
   */
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody StudentDetail studentDetail) {
    // 受講生情報を登録します。
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    // 登録結果を返します。
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生更新画面を表示します。
   *
   * @param id    受講生 ID
   * @param model モデル
   * @return 受講生更新画面
   */
  @GetMapping("/updateStudent/{id}")
  public String updateStudent(@PathVariable("id") String id, Model model) {
    // 指定された ID の受講生情報を取得します。
    Student student = service.findStudentById(id);
    // 指定された ID の受講コースリストを取得します。
    List<StudentsCourses> studentsCourses = service.findStudentsCoursesByStudentId(id);
    // StudentDetail オブジェクトを作成します。
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student); // 受講生情報を設定します。
    studentDetail.setStudentsCourses(studentsCourses); // 受講コースリストを設定します。
    // モデルに StudentDetail オブジェクトを追加します。
    model.addAttribute("studentDetail", studentDetail);
    // 受講生更新画面を返します。
    return "updateStudent";
  }

  /**
   * 受講生情報を更新します。
   *
   * @param id            受講生 ID
   * @param studentDetail 受講生情報
   * @return 更新結果
   */
  @PostMapping("/updateStudent/{id}")
  public ResponseEntity<String> updateStudent(
      @PathVariable("id") String id,
      @RequestBody StudentDetail studentDetail) {

    // 受講生 ID を設定します。
    studentDetail.getStudent().setId(id);
    // 受講生情報を更新します。
    service.updateStudent(studentDetail);

    // 更新結果を返します。
    return ResponseEntity.ok("更新処理が成功しました");
  }
}