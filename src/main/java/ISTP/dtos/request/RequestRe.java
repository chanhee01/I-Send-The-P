package ISTP.dtos.request;

import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.member.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRe {
    private String title;
    private String registerNumber;
    private String hospitalName;
    private String hospitalNumber;
    private Long myBloodTypeId;
    private String bloodProduct;
    private LocalDateTime deadline;
    private String relationship;
    private String content;
    private Long bloodDonationTypeId;
}
