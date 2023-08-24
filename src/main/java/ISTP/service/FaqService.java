package ISTP.service;

import ISTP.domain.help.faq.Faq;
import ISTP.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class FaqService {

    private final FaqRepository faqRepository;

    @Transactional
    public Long save(Faq faq) {
        Faq save = faqRepository.save(faq);
        log.info("{} faq 생성", faq);
        return faq.getId();
    }

}
