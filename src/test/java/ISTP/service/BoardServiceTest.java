package ISTP.service;

import ISTP.domain.bloodDonation.BloodTypeCategories;
import ISTP.domain.bloodDonation.BloodTypeName;
import ISTP.domain.board.Board;
import ISTP.domain.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    BoardService boardService;
    @Autowired
    MemberService memberService;
    @BeforeEach
    public void before() {
        BloodTypeCategories bloodTypeCategories1 = new BloodTypeCategories(BloodTypeName.A_PLUS);
        BloodTypeCategories bloodTypeCategories2 = new BloodTypeCategories(BloodTypeName.B_PLUS);
        Member member1 = new Member("loginId1", "password1", "test1", "별명1", 10, true, "010-1111-2222", bloodTypeCategories1.getId(), "aaa@naver.com", "인천시", true);
        Member member2 = new Member("loginId2", "password2", "test2", "별명2", 20, false, "010-3333-4444", bloodTypeCategories2.getId(), "bbb@naver.com", "서울시", true);
        memberService.save(member1);
        memberService.save(member2);
        for(int i = 1; i <= 12; i++) {
            Board board;
            if(i <= 3) {
                board = new Board("title" + i, "content1" + i, true, member1);
            }
            else if(i <= 6) {
                board = new Board("title" + i, "content1" + i, false, member1);
            }
            else if(i <= 9) {

                board = new Board("title" + i, "content1" + i, true, member2);
            }
            else {
                 board = new Board("title" + i, "content1" + i, false, member2);
            }
            boardService.save(board);
        }
    }

    @Test
    public void findById() {
        Board board = new Board("abc", "abc", true, null);
        boardService.save(board);
        Board findBoard = boardService.findById(board.getId());
        assertThat(findBoard).isEqualTo(board);
    }

    @Test
    public void findByIdError() {
        assertThrows(IllegalArgumentException.class, () -> boardService.findById(14L));
    }

    @Test
    public void findAll() {
        List<Board> all = boardService.findAll();
        assertThat(all.size()).isEqualTo(12);
    }

    @Test
    public void findByBoardType() {
        List<Board> boards = boardService.findByBoardType();
        for (Board board : boards) {
            System.out.println("board = " + board);
        }
    }

    @Test
    public void updateBoard() {
        Board board = new Board("abc", "abc", true, null);
        boardService.save(board);
        boardService.updateBoard(board, "updateTitle", "updateContent", false);
        assertThat(board.getTitle()).isEqualTo("updateTitle");
    }

    @Test
    public void deleteBoard() {
        Board board = new Board("abc", "abc", true, null);
        boardService.save(board);
        boardService.deleteBoard(board);
        assertThrows(IllegalArgumentException.class, () -> boardService.findById(board.getId()));
    }

}