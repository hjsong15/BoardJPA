package com.study.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.board.entity.Board;
import com.study.board.entity.BoardRepository;

@SpringBootTest
public class BoardTests {

	@Autowired
	BoardRepository boardRepository;

	@Test
	void save() {
		
		// 1. 게시글 파라미터 생성
//	params는 Builder 패턴을 통해 생성된 객체이다. // 생성자와는 달리, 빌더 패턴을 이용하면 어떤 멤버에 어떤 값을 세팅하는지 직관적으로 확인이 가능하다. //TIP-> 인자의 순서 상관없다.
//	만약 생성자를 통해 객체를 만든다면 Board entity = new Board("1번 게시글 제목", "1번 게시글 내용", "혁주", 0, 'N');이런식으로 나온다. 가독성이 떨어짐
		Board params = Board.builder()
				.title("1번 게시글 제목")
				.content("1번 게시글 내용")
				.writer("혁주")
				.hits(0)
				.deleteYn('n')
				.build();
		
		// 2. 게시글 저장
		boardRepository.save(params);

		// 3. 1번 게시글 정보 조회
		Board entity = boardRepository.findById((long) 1).get();
		assertThat(entity.getTitle()).isEqualTo("1번 게시글 제목");
		assertThat(entity.getContent()).isEqualTo("1번 게시글 내용");
		assertThat(entity.getWriter()).isEqualTo("혁주");
	}

	@Test
	void findAll() {

		// 1. 전체 게시글 수 조회
		long boardsCount = boardRepository.count();
		
		// 2. 전체 게시글 리스트 조회
		List<Board> boards = boardRepository.findAll();
	}

	@Test
	void delete() {

		// 1.게시글 조회
		Board entity = boardRepository.findById((long) 1).get();

		// 2.게시글 삭제
		boardRepository.delete(entity);
	}

}
