package raisetech.StudentManagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.exception.TestException;
import raisetech.StudentManagement.service.StudentService;

/**
 * 受講生の検索や登録、更新などを行うREST APIとして実行されるControllerです。
 */
@Validated
@RestController
public class StudentController {

  private StudentService service;

  /**
   * コストラクタ
   *
   * @param service 　受講生サービス
   */
  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生詳細の一覧検索です。 全件検索を行うので、条件指定は行わないものになります。
   *
   * @return 受講生詳細一覧（全件）
   */
  @Operation(summary = "一覧検索", description = "受講生の一覧を検索します。")
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }

  /**
   * 受講生詳細検索です。 IDに紐づく任意の受講生の情報を取得します。
   *
   * @param id 　受講生ID
   * @return 受講生詳細
   */
  @Operation(summary = "受講生詳細検索", description = "受講生詳細の検索をします。",
      parameters = {@Parameter(name = "id", description = "受講生のID", required = true)
      },
      responses = {
          @ApiResponse(responseCode = "200", description = "正常に取得"),
          @ApiResponse(responseCode = "404", description = "対象の受講生が存在しない"),
          @ApiResponse(responseCode = "500", description = "サーバー内部エラー")
      }
  )
  @GetMapping("/student/{id}")
  public StudentDetail getStudent(@PathVariable int id) {
    return service.searchStudent(id);
  }

  /**
   * 受講生詳細の登録を行います。
   *
   * @param studentDetail 　受講生詳細
   * @return 実行結果
   */

  @Operation(summary = "受講生登録", description = "受講生の登録をします。",
      responses = {
          @ApiResponse(responseCode = "201", description = "正常に登録"),
          @ApiResponse(responseCode = "400", description = "入力値が不正です。"),
          @ApiResponse(responseCode = "500", description = "サーバー内部エラー")
      })
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody StudentDetail studentDetail) {
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseStudentDetail);
  }


  /**
   * 受講生詳細の更新を行う。 キャンセルフラグの更新もここで行います。（論理削除）
   *
   * @param studentDetail 　受講生詳細
   * @return 実行結果
   */
  @Operation(summary = "受講生更新", description = "受講生の更新をします。",
      responses = {
          @ApiResponse(responseCode = "200", description = "正常に更新できました。"),
          @ApiResponse(responseCode = "400", description = "入力値が不正です。"),
          @ApiResponse(responseCode = "500", description = "サーバー内部エラー")
      })
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
    System.out.println(studentDetail.getStudent());
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました。");
  }

  @Operation(summary = "エラーテスト", description = "テスト用エラーです。")
  @GetMapping("/test-error")
  public String testError() throws TestException {
    throw new TestException("これはテスト用の例外です。");

  }
}

