package com.team3.board.domain;

import com.team3.board.dto.CommentDTO;
import com.team3.board.util.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Slf4j
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Table(name="COMMENT")
@Entity
public class CommentEntity  extends BaseEntity {

    @ManyToOne // comment 와 post 의 관계 는 n : 1
    @JoinColumn(name="post_id") //  @JoinColumn >> FK명을 짓기 위해 사용
    private PostEntity post;


    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;


    @ManyToOne  // 직방 comment 와 대댓글 의 관계 는 n : 1
    @JoinColumn(name="parent_comment_id" , nullable = true)
    CommentEntity parentComment; //댓글이 달린 댓글(부모 댓글)



    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="comment_id")
    private Long id;


    private String content;

    @PositiveOrZero
    private Integer likes;





    // dto 의 toEntity() 용
    public CommentEntity(Long id,String content, Integer likes){
        this.id = id;
        this.content = content;
        this.likes = likes;
    }



    // 이 클래스의 createEntityWithDto 용
    public CommentEntity(PostEntity post , UserEntity user , CommentEntity parentComment ,String content, Integer likes){
        this.post = post;
        this.user= user;
        this.parentComment = parentComment;
        this.content = content;
        this.likes = likes;
    }


    // DTO 에 정의한 toEntity 와는 별개로 생성
        // dto 의 toEntity 는 dto 정보만을 기반으로 entity 를 생성하는 것
        // 이번에 정의한 것은 db 기반으로 만드는 것
    public static CommentEntity createEntityWithDto(CommentDTO dto, PostEntity post , UserEntity user , CommentEntity parentComment) {
        // 엔티티 생성 불가한 경우 (POST 메서드가 PATCH 기능하는거 방지하기 위해 )  예외 발생
        if(dto.getId() != null) throw new IllegalArgumentException("id field is null");

        if(dto.getPostId() != post.getId()) throw new IllegalArgumentException("postId dosent match");
        if(dto.getUserId() != user.getId()) throw new IllegalArgumentException("userId dosent match");
        if(parentComment != null ) {
            if (dto.getParentId() != parentComment.getId()) throw new IllegalArgumentException("parent id dosent match");
        }

        // 엔티티 생성 및 반환
        CommentEntity comment = new CommentEntity(post, user,parentComment , dto.getContent() , dto.getLikes());
        return comment;
    }



    public void patch(CommentDTO dto){ // CommentService 의 patch 전용

        //     private Long id;
        //    private Long postId;
        //    private String username;
        //    private Long parentId;
        //    private String content;
        //    private Integer likes;


        if(dto.getContent() !=null) this.content = dto.getContent();
        if(dto.getLikes() !=null) this.likes = dto.getLikes();
    }

}
