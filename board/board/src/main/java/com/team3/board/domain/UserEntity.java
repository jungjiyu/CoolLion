package com.team3.board.domain;

import com.team3.board.dto.CommentDTO;
import com.team3.board.util.UserRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
@Entity
@Table(name="USER")
public class UserEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true) //겹치지 않게 한다.
    private String username;



    private String password;


    @Enumerated(EnumType.STRING)
    private UserRole role;


    // dto 의 toEntity() 용
    public UserEntity(Long id,String username, String password, UserRole role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }




    @Builder
    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = UserRole.ROLE_USER;
    }
}
