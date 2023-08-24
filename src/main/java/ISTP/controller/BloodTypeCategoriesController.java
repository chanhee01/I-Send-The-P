package ISTP.controller;

import ISTP.domain.bloodDonation.BloodTypeCategories;
import ISTP.dtos.categories.BloodDonationDto;
import ISTP.dtos.categories.BloodTypeDto;
import ISTP.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bloodTypeCategories")
public class BloodTypeCategoriesController {

    private final RequestService requestService;

    @GetMapping
    public List<BloodTypeDto>  bloodTypeCategories() {
        List<BloodTypeDto> bloodTypeDtos = new ArrayList<>();
        List<BloodTypeCategories> bloodType = requestService.findBloodType();
        for (BloodTypeCategories bloodTypeCategories : bloodType) {
            BloodTypeDto bloodTypeDto = new BloodTypeDto(bloodTypeCategories);
            bloodTypeDtos.add(bloodTypeDto);
        }
        return bloodTypeDtos;
    }
}
