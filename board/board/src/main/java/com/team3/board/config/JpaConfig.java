package com.team3.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 데이터를 생성하거나 수정할 때, 누가, 언제 해당 작업을 했는지 알기 위해 사용
@Configuration
public class JpaConfig {
}
