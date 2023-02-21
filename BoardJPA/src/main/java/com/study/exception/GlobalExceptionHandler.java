package com.study.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
//전역 예외 핸들링용 클래스

/*참고 
 *  @ExceptionHandler는 특정클래스에서 발생할 수 있는 예외를 잡아 Throw한다.
    일반적으로 @ExceptionHandler는 @ControllerAdvice가 선언된 클래스에 포함된 메서드에 선언한다.
*/

//컨트롤러 전역에서 발생할 수 있는 예외를 잡아 Throw 해주는 어노테이션
@RestControllerAdvice
//롬복에서 제공해주는 기능으로, 해당 어노테이션이 선언된 클래스에 자동으로 로그 객체를 생성산다.
// log.error( ), log.debug( )와 같이 로깅 관련 메서드를 사용할 수 있다.
@Slf4j
public class GlobalExceptionHandler {

	 @ExceptionHandler(CustomException.class)
	    protected ResponseEntity<ErrorResponse> handleCustomException(final CustomException e) {
	        log.error("handleCustomException: {}", e.getErrorCode());
	        return ResponseEntity
	                .status(e.getErrorCode().getStatus().value())
	                .body(new ErrorResponse(e.getErrorCode()));
	    }

    /*
     * HTTP 405 Exception
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException: {}", e.getMessage());
        return ResponseEntity
                .status(ErrorCode.METHOD_NOT_ALLOWED.getStatus().value())
                .body(new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED));
    }

    /*
     * HTTP 500 Exception
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(final Exception e) {
        log.error("handleException: {}", e.getMessage());
        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }

}