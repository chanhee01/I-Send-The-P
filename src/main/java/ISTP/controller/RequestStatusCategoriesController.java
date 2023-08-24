package ISTP.controller;

import ISTP.domain.bloodDonation.request.RequestStatusCategories;
import ISTP.dtos.categories.RequestStatusDto;
import ISTP.dtos.request.RequestDto;
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
@RequestMapping("/api/requestStatusCategories")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class RequestStatusCategoriesController {

    private final RequestService requestService;

    @GetMapping
    public List<RequestStatusDto> requestStatusCategories() {
        List<RequestStatusDto> requestStatusDtos = new ArrayList<>();
        List<RequestStatusCategories> requestStatusAll = requestService.findRequestStatusAll();
        for (RequestStatusCategories requestStatusCategories : requestStatusAll) {
            RequestStatusDto requestStatusDto = new RequestStatusDto(requestStatusCategories);
            requestStatusDtos.add(requestStatusDto);
        }
        return requestStatusDtos;
    }
}
