package raisetech.StudentManagement.exception;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(TestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, Object> handleTestException(TestException ex){
    logger.error("TestExceptionが発生しました:{}",ex.getMessage());
    return Map.of("error","BAD_REQUEST","message",ex.getMessage());
  }

  // 追加：見つからない → 404
  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String,Object> handleNotFound(ResourceNotFoundException ex){
    return Map.of("error","NOT_FOUND","message", ex.getMessage());
  }

  //追加：バリデーション → 400
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String,Object> handleValidation(MethodArgumentNotValidException ex){
    return Map.of("error","BAD_REQUEST","message",ex.getMessage());
  }

  //追加：想定外 → 500
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Map<String,Object> handleOther(Exception ex){
    logger.error("Unexpected error", ex);
    return Map.of("error","INTERNAL_SERVER_ERROR","message","Unexpected error");
  }


}
