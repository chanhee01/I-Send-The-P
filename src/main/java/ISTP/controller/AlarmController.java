package ISTP.controller;

import ISTP.domain.member.Member;
import ISTP.dtos.alarm.AcceptAndIsReadDto;
import ISTP.dtos.alarm.AlarmSummaryDto;
import ISTP.login.SessionConst;
import ISTP.service.AlarmService;
import ISTP.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alarms")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class AlarmController {

    private final MemberService memberService;
    private final AlarmService alarmService;
    private final HttpSession session;

    @ResponseBody
    @GetMapping("")
    public Map<String, Object> myAlarmList() {
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        boolean alarmStatus = member.isAlarmStatus(); //알람 수신 상태

        List<AcceptAndIsReadDto> allAccept = alarmService.findAllAccept(member.getId());
        List<AlarmSummaryDto> alarmDtoList = new ArrayList<>();
        for (AcceptAndIsReadDto acceptAndIsReadDto : allAccept) {
            AlarmSummaryDto alarmDto = new AlarmSummaryDto(acceptAndIsReadDto);
            alarmDtoList.add(alarmDto);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("alarmStatus", alarmStatus);
        result.put("alarmList", alarmDtoList);
        return result;
    }

    //알람설정 변경
    @PutMapping("/setting")
    public Boolean myAlarmSetting() {
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        memberService.changeAlarm(member);
        return member.isAlarmStatus();
    }

    //알람 확인하면 상태 변경
    @PostMapping("/{memberId}/check/{memberAlarmId}")
    public void isRead(@PathVariable Long memberId, @PathVariable Long memberAlarmId) {
        alarmService.isRead(memberAlarmId);
    }

}
