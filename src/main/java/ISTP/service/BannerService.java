package ISTP.service;

import ISTP.domain.banner.Banner;
import ISTP.dtos.banner.BanDto;
import ISTP.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BannerService {
    private final BannerRepository bannerRepository;

    public List<BanDto> banner() {
        List<Banner> all = bannerRepository.findAll();
        List<String> urls = new ArrayList<>();
        List<BanDto> dtos = new ArrayList<>();
        for (Banner banner : all) {
            BanDto banDto = new BanDto(banner.getImgUrl(), banner.getTo_Url());
            dtos.add(banDto);
        }
        return dtos;
    }
}
