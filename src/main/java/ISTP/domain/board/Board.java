package ISTP.domain.board;

import ISTP.domain.BaseEntity;
import ISTP.domain.member.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString(of = {"id", "title", "content", "boardType"})
public class Board extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;
    private boolean isNotice; //true 면 공지사항 false 면 그 외 인터뷰 등등

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Board(String title, String content, boolean isNotice, Member member) {
        this.title = title;
        this.content = content;
        this.isNotice = isNotice;
        if(member != null) {
            changeBoard(member);
        }
    }

    //==연관관계 메서드==//
    public void changeBoard(Member member) {
        this.member = member;
        member.addBoard(this); // 연관관계 설정
    }

    //게시글 수정하여 업데이트 하는 메서드
    public void updateBoard(String title, String content, Boolean isNotice) {
        this.title = title;
        this.content = content;
        this.isNotice = isNotice;
    }

}
