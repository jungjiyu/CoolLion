package com.team3.board.domain;

import com.team3.board.dto.CommentDTO;
import com.team3.board.dto.PostDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@ToString
@Slf4j
@Getter
@NoArgsConstructor
@Entity
@Table(name="POST")
public class PostEntity extends BaseEntity {



    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JoinColumn(name="user_id")
    @ManyToOne // post 와 user 의 관계 는 n : 1
    private UserEntity user;

    private String title;
    private String content;




    // dto 의 toEntity() 용
    public PostEntity(Long id , String title , String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public PostEntity(Long id , UserEntity user ,  String title , String content){
        this.id = id;
        this.user=user;
        this.title=title;
        this.content = content;
    }

    // 이 클래스의 createEntityWithDto 용
    public static PostEntity createEntityWithDto(PostDTO dto, UserEntity user) {
        // 엔티티 생성 불가한 경우 (POST 메서드가 PATCH 기능하는거 방지하기 위해 )  예외 발생
        if(dto.getId() != null) throw new IllegalArgumentException("id field is null");

        if(dto.getUserId() != user.getId()) throw new IllegalArgumentException("userId dosent match");

        // 엔티티 생성 및 반환
        // dto.toEntity( ) 로 생성하면 안되는게 toEntity( ) 에서는 Article 필드를 따로 초기화 하지 않는 생성자를 사용헀다.
        PostEntity post = new PostEntity(dto.getId(), user, dto.getTitle(), dto.getContent());
        log.info("PostEntity - createEntityWithDto( ) >> "+post.toString());
        return post;
    }
}
