package raisetech.StudentManagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.data.student;
import raisetech.StudentManagement.repository.StudentRepository;

@SpringBootApplication
public class StudentManagementApplication {

  private static Map<String, Integer> StudentMap = new HashMap<>();


  public static void main(String[] args) {
    SpringApplication.run(StudentManagementApplication.class, args);
  }


  //GET POST
  //GETは取得する、リクエストの結果を受け取る
  //POSTは情報を与える、渡す

}
