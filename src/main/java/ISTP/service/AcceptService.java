package ISTP.service;

import ISTP.domain.bloodDonation.accept.Accept;
import ISTP.domain.bloodDonation.accept.AcceptStatus;
import ISTP.domain.member.Member;
import ISTP.repository.AcceptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AcceptService {

    private final AcceptRepository acceptRepository;

    @Transactional
    public Long save(Accept accept) {
        Accept save = acceptRepository.save(accept);
        return save.getId();
    }

    public Accept findById(Long id) {
        return acceptRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException());
    }

    @Transactional
    public void update_cancel(Accept accept) {
        Accept findAccept = findById(accept.getId());
        if (findAccept.getStatus().equals(AcceptStatus.완료))
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
        if(findAccept.getStatus().equals(AcceptStatus.취소))
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
}
