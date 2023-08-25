package ISTP.repository;

import ISTP.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByLoginId(String loginId);
    Optional<Member> findOptionalByLoginId(String loginId);
    Member findByNickname(String nickname);

    Member findByPhoneNumber(String phoneNumber);
    Optional<Member> findLoginByLoginId(String loginId);
    Member findByAddress(String address);


    List<Member> findAllByMyBloodTypeIdAndAlarmStatusAndAddress(Long bloodTypeId, boolean alarmStatus, String address);

    Member findOneByMyBloodTypeIdAndAlarmStatus(Long bloodTypeId, boolean alarmStatus);
    @Query("SELECT m FROM Member m WHERE m.count > 10 ORDER BY m.count DESC LIMIT 5")
    List<Member> findTop5ByCount();

    @Query("select m from Member m where m.myBloodTypeId = :bloodTypeId and m.alarmStatus = true")
    List<Member> findAllByBloodTypeIdAndAlarmStatus(@Param(value = "bloodTypeId") Long bloodTypeId);

    Member findOneByLoginId(String loginId);
}
