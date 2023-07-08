package org.zerock.rmj01.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.rmj01.domain.Board;

// DB layer 접근자
// 인터페이스 생성 후에 JpaRepository<Entity 클래스, PK 타입>을 상속
// -> 기본적인 CRUD 메소드 자동 실행
public interface BoardRepository extends JpaRepository<Board, Long> {

  // title을 매개변수로 데이터 조회
  // @Param 사용 -> 메소드의 파라미터와 쿼리의 매개변수를 매핑
  @Query("select b from Board b where b.title like %:title%")
  List<Board> listTitle(@Param("title") String title);

  // Object 배열로 bno와 title만 조회
  @Query("select b.bno, b.title from Board b where b.title like %:title%")
  List<Object[]> listTitle2(@Param("title") String title);

  // 페이징 조회
  @Query("select b.bno, b.title from Board b where b.title like %:title%")
  Page<Object[]> listTitle2(@Param("title") String title, Pageable pageable);

  // 수정
  // int: 업데이트 되는 데이터 수
  @Modifying
  @Query("update Board b set b.title = :title where b.bno = :bno")
  int modifyTitle(@Param("title") String title, @Param("bno") Long bno);

  // findBy로 content를 포함하는 모든 entity 조회
  // 메소드 이름을 findBy로 만들면 쿼리 메소드로 인식
  Page<Board> findByContentContaining(String content, Pageable pageable);
  
}
