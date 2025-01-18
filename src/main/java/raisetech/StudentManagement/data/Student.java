// Student.java
package raisetech.StudentManagement.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 受講生を表すエンティティクラス。
 */
@Getter
@Setter
public class Student {

  /**
   * 受講生 ID
   */
  @NotBlank(message = "受講生IDは必須です")
  @Pattern(regexp = "\\d+", message = "受講生IDは数字で入力してください")
  private String id;

  /**
   * 氏名
   */
  @NotBlank(message = "氏名は必須です")
  @Size(max = 255, message = "氏名は255文字以内で入力してください")
  private String name;

  /**
   * 氏名（カナ）
   */
  @NotBlank(message = "氏名（カナ）は必須です")
  @Size(max = 255, message = "氏名（カナ）は255文字以内で入力してください")
  @Pattern(regexp = "^[ァ-ヶー]+$", message = "氏名（カナ）は全角カタカナで入力してください")
  private String nameKana;

  /**
   * ニックネーム
   */
  @Size(max = 255, message = "ニックネームは255文字以内で入力してください")
  private String nickname;

  /**
   * メールアドレス
   */
  @NotBlank(message = "メールアドレスは必須です")
  @Size(max = 255, message = "メールアドレスは255文字以内で入力してください")
  @Email(message = "メールアドレスの形式が不正です")
  private String email;

  /**
   * 住所
   */
  @NotBlank(message = "住所は必須です")
  @Size(max = 255, message = "住所は255文字以内で入力してください")
  private String address;

  /**
   * 年齢
   */
  @NotNull(message = "年齢は必須です")
  @Min(value = 0, message = "年齢は0以上で入力してください")
  private int age;

  /**
   * 性別
   */
  @NotBlank(message = "性別は必須です")
  @Pattern(regexp = "^(男性|女性|その他)$", message = "性別は「男性」「女性」「その他」のいずれかで入力してください")
  private String gender;

  /**
   * 備考
   */
  @Size(max = 1000, message = "備考は1000文字以内で入力してください")
  private String remark;

  /**
   * 削除フラグ
   */
  private boolean isDeleted;

  @Override
  public String toString() {
    return "Student{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", nameKana='" + nameKana + '\'' +
        ", nickname='" + nickname + '\'' +
        ", email='" + email + '\'' +
        ", address='" + address + '\'' +
        ", age=" + age +
        ", gender='" + gender + '\'' +
        ", remark='" + remark + '\'' +
        ", isDeleted=" + isDeleted +
        '}';
  }
}