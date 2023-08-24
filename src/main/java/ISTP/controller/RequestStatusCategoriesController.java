package ISTP.controller;

import ISTP.domain.bloodDonation.request.RequestStatusCategories;
import ISTP.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/requestStatusCategories")
public class RequestStatusCategoriesController {

    private final RequestService requestService;

    @GetMapping
    public List<RequestStatusCategories> requestStatusCategories() {
        return requestService.findRequestStatusAll();
    }
}
