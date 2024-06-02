package com.team3.board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionMessages {
    ACCESS_DENIED( "권한이 없습니다"),
    NO_USER_FOUND("user가 존재하지 않습니다"),
    NO_POST_FOUND("post가 존재하지 않습니다"),
    CANNOT_FIND_ENTITY("엔티티를 조회할 수 없습니다."),
    NO_PARENT_FOUND("comment 부모가 존재하지 않습니다"),
    NO_COMMENT_FOUND("comment 가 존재하지 않습니다");
    private String message;
}
