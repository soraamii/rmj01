package org.zerock.rmj01.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass 
// 공통 매핑 정보가 필요할 때 사용, 해당 어노테이션이 선언된 클래스는 Entity가 아님
// 직접 생성해서 사용될 일이 없기 때문에 대부분 추상 클래스로 만들어짐
@Getter
@EntityListeners(value = {AuditingEntityListener.class} )
// JPA Entity에서 이벤트가 발생할 때마다 특정 로직을 실행시킬 수 있는 어노테이션
public abstract class BaseEntity {

  // 해당 컬럼 값을 별로의 클래스로 생성하는 이유
  // ->대부분의 테이블에서 공통적으로 사용될 컬럼이라 상속받아 사용하는 것이 효율적

    // 게시글 작성일자
  @CreatedDate // Entity가 생성되어 저장될 때 시간이 자동으로 저장되는 어노테이션
  @Column(updatable = false) // 재실행 시에 컬럼 값이 계속 변경되는 문제가 생김, update를 막아서 해결하기 위한 어노테이션
  private LocalDateTime regDate;

  // 게시글 수정일자
  @LastModifiedDate // Entity의 값이 변경될 때 시간이 자동으로 저장
  private LocalDateTime modDate;
  
}
