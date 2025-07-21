package raisetech.StudentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生詳細")
@Getter
@Setter
public class Student {


  @Schema(description = "受講生ID", example = "101")
  private int id;

  @NotBlank
  private String name;

  @NotBlank
  private String kanaName;

  @NotBlank
  private String nickname;

  @NotBlank
  @Email
  private String email;

  @Schema(description = "地域（都道府県）", example = "東京")
  @NotBlank
  private String area;

  private int age;

  @NotBlank
  private String sex;

  @Schema(description = "備考・自由記入欄", example = "特になし")
  private String remark;

  @Schema(description = "論理削除フラグ（true＝削除済み）", example = "false")
  private boolean isDeleted;
}
