package raisetech.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  private String id;
  private String name;
  private String kanaName;
  private String nickname;
  private String email;
  private String area;
  private int age;
  private String sex;
  private String remark;
  private boolean isDeleted; //論理的削除

  //課題
  //データベースに下二つを追加
  //データベースの構造を変更する
}
