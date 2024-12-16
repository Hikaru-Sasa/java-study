package raisetech.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  private String id;
  private String name;
  private String nameKana;
  private String nickname;
  private String email;
  private String address;
  private int age;
  private String gender;
  private String remark;
  private boolean isDeleted;

  // 明示的に isDeleted() メソッドを定義
  public boolean isDeleted() {
    return isDeleted;
  }

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