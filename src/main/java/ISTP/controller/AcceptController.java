package ISTP.controller;

import ISTP.domain.bloodDonation.accept.Accept;
import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.member.Member;
import ISTP.dtos.bloodCenter.BloodCenterDTO;
import ISTP.dtos.member.MemberChangeDto;
import ISTP.dtos.request.MyAcceptDto;
import ISTP.login.SessionConst;
import ISTP.service.AcceptService;
import ISTP.service.BloodCenterService;
import ISTP.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accepts")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AcceptController {

    private final MemberService memberService;
    private final AcceptService acceptService;
    private final HttpSession session;
    private final BloodCenterService bloodCenterService;

    @GetMapping("/count")
    public Long count() {
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        memberService.findById(member.getId());
        return acceptService.count(member);
    }


    // 내가 요청 수락을 한 긴급헌혈 요청서 목록
    @ResponseBody
    @GetMapping("")
    public List<MyAcceptDto> myAcceptRequestList() {
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<Accept> accepts = acceptService.findByMember(member);
        List<MyAcceptDto> acceptDtos = new ArrayList<>();
        for (Accept accept : accepts) {
            Request request = accept.getRequest();
            MyAcceptDto acceptDto = new MyAcceptDto(request);
            acceptDtos.add(acceptDto);
        }
        return acceptDtos;
    }

    @GetMapping("/hospital")
    public List<BloodCenterDTO> hospital() throws Exception {
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<BloodCenterDTO> hospital = bloodCenterService.API(member.getAddress());

        return hospital;
    }
}
