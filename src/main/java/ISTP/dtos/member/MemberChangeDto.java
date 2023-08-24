package ISTP.dtos.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberChangeDto {
    private String phoneNumber;
    private String userNickname;
    private String address;
}
