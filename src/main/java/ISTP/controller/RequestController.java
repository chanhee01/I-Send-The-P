package ISTP.controller;

import ISTP.dtos.request.RequestRe;
import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.member.Member;
import ISTP.dtos.request.RequestDto;
import ISTP.dtos.request.RequestListDto;
import ISTP.message.MessageService;
import ISTP.service.AlarmService;
import ISTP.service.MemberService;
import ISTP.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService requestService;
    private final MemberService memberService;
    //private final BloodCenterService bloodService;
    private final AlarmService alarmService;
    private final MessageService messageService;

    @GetMapping("") // 게시글 리스트
    public Page<RequestListDto> requestList(@PageableDefault(size = 10) Pageable pageable) {

        Page<Request> requests = requestService.findByDESC(pageable);
        Page<RequestListDto> requestDtoList = requests.
                map(r -> new RequestListDto(r));
        return requestDtoList;
    }

    @GetMapping("/{requestId}") // 게시글 리스트에서 하나 클릭해서 들어가기
    public RequestDto requestOne(@PathVariable Long requestId) {
        Request request = requestService.findByOneId(requestId);
        Member member = request.getMember();
        RequestDto requestDto = new RequestDto(request, member);

        return requestDto;
    }

    @PostMapping("")// 게시글 올리기
    public Long bloodRequest(@RequestBody RequestRe request) {
        Member member = memberService.findById(1L);

        Request savedRequest = new Request(request.getTitle(), request.getRegisterNumber(), request.getHospitalName(),
                request.getHospitalNumber(), request.getMyBloodTypeId(), request.getBloodProduct(), request.getDeadline(),
                request.getRelationship(), request.getContent(), request.getBloodDonationTypeId(),
                request.getBloodDonationTypeId(), member);

        Long savedId = requestService.save(savedRequest);

        // 혈액형 고정, 기간 몰라서 3일로 고정함

        List<Member> allByMemberBloodType = memberService.findAllByBloodTypeIdAndAlarmStatus(request.getBloodDonationTypeId());
        for (Member m : allByMemberBloodType) {
            // messageService.sendOne(m.getPhoneNumber(), "헌혈이 필요합니다."); // 나중에 문자로 교체, 지금은 돈들어가니 안해놓음
        }

        //병원명으로 주소 어딘지 알수있게 Hospital 수정해야할듯?
        //=========규혁 추가==========
        alarmService.createAlarmForMember(member, savedRequest);

        /*System.out.println("asdfsda" + acceptMember.getPhoneNumber());
        messageService.sendOne(acceptMember.getPhoneNumber(), "헌혈이 필요합니다.");*/
        return savedId;
    }

    @DeleteMapping("/{requestId}") // 글 삭제
    public void delete_request(@PathVariable Long requestId) {
        requestService.delete(requestId);
    }

}


