package com.study.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

//Custom 예외 처리용 Exception 클래스 / ErrorResponse와 마찬가지로 ErrorCode를 통한 객체 생성만 허용한다.
//Unchecked Exception인 RuntimeException을 상속받는 것을 꼭 기억

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {

	private final ErrorCode errorCode;

}
