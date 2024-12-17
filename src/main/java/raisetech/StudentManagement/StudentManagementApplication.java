package raisetech.StudentManagement;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class StudentManagementApplication {

  private static Map<String, Integer> StudentMap = new HashMap<>();


  public static void main(String[] args) {
    SpringApplication.run(StudentManagementApplication.class, args);
  }

}
