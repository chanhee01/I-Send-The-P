package ISTP.dtos.member;

import ISTP.domain.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberEditMyPageDto {

    private String name;
    private Integer age;
    private boolean gender;
    private Long myBloodTypeId;
    private String phoneNumber;
    private String nickname;
    private String address;

    public MemberEditMyPageDto(Member member) {
        name = member.getName();
        age = member.getAge();
        gender = member.isGender();
        myBloodTypeId = member.getMyBloodTypeId();
        phoneNumber = member.getPhoneNumber();
        nickname = member.getNickname();
        address = member.getAddress();
    }
}
