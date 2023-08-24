package ISTP.controller;

import ISTP.domain.banner.Banner;
import ISTP.domain.member.Member;
import ISTP.dtos.member.BannerDto;
import ISTP.dtos.member.MemberRankingDto;
import ISTP.service.BannerService;
import ISTP.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class HomeController {

    private final MemberService memberService;
    private final BannerService bannerService;

    //랭킹보여줄 DTO
    @ResponseBody
    @GetMapping
    public List<MemberRankingDto> ranking() {
        List<Member> top5ByCount = memberService.findTop5ByCount();
        List<MemberRankingDto> memberRankingDtos = new ArrayList<>();
        for (Member member : top5ByCount) {
            MemberRankingDto memberRankingDto = new MemberRankingDto(member);
            memberRankingDtos.add(memberRankingDto);
        }
        return memberRankingDtos;
    }

    @GetMapping("/banner")
    public List<BannerDto> banner() {
        List<String> banner = bannerService.banner();
        List<BannerDto> bannerDtos = new ArrayList<>();
        for(String url : banner) {
            BannerDto bannerDto = new BannerDto(url);
            bannerDtos.add(bannerDto);
        }
        return bannerDtos;
    }
}
