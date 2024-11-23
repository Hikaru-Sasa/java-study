package raisetech.StudentManagement.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import raisetech.StudentManagement.Controller.converter.StudentConverter;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

@Controller

public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service,StudentConverter converter)
  {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentsList")
  public String getStudentList(Model model) {
    List<Student> Students = service.searchStudentList();
    List<StudentsCourses> studentsCourses = service.searchStudentsCoursesList();

    model.addAttribute("studentsList",converter.convertStudentDetails(Students, studentsCourses));
    return "studentList";
  }


  @GetMapping("/studentsCourseList")
  public List<StudentsCourses> getStudentsCoursesList() {
    return service.searchStudentsCoursesList();
  }


  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudentsCourse(new StudentsCourses());
    model.addAttribute("studentDetail", studentDetail);
    return "registerStudent";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result, RedirectAttributes redirectAttributes) {

    try {
      service.registeStudent(studentDetail); // registeStudentメソッドを呼び出す

      // Student オブジェクトを作成
      Student student = studentDetail.getStudent();

      // StudentService を使用してデータベースに保存
      service.saveStudent(student);

      // StudentsCourses オブジェクトを作成
      StudentsCourses studentsCourse = studentDetail.getStudentsCourse();
      studentsCourse.setStudentId(Integer.parseInt(student.getId())); // studentId を設定

      // StudentService を使用してデータベースに保存
      service.saveStudentsCourse(studentsCourse);

      System.out.println(student.getName() + "さんが新規受講生として登録されました。");
      System.out.println(student.getName() + "さんが" + studentsCourse.getCourseName() + "コースに登録されました。");
      System.out.println("studentDetail: " + studentDetail); // studentDetail オブジェクトの内容をログ出力
    } catch (Exception e) {
      // エラーメッセージを表示して登録画面に戻る
      result.rejectValue("student", "error.student", "受講生の登録に失敗しました。");
      return "redirect:/studentsList"; // 登録成功後にリダイレクト
    }

    return "redirect:/studentsList";
  }

  @GetMapping("/updateStudent/{id}")
  public String updateStudent(@PathVariable("id") String id, Model model) {
    Student student = service.findStudentById(id); // IDで受講生を検索
    List<StudentsCourses> studentsCourses = service.findStudentsCoursesByStudentId(id); // IDで受講生のコース情報を検索
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentsCourses(studentsCourses);
    model.addAttribute("studentDetail", studentDetail);
    return "updateStudent";
  }

  @PostMapping("/updateStudent/{id}")
  public String updateStudent(@PathVariable("id") String id, @ModelAttribute StudentDetail studentDetail, BindingResult result, RedirectAttributes redirectAttributes) {
    try {
      // Student オブジェクトを更新
      Student student = studentDetail.getStudent();
      student.setId(id); // IDをセット
      service.updateStudent(student);

      // StudentsCourses オブジェクトを更新
      List<StudentsCourses> studentsCourses = studentDetail.getStudentsCourses();
      for (StudentsCourses studentsCourse : studentsCourses) {
        studentsCourse.setStudentId(Integer.parseInt(id)); // studentId を設定
        service.updateStudentsCourse(studentsCourse);
      }

      redirectAttributes.addFlashAttribute("successMessage", "受講生情報を更新しました。"); // フラッシュメッセージを追加
    } catch (Exception e) {
      // エラーメッセージを表示して更新画面に戻る
      result.rejectValue("student", "error.student", "受講生の更新に失敗しました。");
      return "redirect:/updateStudent/" + id; // 更新失敗後にリダイレクト
    }

    return "redirect:/studentsList";
  }

}
