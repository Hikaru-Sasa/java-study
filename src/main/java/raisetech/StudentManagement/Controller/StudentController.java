package raisetech.StudentManagement.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import java.util.List;

@Tag(name = "受講生API", description = "受講生情報に対するAPIです")
public interface StudentController {

  @Operation(summary = "受講生一覧取得", description = "登録されている受講生のリストを取得します。")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "取得成功"),
      @ApiResponse(responseCode = "500", description = "サーバーエラー")
  })
  @GetMapping("/students")
  List<StudentDetail> getStudentList();

  @Operation(summary = "受講生情報取得", description = "指定されたIDの受講生情報を取得します。")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "取得成功"),
      @ApiResponse(responseCode = "400", description = "不正なリクエスト"),
      @ApiResponse(responseCode = "404", description = "受講生情報が存在しない")
  })
  @GetMapping("/students/{id}")
  StudentDetail getStudent(
      @Parameter(description = "受講生ID", example = "1")
      @PathVariable @NotBlank @Pattern(regexp = "\\d+") String id
  );

  @Operation(summary = "受講コース一覧取得", description = "登録されている受講コースのリストを取得します。")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "取得成功"),
      @ApiResponse(responseCode = "500", description = "サーバーエラー")
  })
  @GetMapping("/courses")
  List<StudentsCourses> getStudentsCoursesList();

  @Operation(summary = "受講生登録", description = "受講生情報を登録します。")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "登録成功"),
      @ApiResponse(responseCode = "400", description = "不正なリクエスト")
  })
  @PostMapping("/students")
  ResponseEntity<StudentDetail> registerStudent(
      @Parameter(description = "登録する受講生情報")
      @RequestBody @Valid StudentDetail studentDetail
  );

  @Operation(summary = "受講生情報更新", description = "指定されたIDの受講生情報を更新します。")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "更新成功"),
      @ApiResponse(responseCode = "400", description = "不正なリクエスト"),
      @ApiResponse(responseCode = "404", description = "受講生情報が存在しない")
  })
  @PutMapping("/students/{id}")
  ResponseEntity<String> updateStudent(
      @Parameter(description = "受講生ID", example = "1")
      @PathVariable("id") String id,
      @Parameter(description = "更新する受講生情報")
      @RequestBody @Valid StudentDetail studentDetail
  );
}