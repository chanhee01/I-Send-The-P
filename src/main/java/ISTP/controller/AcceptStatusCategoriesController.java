package ISTP.controller;

import ISTP.domain.bloodDonation.accept.AcceptStatusCategories;
import ISTP.service.AcceptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/acceptStatusCategories")
public class AcceptStatusCategoriesController {

    private final AcceptService acceptService;
    //디티오로 나중에 변환합시다 ㅎㅎ...
    @GetMapping
    public List<AcceptStatusCategories> acceptStatusCategories() {
        return acceptService.findAcceptStatusAll();
    }
}
