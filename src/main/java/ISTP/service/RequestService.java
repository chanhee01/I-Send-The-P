package ISTP.service;

<<<<<<< HEAD
import ISTP.domain.bloodDonation.BloodTypeCategories;
=======
import ISTP.domain.bloodDonation.BloodType;
import ISTP.domain.bloodDonation.accept.Accept;
import ISTP.domain.bloodDonation.accept.AcceptStatus;
>>>>>>> ca6164d3eeb4f35ce76681d5f7e5daccb7048a1b
import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.bloodDonation.request.RequestStatus;
import ISTP.domain.member.Member;
import ISTP.repository.BloodTypeCategoriesRepository;
import ISTP.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final BloodTypeCategoriesRepository bloodTypeCategoriesRepository;

    @Transactional
    public Long save(Request request) {
        Request saveRequest = requestRepository.save(request);
        return saveRequest.getId();
    }

    public Request findById(Long requestId) {
        return requestRepository.findById(requestId).
                orElseThrow(() -> new IllegalArgumentException());
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public Page<Request> findByDESC(Pageable pageable) {
        return requestRepository.findByDESC(pageable);
    }

    @Transactional
    public void changeStatus(Request request) { // 신청에서 진행중으로 바꾸기
        request.update_status();
    }

    @Transactional
    public void changeStatus2(Request request) { // 진행중에서 완료로 바꾸기
        Request findRequest = findById(request.getId());
        if(findRequest.getStatus().equals(RequestStatus.신청))
        {
            new IllegalArgumentException("수혈 수락부터 눌러야합니다.");
        }
        else {
            request.update_finish();
        }
    }

    @Transactional
    public void changeStatus3(Request request) { // 취소누르면 다시 신청으로 바꾸기
        Request findRequest = findById(request.getId());
        if(findRequest.getStatus().equals(RequestStatus.완료))
        {
            new IllegalArgumentException("이미 완료되어서 취소로 변경할 수 없습니다.");
        }
        else {
            request.update_request();
        }
    }

    @Transactional
    public void delete(Long requestId) {
        requestRepository.deleteById(requestId);
    }

    public Request findByOneId(Long requestId) {
        return requestRepository.findOneById(requestId);
    }

    // 멤버가 작성한 요청을 제외한 혈액형 타입으로 요청 리스트 찾는 메서드
    public List<Request> findAllByBloodTypeExcludingMemberRequests(String bloodType, Member member) {
        BloodTypeCategories byBloodType = bloodTypeCategoriesRepository.findByBloodType(bloodType);
        return requestRepository.findAllByBloodTypeIdAndMemberNot(byBloodType.getId(), member);
    }

    //멤버가 작성한 요청 리스트 찾는 메서드
    public List<Request> findAllByMemberNickname(String nickname) {
        return requestRepository.findAllByMemberNickname(nickname);
    }

    public List<Member> findRegionByMemberBloodType(String requestAddress, String bloodType) {
        BloodTypeCategories byBloodType = bloodTypeCategoriesRepository.findByBloodType(bloodType);
        return requestRepository.findRegionByMemberBloodTypeId(requestAddress, byBloodType.getId());
    }

    public List<Member> findAllByMemberBloodType(String bloodType) {
        BloodTypeCategories byBloodType = bloodTypeCategoriesRepository.findByBloodType(bloodType);
        return requestRepository.findAllByMemberBloodType(byBloodType.getId());
    }
}
