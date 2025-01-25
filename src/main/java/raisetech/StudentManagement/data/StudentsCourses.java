// StudentsCourses.java
package raisetech.StudentManagement.data;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import raisetech.StudentManagement.exception.TestException;

/**
 * 受講生が受講するコースを表すエンティティクラス。
 */
@Getter
@Setter
public class StudentsCourses {

  /**
   * コース ID
   */
  @Positive(message = "コースIDは正の整数で入力してください")
  private int id;

  /**
   * 受講生 ID
   */
  @NotNull(message = "受講生IDは必須です")
  @Positive(message = "受講生IDは正の整数で入力してください")
  private int studentId;

  /**
   * コース名
   */
  @NotBlank(message = "コース名は必須です")
  @Size(max = 255, message = "コース名は255文字以内で入力してください")
  private String courseName;

  /**
   * 開始日
   */
  @NotNull(message = "開始日は必須です")
  @PastOrPresent(message = "開始日は過去または現在の日付を入力してください")
  private LocalDateTime startDate;

  /**
   * 終了日
   */
  @NotNull(message = "終了日は必須です")
  @Future(message = "終了日は未来の日付を入力してください")
  private LocalDateTime endDate;


  @Override
  public String toString() {
    return "StudentsCourses{" +
        "id=" + id +
        ", studentId=" + studentId +
        ", courseName='" + courseName + '\'' +
        ", startDate='" + startDate + '\'' +
        ", endDate='" + endDate + '\'' +
        '}';
  }

  @ExceptionHandler(TestException.class)
  public ResponseEntity<String> handleTestException(TestException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

}