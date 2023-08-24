package ISTP.repository;

import ISTP.domain.bloodDonation.accept.Accept;
import ISTP.domain.bloodDonation.accept.AcceptStatusCategories;
import ISTP.domain.bloodDonation.request.RequestStatusName;
import ISTP.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AcceptRepository extends JpaRepository<Accept, Long> {

    //멤버가 요청
    List<Accept> findByMemberOrderByCreateDateDesc(Member member);
    void deleteByMemberId(Long memberId);

    @Query("select count(a) from Accept a join a.member m where m.id = :memberId and a.acceptStatusId = :statusId")
    Long count(@Param(value = "memberId") Long memberId, @Param(value = "statusId") Long status);

    List<Accept> findAllByAcceptStatusIdAndIsOngoing(Long acceptStatusId, Boolean ongoing);
}
