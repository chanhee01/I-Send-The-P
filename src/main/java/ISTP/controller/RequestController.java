package ISTP.controller;

import ISTP.domain.bloodDonation.BloodTypeCategories;
import ISTP.domain.bloodDonation.request.RequestStatusCategories;
import ISTP.domain.bloodDonation.request.RequestStatusName;
import ISTP.dtos.request.RequestRe;
import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.member.Member;
import ISTP.dtos.request.RequestDto;
import ISTP.dtos.request.RequestListDto;
import ISTP.service.AlarmService;
import ISTP.service.MemberService;
import ISTP.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static ISTP.domain.bloodDonation.BloodTypeName.*;
import static ISTP.domain.bloodDonation.request.RequestStatusName.APPLICATION;
import static ISTP.domain.bloodDonation.request.RequestStatusName.APPLICATION_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/request")
public class RequestController {

    private final RequestService requestService;
    private final MemberService memberService;
    //private final BloodCenterService bloodService;
    private final AlarmService alarmService;

    @GetMapping("/list") // 게시글 리스트
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

    @PostMapping("/blood")// 게시글 올리기
    public Long bloodRequest(@Validated @RequestBody RequestRe request) {
        Member member = memberService.findById(1L);
        BloodTypeCategories bloodType_A_PLUS = memberService.findByBloodType(A_PLUS);
        RequestStatusCategories byRequestStatus = requestService.findByRequestStatus(APPLICATION);
        Request savedRequest = new Request(member, request.getSickness(), request.getTitle(), request.getContent(),
                LocalDateTime.now().plusDays(3), request.getNumber(), request.getHospital(), byRequestStatus,
                bloodType_A_PLUS, request.getRelationship(), request.getRequests_blood_type(), request.getAddress());
        // 혈액형 고정, 기간 몰라서 3일로 고정함

        Long savedId = requestService.save(savedRequest);

        /*if(savedRequest.getAddress().equals("전체")) { // 전체 선택하면 전체의 사람들한테 보내짐
            List<Member> allByMemberBloodType = requestService.findAllByMemberBloodType(savedRequest.getBloodType());
            for (Member m : allByMemberBloodType) {
                System.out.println("전체 = " + m.getLoginId()); // 나중에 문자로 교체, 지금은 돈들어가니 안해놓음
            }
        }
        else { // 전체가 아니라 서울, 인천 이렇게 입력하면 해당 지역에 사는 사람만 알림 가기
            List<Member> regionByMemberBloodType = requestService.findRegionByMemberBloodType(savedRequest.getAddress(), savedRequest.getBloodType());
            for (Member m : regionByMemberBloodType) {
                System.out.println("지역만 = " + m.getLoginId()); // 나중에 문자로 교체, 지금은 돈들어가니 안해놓음
            }
        }*/

        //병원명으로 주소 어딘지 알수있게 Hospital 수정해야할듯?
        //=========규혁 추가==========
        alarmService.createAlarmForMember(member, savedRequest);
        return savedId;
    }

    @DeleteMapping("/delete/{requestId}") // 글 삭제
    public void delete_request(@PathVariable Long requestId) {
        requestService.delete(requestId);
    }

   /* @GetMapping("api")
    public List<BloodCenterDTO> api(@RequestParam String region) throws Exception {
        return bloodService.API(region);
    }*/
}


