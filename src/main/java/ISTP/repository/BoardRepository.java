package ISTP.repository;

import ISTP.domain.board.Board;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @EntityGraph(attributePaths = {"member"})
    @Query("SELECT b FROM Board b WHERE b.isNotice = :isNotice ORDER BY b.createDate DESC")
    List<Board> findAllByBoardType(@Param("isNotice") Boolean isNotice);

    @EntityGraph(attributePaths = {"member"})
    @Query("select b from Board b join fetch b.member m where b.id = :id")
    Optional<Board> findByIdWithMember(@Param("id") Long boardId);

    void deleteByMemberId(Long memberId);


}
