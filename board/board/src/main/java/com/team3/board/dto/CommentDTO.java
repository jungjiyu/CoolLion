package com.team3.board.dto;

import com.team3.board.domain.CommentEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
@Slf4j
@Schema(name="CommentDetail")
public class CommentDTO {


    private Long id;
    private Long postId;
    private Long userId;
    private Long parentId;
    private String content;
    private Integer likes;


    public CommentEntity toEntity(){
        CommentEntity entity = new CommentEntity(this.id,this.content, this.likes);
        log.info("CommentDTO - toEntity() >> "+entity.toString());
        return entity;
    }

    public static CommentDTO toDTO(CommentEntity comment){
        return  new CommentDTO(comment.getId(), comment.getPost().getId() ,comment.getUser().getId()
                ,  comment.getParentComment() ==null ? null: comment.getParentComment().getId()
                ,  comment.getContent(), comment.getLikes());
    }

}
