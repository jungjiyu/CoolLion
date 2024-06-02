package com.team3.board.repository;

import com.team3.board.domain.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

//    @Query(value="SELECT * FROM comment WHERE post_id = :postId", nativeQuery = true)
    public abstract List<CommentEntity>  findByPostId (Long postId);
    public abstract void deleteByPostId(Long postId);

    public abstract void deleteByParentCommentId(Long parentId);
}
