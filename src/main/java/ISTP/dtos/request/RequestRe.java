package ISTP.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class RequestRe {

    @NotBlank
    private String sickness;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private LocalDateTime time;
    @NotBlank
    private String number;
    @NotBlank
    private String hospital;
    @NotBlank
    private String relationship;
    @NotBlank
    private String requests_blood_type;
    private String address;
}
