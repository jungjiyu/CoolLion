package com.team3.board.controller;

import com.team3.board.dto.CommentDTO;
import com.team3.board.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("api/board/comment")
@RestController
@RequiredArgsConstructor
@Tag(name="comment 관리 API")
public class CommentController {

    private final CommentService commentService;

    @DeleteMapping("/{postId}")
    @Operation(
            summary = "특정 게시물에 달린 모든 댓글을 삭제하는 API",
            description = "pathvariable 로 받은 게시물의 id를 기준으로 모든 댓글이 삭제됩니다"
    )
    public ResponseEntity deleteAllComment(@PathVariable Long id){
        commentService.deleteByPostId(id);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{postId}")
    @Operation(
            summary = "특정 게시물에 달린 모든 댓글을 조회하는 API",
            description = "pathvariable 로 받은 게시물의 id를 기준으로 모든 댓글이 조회됩니다"

    )
    public ResponseEntity<List<CommentDTO>> getCommentsById(@PathVariable Long postId ){
        List<CommentDTO> response = commentService.getCommentsById(postId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }




    @PostMapping("")
    @Operation(
            summary = "특정 게시물에 댓글을 다는 API",
            description = "request body 로 받은 데이터를 기반으로 댓글이 생성됩니다. 보내주셔야하는 필드는 아래와 같습니다.\n\n"+
                    " Long postId\n\nLong userId\n\nLong parentId(nullable)\n\nString content\n\nInteger likes(nullable)\n\n"
    )
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO dto){
        log.info("createComment() input >> "+dto.toString());
        CommentDTO createdDto = commentService.createComment(dto.getUserId(), dto.getPostId(),dto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }


    @PatchMapping("/{id}")
    @Operation(
            summary = "특정 댓글을 업데이트하는 API",
            description = "pathvariable 로 받은 게시물의 id와 request body 로 받은 데이터를 기반으로 댓글이 수정됩니다."

    )
    public ResponseEntity<CommentDTO> patchComment(@PathVariable Long id,@Valid @RequestBody CommentDTO dto){
        CommentDTO updated = commentService.patchComment(id, dto);
        return  ResponseEntity.status(HttpStatus.OK).body(updated);
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "특정 댓글을 삭제하는 API",
            description = "pathvariable 로 받은 게시물의 id를 기반으로 댓글이 삭제됩니다."
    )
    public ResponseEntity deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }





}
