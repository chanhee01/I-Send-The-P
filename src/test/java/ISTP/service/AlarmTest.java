package ISTP.service;

import ISTP.alarmScheduler.AlarmScheduler;
import ISTP.domain.bloodDonation.accept.Accept;
import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.TaskScheduler;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootTest
public class AlarmTest {

    @Autowired
    BloodCenterService bloodCenterService;

    @Autowired
    AlarmScheduler alarmScheduler;

    @Autowired
    MemberService memberService;

    @Autowired
    RequestService requestService;

    @Autowired
    AcceptService acceptService;

    @Autowired
    private TaskScheduler taskScheduler;


    @Test
    public void alarm() {
        Member member = new Member("iddd1", "pass1", "nick111", "aaaaaa",
                20, true, "01041539032", 1L, "asdfdsaf", "부산", true);


        Member member1 = new Member("iddd2", "pass2", "nick22", "aaaaaa2",
                20, true, "01041539032", 1L, "asdfdsaf2", "부산", true);

        memberService.save(member);
        memberService.save(member1);


        Request request = new Request("sadfas", "asdf", "sadf", "sadf", 1L,
                "asdf", LocalDateTime.now().plusDays(3), "친척", "cs", 1L,
                1L, member);

        requestService.save(request);

        Accept accept = new Accept(member1, request, 1L);

        acceptService.save(accept);

        acceptService.update_finish(accept);


        LocalDateTime testDateTime = LocalDateTime.now().plusDays(14).withHour(9).withMinute(0).withSecond(0);

        Clock fixedClock = Clock.fixed(testDateTime.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        LocalDateTime now = LocalDateTime.now(fixedClock);

        taskScheduler.schedule(() -> {
            // 여기에 알람이 발생하는 로직을 추가하세요.
            // 알람이 발생해야하는 조건과 동작을 구현합니다.
            // 예를 들어, 알람이 발생되는지 여부를 확인하는 테스트 코드를 추가합니다.
            System.out.println("알람이 발생했습니다.");
        }, fixedClock.instant());

        System.out.println(now);
    }
}
