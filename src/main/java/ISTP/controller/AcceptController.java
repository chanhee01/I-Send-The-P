package ISTP.controller;

import ISTP.domain.bloodDonation.accept.Accept;
import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.member.Member;
import ISTP.dtos.member.MemberChangeDto;
import ISTP.dtos.request.MyAcceptDto;
import ISTP.service.AcceptService;
import ISTP.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accepts")
public class AcceptController {

    private final MemberService memberService;
    private final AcceptService acceptService;

    @GetMapping("/count")
    public Long count(@RequestParam Long memberId) {
        Member findMember = memberService.findById(memberId);
        memberService.findById(findMember.getId());
        return acceptService.count(findMember);
    }


    //내가 요청 수락을 한 긴급헌혈 요청서 목록
    @ResponseBody
    @GetMapping("")
    public List<MyAcceptDto> myAcceptRequestList(@RequestParam Long memberId) {
        Member member = memberService.findById(memberId);
        List<Accept> accepts = acceptService.findByMember(member);
        List<MyAcceptDto> acceptDtos = new ArrayList<>();
        for (Accept accept : accepts) {
            Request request = accept.getRequest();
            MyAcceptDto acceptDto = new MyAcceptDto(request);
            acceptDtos.add(acceptDto);
        }
        return acceptDtos;
    }
}
