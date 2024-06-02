package com.team3.board.service;

import com.team3.board.domain.CommentEntity;
import com.team3.board.domain.PostEntity;
import com.team3.board.domain.UserEntity;
import com.team3.board.dto.CommentDTO;
import com.team3.board.exception.ExceptionMessages;
import com.team3.board.exception.InternalServerException;
import com.team3.board.repository.CommentRepository;
import com.team3.board.repository.PostRepository;
import com.team3.board.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    @Transactional
    public void deleteByPostId(Long postId) {
        commentRepository.deleteByPostId(postId);
    }


    public List<CommentDTO> getCommentsById(Long postId) {
        List<CommentEntity> entityList= commentRepository.findByPostId(postId);
        List<CommentDTO> dtoList = entityList.stream().map(entity->CommentDTO.toDTO(entity)).collect(Collectors.toList());
        return dtoList;
    }


    @Transactional
    public CommentDTO createComment(Long userId , Long postId, CommentDTO dto) {
        // 1. 해당 postId 를 가지는 게시글이 존재하는지 우선 검색
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(()->  new InternalServerException(ExceptionMessages.NO_POST_FOUND.getMessage())); // post 없을 시 예외 발생

        log.info("createComment - postRepository.findById(postId) >> "+post.toString());

        // 2. 해당 id 를 가지는 사용자가 존재하는지 우선 검색
        UserEntity user = userRepository.findById(userId).orElseThrow(()->  new InternalServerException(ExceptionMessages.NO_USER_FOUND.getMessage()));
        log.info("createComment - userRepository.findById(userId) >> "+user.toString());


        // 3. 해당 commentId 를 가지는 부모 comment 가 존재하는지 우선 검색
        CommentEntity parentComment = null;
        if(dto.getParentId() != null) {
            parentComment = commentRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new InternalServerException(ExceptionMessages.NO_PARENT_FOUND.getMessage())); // 부모 comment 없을 시 예외 발생
            log.info("createComment - commentRepository.findById(dto.getParentId()) >> " + parentComment.toString());
        }
        // 4. 댓글 엔티티 생성
            //  createEntityWithDto(CommentDTO dto, PostEntity post , UserEntity user , CommentEntity parentComment)
        CommentEntity comment = CommentEntity.createEntityWithDto(dto, post, user ,parentComment );
        CommentEntity created = commentRepository.save(comment);
        return CommentDTO.toDTO(created);
    }



    @Transactional
    public CommentDTO patchComment(Long id, CommentDTO dto) {
        // 1. 해당 id 를 가지는 댓글이 존재하는지 우선 검색
        CommentEntity comment = commentRepository.findById(id)
                .orElseThrow(()-> new InternalServerException(ExceptionMessages.NO_COMMENT_FOUND.getMessage()));

        //2. dto 기반 null 아닌 값으로 수정
        comment.patch(dto);
        CommentEntity updated = commentRepository.save(comment);
        return CommentDTO.toDTO(updated);
    }

    @Transactional
    public void deleteComment(Long id) {
        // 1. 해당 id 를 가지는 댓글이 존재하는지 우선 검색
        CommentEntity comment = commentRepository.findById(id)
                .orElseThrow(()-> new InternalServerException(ExceptionMessages.NO_COMMENT_FOUND.getMessage()));

        //2. 해당 댓글의 대댓들을 삭제
        commentRepository.deleteByParentCommentId(id);

        //3. 해당 댓글 자체를 삭제
        commentRepository.deleteById(id);



    }
}
