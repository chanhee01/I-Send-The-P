package ISTP.alarmScheduler;

import ISTP.domain.bloodDonation.accept.Accept;
import ISTP.domain.member.Member;
import ISTP.message.MessageService;
import ISTP.service.AcceptService;
import jakarta.transaction.Transactional;
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
    private final MessageService messageService;

    @Scheduled(cron = "0 0 9 * * *") // 매일 아침 9시에 실행
    @Transactional
    public void yourScheduledMethod() {

        List<Accept> all = acceptService.findAcceptByAcceptStatusIdAndiIsOngoing(3L, true);
        for (Accept accept : all) {

            Accept findAccept = acceptService.findById(accept.getId());
            Member member = findAccept.getMember();

            Long bloodDonationTypeId = accept.getRequest().getBloodDonationTypeId();
            LocalDateTime lastModifiedDate = accept.getLastModifiedDate();

            LocalDateTime twoWeeksAfter = lastModifiedDate.plus(14, ChronoUnit.DAYS);
            LocalDateTime eightWeeksAfter = lastModifiedDate.plus(56, ChronoUnit.DAYS);

            if (bloodDonationTypeId == 1L && eightWeeksAfter.isBefore(LocalDateTime.now())) {
                System.out.println("전혈헌혈: 8주 이후의 아침 9시입니다. 알람 발송!");
                messageService.sendOne(member.getPhoneNumber(), member.getName() +
                        " 회원님! 전혈헌혈을 하신 지 8주가 지나 오늘부터 헌혈이 다시 가능합니다!\n" +
                        "http://172.20.10.5:3030/home");
                acceptService.changeIsFinished(accept); // 헌혈 대기기간 완전 종료
                // 알람기능 구현
            }
            else if (bloodDonationTypeId == 2L && twoWeeksAfter.isBefore(LocalDateTime.now())) {
                System.out.println("혈장헌혈: 2주 이후의 아침 9시입니다. 알람 발송!");
                messageService.sendOne(member.getPhoneNumber(), member.getName() +
                        " 회원님! 혈장헌혈을 하신 지 2주가 지나 오늘부터 헌혈이 다시 가능합니다!\n" +
                        "http://172.20.10.5:3030/home");
                acceptService.changeIsFinished(accept); // 헌혈 대기기간 완전 종료
                // 알람기능 구현
            }
            else if(bloodDonationTypeId == 3L && twoWeeksAfter.isBefore(LocalDateTime.now())) {
                System.out.println("혈소판헌혈: 2주 이후의 아침 9시입니다. 알람 발송!");
                messageService.sendOne(member.getPhoneNumber(), member.getName() +
                        " 회원님! 혈소판헌혈을 하신 지 2주가 지나 오늘부터 헌혈이 다시 가능합니다!\n" +
                        "http://172.20.10.5:3030/home");
                acceptService.changeIsFinished(accept); // 헌혈 대기기간 완전 종료
                // 알람기능 구현
            }
            else if(bloodDonationTypeId == 4L && twoWeeksAfter.isBefore(LocalDateTime.now())) {
                System.out.println("혈장_혈소판헌혈: 2주 이후의 아침 9시입니다. 알람 발송!");
                messageService.sendOne(member.getPhoneNumber(), member.getName() +
                        " 회원님! 혈장_혈소판헌혈을 하신 지 2주가 지나 오늘부터 헌혈이 다시 가능합니다!\n" +
                        "http://172.20.10.5:3030/home");
                acceptService.changeIsFinished(accept); // 헌혈 대기기간 완전 종료
                // 알람기능 구현
            }

        }

    }
}
