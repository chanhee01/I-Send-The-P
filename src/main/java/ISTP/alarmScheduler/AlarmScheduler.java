package ISTP.alarmScheduler;

import ISTP.domain.bloodDonation.accept.Accept;
import ISTP.service.AcceptService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AlarmScheduler {
    private final AcceptService acceptService;
    @Scheduled(cron = "0 0 9 * * *") // 매일 아침 9시에 실행
    public void yourScheduledMethod() {
        List<Accept> all = acceptService.findAcceptByAcceptStatusIdAndiIsOngoing(3L, true);
        for (Accept accept : all) {

            Long bloodDonationTypeId = accept.getRequest().getBloodDonationTypeId();
            LocalDateTime lastModifiedDate = accept.getLastModifiedDate();

            LocalDateTime twoWeeksAfter = LocalDateTime.now().plus(14, ChronoUnit.DAYS);
            LocalDateTime eightWeeksAfter = LocalDateTime.now().plus(56, ChronoUnit.DAYS);

            if (bloodDonationTypeId == 1L && lastModifiedDate.isAfter(eightWeeksAfter)) {
                System.out.println("전혈헌혈: 8주 이후의 아침 9시입니다. 알람 발송!");
                acceptService.changeIsFinished(accept); // 헌혈 대기기간 완전 종료
                // 알람기능 구현
            }
            else if (bloodDonationTypeId == 2L && lastModifiedDate.isAfter(twoWeeksAfter)) {
                System.out.println("혈장헌혈: 2주 이후의 아침 9시입니다. 알람 발송!");
                acceptService.changeIsFinished(accept); // 헌혈 대기기간 완전 종료
                // 알람기능 구현
            }
            else if(bloodDonationTypeId == 3L && lastModifiedDate.isAfter(twoWeeksAfter)){
                System.out.println("혈소판헌혈: 2주 이후의 아침 9시입니다. 알람 발송!");
                acceptService.changeIsFinished(accept); // 헌혈 대기기간 완전 종료
                // 알람기능 구현
            }
            else if(bloodDonationTypeId == 4L && lastModifiedDate.isAfter(twoWeeksAfter)) {
                System.out.println("혈장_혈소판헌혈: 2주 이후의 아침 9시입니다. 알람 발송!");
                acceptService.changeIsFinished(accept); // 헌혈 대기기간 완전 종료
                // 알람기능 구현
            }

        }

    }
}
