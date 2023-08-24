package ISTP.repository;

import ISTP.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByLoginId(String loginId);
    Member findByNickname(String nickname);

    Member findByPhoneNumber(String phoneNumber);
    Optional<Member> findLoginByLoginId(String loginId);


    List<Member> findAllByMyBloodTypeIdAndAlarmStatusAndAddress(Long bloodTypeId, boolean alarmStatus, String address);
    @Query("SELECT m FROM Member m WHERE m.count > 10 ORDER BY m.count DESC LIMIT 5")
    List<Member> findTop5ByCount();

}
