package ISTP.service;

import ISTP.domain.bloodDonation.BloodTypeCategories;
import ISTP.domain.bloodDonation.accept.Accept;
import ISTP.domain.bloodDonation.accept.AcceptStatusCategories;
import ISTP.domain.bloodDonation.accept.AcceptStatusName;
import ISTP.domain.bloodDonation.request.RequestStatusCategories;
import ISTP.domain.member.Member;
import ISTP.repository.AcceptRepository;
import ISTP.repository.AcceptStatusCategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AcceptService {

    private final AcceptRepository acceptRepository;
    private final AcceptStatusCategoriesRepository acceptStatusCategoriesRepository;

    @Transactional
    public Long save(Accept accept) {
        Accept save = acceptRepository.save(accept);
        return save.getId();
    }

    public Accept findById(Long id) {
        return acceptRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException());
    }

    public AcceptStatusCategories findByAcceptStatus(String acceptStatus) {
        AcceptStatusCategories byAcceptStatus = acceptStatusCategoriesRepository.findByAcceptStatus(acceptStatus);
        return byAcceptStatus;
    }

    @Transactional
    public Long acceptTypeSave(AcceptStatusCategories acceptStatusCategories) {
        AcceptStatusCategories save = acceptStatusCategoriesRepository.save(acceptStatusCategories);
        return save.getId();
    }

    @Transactional
    public void update_cancel(Accept accept) {
        Accept findAccept = findById(accept.getId());
        if (findAccept.getAcceptStatusId().equals(AcceptStatusName.COMPLETE_ID))
        {
            new IllegalArgumentException("완료가 된 요청은 취소로 변경할 수 없습니다.");
        }
        else {
            accept.update_cancel();
        }
    }

    @Transactional
    public void update_request(Accept accept) {
        accept.update_request();
    }

    @Transactional
    public void update_finish(Accept accept) {
        Accept findAccept = findById(accept.getId());
        if(findAccept.getAcceptStatusId().equals(AcceptStatusName.CANCEL_ID))
        {
            new IllegalArgumentException("완료가 된 요청은 취소로 변경할 수 없습니다.");
        }
        else {
            accept.update_finish();
        }
    }

    public List<Accept> findByMember(Member member) {
        return acceptRepository.findByMemberOrderByCreateDateDesc(member);
    }

    public Long count(Member member) {
        return acceptRepository.count(member.getId(), 3L);
    }
}
