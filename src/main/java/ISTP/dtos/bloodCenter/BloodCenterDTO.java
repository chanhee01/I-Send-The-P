package ISTP.dtos.bloodCenter;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"phoneNumber", "address", "DonationCenter"})
public class BloodCenterDTO {
    private String phoneNumber;
    private String address;
    private String DonationCenter;
}