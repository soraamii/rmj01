package org.zerock.rmj01.repository;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.rmj01.domain.Board;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;


@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

  @Autowired
  private BoardRepository boardRepository;

  @Test
  public void testInsert() {

    for(int i = 0; i < 100; i++) {

      Board board = Board.builder()
        .title("Sample Title" + i)
        .content("Sample Content" + i)
        .writer("User00" + ( i % 10))
        .build();

      boardRepository.save(board);

    }
  }

  // @Query를 사용한 조회 테스트
  @Test
  public void testQuery1_1() {

    List<Board> list = boardRepository.listTitle("1");

    log.info("==================================");
    log.info("list size: " + list.size());
    log.info(list);
  }

  // @Query를 사용하여 Object 배열로 조회 테스트
  @Test
  public void testQuery1_2() {

    List<Object[]> list = boardRepository.listTitle2("2");

    log.info("==================================");
    log.info("list size: " + list.size());
    list.forEach(arr -> log.info(Arrays.toString(arr)));


  }

  // @Query + Pageable 조회 테스트
  @Test
  public void testQuery1_3() {

    Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

    Page<Object[]> result = boardRepository.listTitle2("1", pageable);

    log.info("result: " + result);

  }

  // @Query 사용하여 update test
  @Test
  @Transactional // 테스트 코드의 독립성 보장
  @Commit // test 코드에서 modify는 Commit 걸어줘야 함 아니면 rollback함
  public void testModify() {

    Long bno = 98L;

    String title = "Modified Test";

    int count = boardRepository.modifyTitle(title, bno);

    log.info("count: " + count);

  }

  // findByContentContaining으로 content에 1이 들어있는 데이터를 페이징하여 조회
  @Test
  public void testContent() {

    Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

    Page<Board> result = boardRepository.findByContentContaining("1", pageable);

    log.info("result: " + result);

  }


  
}
