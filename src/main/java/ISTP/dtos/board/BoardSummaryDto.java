package ISTP.dtos.board;

import ISTP.domain.board.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardSummaryDto {

    private String title;
    private LocalDateTime createdTime;
    private String nickname;
    private Long id;

    public BoardSummaryDto(Board board) {
        this.title = board.getTitle();
        this.createdTime = board.getCreateDate();
        this.nickname = board.getMember().getNickname();
        this.id = board.getId();
    }

}
