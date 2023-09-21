package ISTP.service;

import ISTP.domain.bloodDonation.BloodTypeCategories;
import ISTP.domain.bloodDonation.BloodTypeName;
import ISTP.domain.member.Member;
import ISTP.login.LoginService;
import ISTP.repository.BloodTypeCategoriesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static ISTP.domain.bloodDonation.BloodTypeName.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    BloodTypeCategoriesRepository bloodTypeCategoriesRepository;
    @Autowired
    LoginService loginService;
    @BeforeEach
    public void before() {
        BloodTypeCategories bloodTypeCategories1 = new BloodTypeCategories(A_PLUS);
        BloodTypeCategories bloodTypeCategories2 = new BloodTypeCategories(B_PLUS);
        BloodTypeCategories bloodTypeCategories3 = new BloodTypeCategories(AB_PLUS);
        BloodTypeCategories bloodTypeCategories4 = new BloodTypeCategories(O_PLUS);
        bloodTypeCategoriesRepository.save(bloodTypeCategories1);
        bloodTypeCategoriesRepository.save(bloodTypeCategories2);
        bloodTypeCategoriesRepository.save(bloodTypeCategories3);
        bloodTypeCategoriesRepository.save(bloodTypeCategories4);
        for(int i = 1; i <= 20; i++) {
            Member member = new Member("id" + i, "pass" + i, "nick" + i, "address" + i);
            if(i <= 5) {
                BloodTypeCategories byBloodType = memberService.findByBloodType(A_PLUS);
                member.setMyBloodType(byBloodType);
            }
            else if(i <= 10) {
                BloodTypeCategories byBloodType = memberService.findByBloodType(B_PLUS);
                member.setMyBloodType(byBloodType);
            }
            else if(i <= 15) {
                BloodTypeCategories byBloodType = memberService.findByBloodType(O_PLUS);
                member.setMyBloodType(byBloodType);
            }
            else {
                BloodTypeCategories byBloodType = memberService.findByBloodType(AB_PLUS);
                member.setMyBloodType(byBloodType);;
            }
            memberService.save(member);
        }
    }

    //회원 저장 테스트
    @Test
    @Transactional
    public void save() {
        Member member = new Member("loginId1", "password1");
        Long memberId = memberService.save(member);

        Member findMember = memberService.findById(memberId);
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    public void findAll() {
        Member member1 = new Member("loginId1", "password1");
        Member member2 = new Member("loginId2", "password2");

        memberService.save(member1);
        memberService.save(member2);

        List<Member> allMember = memberService.findAll();
        assertThat(allMember.size()).isEqualTo(22);
    }

    @Test
    public void duplicatedSave() {
        // Given
        Member member1 = new Member("loginId1", "password1");
        Member member2 = new Member("loginId1", "password2");

        // When
        memberService.save(member1);

        // Then
        assertThrows(IllegalArgumentException.class, () -> memberService.save(member2));
    }


    @Test
    public void findPassword() {
        Member member1 = new Member("loginId1", "password1");
        memberService.save(member1);
        String findPassword = memberService.findByPassword(member1.getLoginId());
        assertThat(member1.getPassword()).isEqualTo(findPassword);
    }

    @Test
    public void findNotPassword() {
        Member member1 = new Member("loginId1", "password1");
        memberService.save(member1);
        assertThrows(IllegalArgumentException.class, () -> memberService.findByPassword("notMember"));
    }

    @Test
    public void duplicatedLoginId() {
        Member member1 = new Member("loginId1", "password1");
        memberService.save(member1);

        String loginId = "loginId2";
        assertThat(memberService.duplicatedLoginId(loginId)).isTrue();
    }

    @Test
    public void duplicatedLoginIdError() {
        Member member1 = new Member("loginId1", "password1");
        memberService.save(member1);

        String loginId = "loginId1";
        assertThrows(IllegalArgumentException.class, () -> memberService.duplicatedLoginId(loginId));
    }

    @Test
    public void duplicatedNickname() {
        Member member1 = new Member("loginId1", "password1", "nickname1");
        memberService.save(member1);

        String nickname = "nickname2";
        assertThat(memberService.duplicatedNickname(nickname)).isTrue();
    }

    @Test
    public void duplicatedNicknameError() {
        Member member1 = new Member("loginId1", "password1", "nickname1");
        memberService.save(member1);

        String nickname = "nickname1";
        assertThrows(IllegalArgumentException.class, () -> memberService.duplicatedNickname(nickname));
    }

    @Test
    public void duplicatedPhoneNumber() {
        Member member1 = new Member("loginId1", "password1", "name1", "nickname1", 991021, "01076645199", false);
        memberService.save(member1);
        String phoneNumber = "01076645198";
        assertThat(memberService.duplicatedPhoneNumber(phoneNumber)).isTrue();
    }

    @Test
    public void duplicatedPhoneNumberError() {
        Member member1 = new Member("loginId1", "password1", "name1", "nickname1", 991021, "01076645199", false);
        memberService.save(member1);
        String phoneNumber = "01076645199";
        assertThrows(IllegalArgumentException.class, () -> memberService.duplicatedPhoneNumber(phoneNumber));
    }


    @Test
    public void passwordReEnter() {
        String password = "aaa";
        String rePassword = "aaa";
        boolean result = memberService.passwordReEnter(password, rePassword);
        assertThat(result).isTrue();
    }
    @Test
    public void passwordReEnterError() {
        String password = "aaa";
        String rePassword = "bbb";
        boolean result = memberService.passwordReEnter(password, rePassword);
        assertThat(result).isFalse();
    }

    @Test
    public void login() {
        Member member1 = new Member("loginId1", "password1", "nickname1");
        memberService.save(member1);

        Member loginMember = loginService.login("loginId1", "password1");
        assertThat(loginMember).isEqualTo(member1);
    }

    @Test
    public void loginError() {
        Member member1 = new Member("loginId1", "password1", "nickname1");
        memberService.save(member1);

        Member loginMember = loginService.login("loginId1", "password2");
        assertThat(loginMember).isNull();
    }

    @Test
    public void withdrawal() {
        Member member1 = new Member("loginId1", "password1", "nickname1");
        memberService.save(member1);

        memberService.withdrawal(member1);
        List<Member> all = memberService.findAll();
        assertThat(all).doesNotContain(member1);
    }

    @Test
    public void changeNickname() {
        Member member1 = new Member("loginId1", "password1", "nickname1");
        memberService.save(member1);

        memberService.changeNickname(member1, "changeNick");
        assertThat(member1.getNickname()).isEqualTo("changeNick");
    }

    @Test
    public void changeAddress() {
        Member member1 = new Member("loginId1", "password1", "nickname1", "인천시");
        memberService.save(member1);
        memberService.changeAddress(member1, "서울시");
        assertThat(member1.getAddress()).isEqualTo("서울시");
    }

    @Test
    public void changeAlarm() {
        Member member1 = new Member("loginId1", "password1", "nickname1", "인천시");
        memberService.save(member1);
        assertThat(member1.isAlarmStatus()).isEqualTo(true);
        memberService.changeAlarm(member1);
        assertThat(member1.isAlarmStatus()).isEqualTo(false);
        memberService.changeAlarm(member1);
        assertThat(member1.isAlarmStatus()).isEqualTo(true);
        assertThat(member1.isAlarmStatus()).isTrue();
        memberService.changeAlarm(member1);
        memberService.changeAlarm(member1);
    }

    @Test
    public void countPlus() {
        Member member1 = new Member("loginId1", "password1", "nickname1", "인천시");
        memberService.save(member1);
        assertThat(member1.getCount()).isEqualTo(0);
        memberService.countPlus(member1);
        assertThat(member1.getCount()).isEqualTo(1);
        memberService.countPlus(member1);
        assertThat(member1.getCount()).isEqualTo(2);
    }

    @Test
    public void findAllByMyBloodType() {
        List<Member> allByMyBloodType = memberService.findAlarmMember(A_PLUS, false, "");
        assertThat(allByMyBloodType.size()).isEqualTo(0);
        for (Member member : allByMyBloodType) {
            assertThat(member.getMyBloodTypeId()).isEqualTo(1L);
        }
    }


    @Test
    public void findTop5ByDonationCount() {
        int count = 10;
        List<Member> all = memberService.findAll();
        for (Member member : all) {
            for(int i = 0; i <= count; i++) {
                member.countPlus();
            }
        }
        List<Member> top5ByDonationCount = memberService.findTop5ByCount();
        assertThat(top5ByDonationCount.size()).isEqualTo(5);
    }
}