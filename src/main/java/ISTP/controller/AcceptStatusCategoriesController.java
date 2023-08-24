package ISTP.controller;

import ISTP.domain.bloodDonation.accept.AcceptStatusCategories;
import ISTP.dtos.categories.AcceptStatusDto;
import ISTP.service.AcceptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/acceptStatusCategories")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class AcceptStatusCategoriesController {

    private final AcceptService acceptService;
    @GetMapping
    public List<AcceptStatusDto> acceptStatusCategories() {
        List<AcceptStatusDto> acceptStatusDtos = new ArrayList<>();
        List<AcceptStatusCategories> acceptStatusAll = acceptService.findAcceptStatusAll();
        for (AcceptStatusCategories acceptStatusCategories : acceptStatusAll) {
            AcceptStatusDto acceptStatusDto = new AcceptStatusDto(acceptStatusCategories);
            acceptStatusDtos.add(acceptStatusDto);
        }
        return acceptStatusDtos;

    }
}
