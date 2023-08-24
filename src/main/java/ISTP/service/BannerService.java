package ISTP.service;

import ISTP.domain.banner.Banner;
import ISTP.domain.board.Board;
import ISTP.repository.BannerRepository;
import ISTP.repository.BoardRepository;
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

    public List<String> banner() {
        List<Banner> all = bannerRepository.findAll();
        List<String> urls = new ArrayList<>();
        for (Banner banner : all) {
            urls.add(banner.getImgUrl());
        }
        return urls;
    }
}
