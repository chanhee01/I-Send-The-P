package ISTP.controller;

import ISTP.domain.bloodDonation.BloodDonationCategories;
import ISTP.domain.bloodDonation.BloodTypeCategories;
import ISTP.domain.bloodDonation.accept.AcceptStatusCategories;
import ISTP.dtos.categories.BloodDonationDto;
import ISTP.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bloodDonationCategories")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class BloodDonationCategoriesController {

    private final RequestService requestService;

    @GetMapping
    public List<BloodDonationDto> bloodTypeCategories() {
        List<BloodDonationDto> bloodDonationDtos = new ArrayList<>();
        List<BloodDonationCategories> bloodDonation = requestService.findBloodDonation();
        for (BloodDonationCategories bloodDonationCategories : bloodDonation) {
            BloodDonationDto bloodDonationDto = new BloodDonationDto(bloodDonationCategories);
            bloodDonationDtos.add(bloodDonationDto);
        }
        return bloodDonationDtos;
    }
}
