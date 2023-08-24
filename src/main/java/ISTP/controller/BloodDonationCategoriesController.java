package ISTP.controller;

import ISTP.domain.bloodDonation.BloodDonationCategories;
import ISTP.domain.bloodDonation.BloodTypeCategories;
import ISTP.domain.bloodDonation.accept.AcceptStatusCategories;
import ISTP.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bloodDonationCategories")
public class BloodDonationCategoriesController {

    private final RequestService requestService;

    @GetMapping
    public List<BloodDonationCategories> bloodTypeCategories() {
        return requestService.findBloodDonation();
    }
}
