package raisetech.StudentManagement.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
  public String newStudent(Model model){
    model.addAttribute("studentDetail", new StudentDetail());
    return "registerStudent";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if (result.hasErrors()) {
      return "registerStudent";
    }

    if (studentDetail == null || studentDetail.getStudent() == null) {
      result.rejectValue("student", "error.student", "受講生情報が正しく入力されていません。");
      return "registerStudent";
    }

    try {
      Student student = studentDetail.getStudent();

      service.saveStudent(student);

      StudentsCourses studentsCourse = studentDetail.getStudentsCourse();
      studentsCourse.setStudentId(Integer.parseInt(student.getId())); // studentId を設定

      service.saveStudentsCourse(studentsCourse); // StudentsCourses オブジェクトを保存

      System.out.println(student.getName() + "さんが新規受講生として登録されました。");
      System.out.println(student.getName() + "さんが" + studentsCourse.getCourseName() + "コースに登録されました。");
    } catch (Exception e) {
      result.rejectValue("student", "error.student", "受講生の登録に失敗しました。");
      return "registerStudent";
    }

    return "redirect:/studentsList";
  }
}
