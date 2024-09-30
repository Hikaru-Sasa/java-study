package raisetech.StudentManagement;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

  private static Map<String, Integer> StudentMap = new HashMap<>();

  @Autowired
  private StudentRepository repository;

  static {
    StudentMap.put("TANAKA", 25);
    StudentMap.put("SATOU", 30);
  }

  public static void main(String[] args) {
    SpringApplication.run(StudentManagementApplication.class, args);
  }

  @GetMapping("/students")
  public String getStudents(@RequestParam("name") String name) {
    student student = repository.searchByName(name);
    return student.getName() + " " + student.getAge() + "歳";
  }

  @PostMapping("/students") // 新しい学生を追加または既存の学生の情報を更新
  public void registerStudent(@RequestParam String name, @RequestParam int age) {
    repository.registerStudent(name, age);
  }

  @PatchMapping("/students")
  public void updateStudent(String name,int age){
    repository.updateStudent(name,age);
  }

  @DeleteMapping("/students")
  public void deleteStudent(String name){
    repository.deleteStudent(name);
  }

  //GET POST
  //GETは取得する、リクエストの結果を受け取る
  //POSTは情報を与える、渡す

}
