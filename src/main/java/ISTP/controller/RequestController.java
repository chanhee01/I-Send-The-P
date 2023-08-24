package ISTP.controller;

import ISTP.domain.bloodDonation.accept.Accept;
import ISTP.domain.bloodDonation.accept.AcceptStatusCategories;
import ISTP.domain.bloodDonation.accept.AcceptStatusName;
import ISTP.dtos.bloodCenter.BloodCenterDTO;
import ISTP.dtos.request.*;
import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.member.Member;
import ISTP.message.MessageService;
import ISTP.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/requests")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RequestController {

    private final RequestService requestService;
    private final MemberService memberService;
    //private final BloodCenterService bloodService;
    private final AlarmService alarmService;
    private final MessageService messageService;
    private final AcceptService acceptService;
    private final BloodCenterService bloodCenterService;

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

    @PostMapping// 게시글 올리기
    public Long bloodRequest(@RequestBody RequestRe request) throws Exception {
        Member member = memberService.findById(1L);

        Request savedRequest = new Request(request.getTitle(), request.getRegisterNumber(), request.getHospitalName(),
                request.getHospitalNumber(), request.getMyBloodTypeId(), request.getBloodProduct(), request.getDeadline(),
                request.getRelationship(), request.getContent(), request.getBloodDonationTypeId(),
                request.getBloodDonationTypeId(), member);

        Long savedId = requestService.save(savedRequest);

        // 혈액형 고정, 기간 몰라서 3일로 고정함

        List<Member> allByMemberBloodType = memberService.findAllByBloodTypeIdAndAlarmStatus(request.getBloodDonationTypeId());
        for (Member m : allByMemberBloodType) {
            List<BloodCenterDTO> hospital = bloodCenterService.API(m.getAddress());

            StringBuilder messageBuilder = new StringBuilder();
            List<String> infoList = new ArrayList<>();
            for (BloodCenterDTO bloodCenterDTO : hospital) {
                String phoneNumber = bloodCenterDTO.getPhoneNumber();
                String donationCenter = bloodCenterDTO.getDonationCenter();
                String address = bloodCenterDTO.getAddress();
                String info = "헌혈의집: " + donationCenter + "\n헌혈의집 주소: " + address + "\n전화번호: " + phoneNumber;
                infoList.add(info);
                messageBuilder.append(info).append("\n"); // 문자열 추가
            }

            String finalMessage = messageBuilder.toString();
            System.out.println("finalMessage = " + finalMessage);

            /*messageService.sendOne(m.getPhoneNumber(), "사용자와 같은 혈액형을 가진 환자로부터 헌혈 요청이 도착했습니다.\n" +
                    "헌혈이 가능한 상태라면, 환자에게 희망을 선물해주세요!\n" +
                     finalMessage + "\n자세한 내용은 아래의 링크를 참고하세요\n" +
                    "http://172.20.10.5:3000/api/requests/" + savedRequest.getId()); // 나중에 문자로 교체, 지금은 돈들어가니 안해놓음*/
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

    @PostMapping("/{requestId}/accept") // 글에서 수락버튼 누르는 것
    public Long accept(@PathVariable Long requestId) {
        Request request = requestService.findById(requestId);
        Member member = memberService.findById(1L);
        AcceptStatusCategories byAcceptStatus = acceptService.findByAcceptStatus(AcceptStatusName.ACCEPT);
        Accept accept = new Accept(member, request, byAcceptStatus.getId());
        Long savedId = acceptService.save(accept);
        requestService.changeStatus(request);
        return savedId;
    }

    @PutMapping("/{requestId}/complete") // 헌혈 후 완료버튼 누르기
    public void finish(@PathVariable Long requestId, @PathVariable Long acceptId) {
        Accept accept = acceptService.findById(acceptId);
        acceptService.update_finish(accept);
        Request request = requestService.findById(requestId);
        requestService.changeStatus2(request);
        memberService.countPlus(accept.getMember());
    }

    @PutMapping("/{requestId}/cancle") // 수락했는데 취소하는것
    public void cancel(@PathVariable Long requestId, @PathVariable Long acceptId) {
        Accept accept = acceptService.findById(acceptId);
        acceptService.update_cancel(accept);
        Request request = requestService.findById(requestId);
        requestService.changeStatus3(request);
    }

    @GetMapping("{requestId}/hospitals")
    public List<HospitalDto> hospital() throws Exception {
        Member member = memberService.findById(1L);
        String hospital = member.getAddress();
        List<BloodCenterDTO> api = bloodCenterService.API(hospital);
        List<HospitalDto> hospitalDtos = new ArrayList<>();

        for (BloodCenterDTO bloodCenterDTO : api) {
            String address = bloodCenterDTO.getAddress();
            String donationCenter = bloodCenterDTO.getDonationCenter();
            HospitalDto hospitalDto = new HospitalDto(address, donationCenter);
            hospitalDtos.add(hospitalDto);
        }
        return hospitalDtos;
    }

    //내가 등록한 긴급헌혈 요청서 목록
    @ResponseBody
    @GetMapping("/{memberId}")
    public List<MyRequestDto> myRequestList(@PathVariable Long memberId) {
        Member member = memberService.findById(memberId);
        List<Request> allByMemberNickname = requestService.findAllByMemberNickname(member.getNickname());
        List<MyRequestDto> requestDtos = new ArrayList<>();
        for (Request request : allByMemberNickname) {
            MyRequestDto requestDto = new MyRequestDto(request);
            requestDtos.add(requestDto);
        }
        return requestDtos;
    }
}


