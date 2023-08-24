package ISTP.alarmScheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlarmScheduler {

    @Scheduled(cron = "0 0 9 * * *") // 매일 아침 9시에 실행
    public void yourScheduledMethod() {

    }
}
