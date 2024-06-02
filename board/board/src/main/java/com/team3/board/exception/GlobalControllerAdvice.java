package com.team3.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<String> internalServerException(InternalServerException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }



    //최상위 클래스
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e){
        // 대략적으로 어떤 에러 발생했는지 클라이언트 response body 에 날려준다
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
