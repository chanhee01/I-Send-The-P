package ISTP.repository;

import ISTP.domain.bloodDonation.accept.Accept;
import ISTP.domain.bloodDonation.accept.AcceptStatus;
import ISTP.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AcceptRepository extends JpaRepository<Accept, Long> {


    //멤버가 요청
    List<Accept> findByMemberOrderByCreateDateDesc(Member member);
    void deleteByMemberId(Long memberId);

    //@Query("select count() from Member m join fetch m.accepts a where a.status = :status")
    //Long count(@Param(value = "status") AcceptStatus acceptStatus);
    // 테이블로 바꾸면 이거 작성
}
