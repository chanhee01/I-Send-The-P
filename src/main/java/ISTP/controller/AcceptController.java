package ISTP.controller;

import ISTP.domain.bloodDonation.accept.Accept;
import ISTP.domain.bloodDonation.accept.AcceptStatusCategories;
import ISTP.domain.bloodDonation.accept.AcceptStatusName;
import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.bloodDonation.request.RequestStatusCategories;
import ISTP.domain.member.Member;
import ISTP.dtos.bloodCenter.BloodCenterDTO;
import ISTP.dtos.request.HospitalDto;
import ISTP.service.AcceptService;
import ISTP.service.BloodCenterService;
import ISTP.service.MemberService;
import ISTP.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accept")
public class AcceptController {

    private final AcceptService acceptService;
    private final RequestService requestService;
    private final MemberService memberService;
    private final BloodCenterService bloodCenterService;

    @PostMapping("/{requestId}") // 글에서 수락버튼 누르는 것
    public Long accept(@PathVariable Long requestId) {
        Request request = requestService.findById(requestId);
        Member member = memberService.findById(1L);
        AcceptStatusCategories byAcceptStatus = acceptService.findByAcceptStatus(AcceptStatusName.ACCEPT);
        Accept accept = new Accept(member, request, byAcceptStatus);
        Long savedId = acceptService.save(accept);
        requestService.changeStatus(request);
        return savedId;
    }

    @PostMapping("/{requestId}/change_finish/{acceptId}") // 헌혈 후 완료버튼 누르기
    public void finish(@PathVariable Long requestId, @PathVariable Long acceptId) {
        Accept accept = acceptService.findById(acceptId);
        acceptService.update_finish(accept);
        Request request = requestService.findById(requestId);
        requestService.changeStatus2(request);
        memberService.countPlus(accept.getMember());
    }

    @PostMapping("/{requestId}/change_cancel/{acceptId}") // 수락했는데 취소하는것
    public void cancel(@PathVariable Long requestId, @PathVariable Long acceptId) {
        Accept accept = acceptService.findById(acceptId);
        acceptService.update_cancel(accept);
        Request request = requestService.findById(requestId);
        requestService.changeStatus3(request);
    }

    @GetMapping("/hospital")
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


}
