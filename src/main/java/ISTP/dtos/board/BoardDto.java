package ISTP.dtos.board;


import ISTP.domain.board.Board;
import ISTP.domain.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardDto {

    private String title;
    private String content;
    private boolean isNotice;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private Long memberId;

    public BoardDto(Board board, Member member) {
        title = board.getTitle();
        content = board.getContent();
        isNotice = board.isNotice();
        createDate = board.getCreateDate();
        lastModifiedDate = board.getLastModifiedDate();
        memberId = member.getId();
    }
}