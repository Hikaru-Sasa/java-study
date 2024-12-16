package raisetech.StudentManagement.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentsList")
  public String getStudentList(Model model) {
    // キャンセルされていない受講生のみを取得するように変更
    List<Student> students = service.searchActiveStudentList();
    List<StudentsCourses> studentsCourses = service.searchStudentsCoursesList();

    model.addAttribute("studentsList", converter.convertStudentDetails(students, studentsCourses));
    return "studentList";
  }

  @GetMapping("/studentsCourseList")
  public List<StudentsCourses> getStudentsCoursesList() {
    return service.searchStudentsCoursesList();
  }

  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudentsCourses(List.of(new StudentsCourses()));
    model.addAttribute("studentDetail", studentDetail);
    return "registerStudent";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();
    service.saveStudent(student);

    List<StudentsCourses> studentsCourses = studentDetail.getStudentsCourses();
    for (StudentsCourses studentsCourse : studentsCourses) {
      studentsCourse.setStudentId(Integer.parseInt(student.getId()));
      service.saveStudentsCourse(studentsCourse);
    }

    return "redirect:/studentsList";
  }

  @GetMapping("/updateStudent/{id}")
  public String updateStudent(@PathVariable("id") String id, Model model) {
    Student student = service.findStudentById(id);
    List<StudentsCourses> studentsCourses = service.findStudentsCoursesByStudentId(id);
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentsCourses(studentsCourses); // StudentsCourses を追加
    model.addAttribute("studentDetail", studentDetail);
    return "updateStudent";
  }

  @PostMapping("/updateStudent/{id}")
  public String updateStudent(@PathVariable("id") String id,
      @ModelAttribute StudentDetail studentDetail) {

    // Student オブジェクトを更新
    Student student = studentDetail.getStudent();
    student.setId(id); // student の id を設定

    service.updateStudent(studentDetail); // studentDetail を更新

    // StudentsCourses オブジェクトを更新
    List<StudentsCourses> studentsCourses = studentDetail.getStudentsCourses();
    for (StudentsCourses studentsCourse : studentsCourses) {
      studentsCourse.setStudentId(Integer.parseInt(id));
      service.updateStudentsCourse(studentsCourse); // id をキーに更新
    }

    return "redirect:/studentsList";
  }
}