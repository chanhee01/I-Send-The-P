package ISTP.controller;

import ISTP.domain.bloodDonation.BloodTypeCategories;
import ISTP.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bloodTypeCategories")
public class BloodTypeCategoriesController {

    private final RequestService requestService;

    @GetMapping
    public List<BloodTypeCategories>  bloodTypeCategories() {
        return requestService.findBloodType();
    }
}
