package com.team3.board.dto;

import com.team3.board.domain.CommentEntity;
import com.team3.board.domain.PostEntity;
import com.team3.board.domain.UserEntity;
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
@Schema(name="PostDetail")
public class PostDTO {

    private Long id;
    private Long userId;
    private String title;
    private String content;


    public PostEntity toEntity(){
        PostEntity entity = new PostEntity(this.id,this.title, this.content);
        log.info("PostDTO - toEntity() >> "+entity.toString());
        return entity;
    }

    public static PostDTO toDTO(PostEntity post){
        return  new PostDTO(post.getId(), post.getUser().getId(), post.getTitle(), post.getTitle());
    }

}
