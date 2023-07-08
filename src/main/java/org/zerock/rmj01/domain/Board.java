package org.zerock.rmj01.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "t_board2")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter 
// Setter 사용 안 하는 이유
// -> 객체의 일관성을 유지하기 위해 객체 생성 시점에 값을 넣어줌 
@ToString
public class Board extends BaseEntity{

  // 게시글 번호
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increament
  private Long bno;

  // 게시글 제목
  @Column(length = 200, nullable = false)
  private String title;

  // 게시글 내용
  @Column(length = 1000, nullable = false)
  private String content;

  // 게시글 작성자
  @Column(length = 50, nullable = false)
  private String writer;


  
}
