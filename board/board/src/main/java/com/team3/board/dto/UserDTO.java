package com.team3.board.dto;

import com.team3.board.domain.CommentEntity;
import com.team3.board.domain.UserEntity;
import com.team3.board.util.UserRole;
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
@Schema(name="UserDetail")
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private UserRole role;

    public UserDTO(UserEntity user) {
        this.username = user.getUsername();
        this.id = user.getId();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    public UserEntity toEntity(){
        UserEntity entity = new UserEntity(this.id,this.username, this.password, this.role);
        log.info("CommentDTO - toEntity() >> "+entity.toString());
        return entity;
    }

    public static UserDTO toDTO(UserEntity user){
        return  new UserDTO(user.getId(), user.getUsername(),user.getPassword(), user.getRole());
    }

}
