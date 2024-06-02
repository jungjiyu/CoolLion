package com.team3.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass// 이걸 붙이면 baseentity 가 테이블을 생성하지 않고 상속 가능하게 한다. 근데 자동으로 업데이트가 되진 않는다
@EntityListeners(AuditingEntityListener.class) // 얘가 업뎃 해줌.
@Getter
@NoArgsConstructor
public class BaseEntity { // 중복되는 얘들을 가지는 엔티티
    @Column(name="created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
