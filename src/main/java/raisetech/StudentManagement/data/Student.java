// Student.java
package raisetech.StudentManagement.data;

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
  private String id;
  /**
   * 氏名
   */
  private String name;
  /**
   * 氏名（カナ）
   */
  private String nameKana;
  /**
   * ニックネーム
   */
  private String nickname;
  /**
   * メールアドレス
   */
  private String email;
  /**
   * 住所
   */
  private String address;
  /**
   * 年齢
   */
  private int age;
  /**
   * 性別
   */
  private String gender;
  /**
   * 備考
   */
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