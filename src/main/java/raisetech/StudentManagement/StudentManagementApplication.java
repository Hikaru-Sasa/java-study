// StudentManagementApplication.java
package raisetech.StudentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring Boot アプリケーションのメインクラス。
 */
@SpringBootApplication
public class StudentManagementApplication {

  private static final Map<String, Integer> STUDENT_MAP = new HashMap<>();

  /**
   * メインメソッド。
   *
   * @param args コマンドライン引数
   */
  public static void main(String[] args) {
    // Spring Boot アプリケーションを起動します。
    SpringApplication.run(StudentManagementApplication.class, args);
  }
}