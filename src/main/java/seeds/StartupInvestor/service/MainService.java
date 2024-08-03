package seeds.StartupInvestor.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seeds.StartupInvestor.domain.MainPost;
import seeds.StartupInvestor.dto.response.RespMainPost;
import seeds.StartupInvestor.repository.CompanyRepo;
import seeds.StartupInvestor.repository.MainPostReplyRepo;
import seeds.StartupInvestor.repository.MainPostRepo;

@Service
@Transactional
@RequiredArgsConstructor
public class MainService {

    private final CompanyRepo companyRepo;
    private final MainPostRepo mainPostRepo;
    private final MainPostReplyRepo mainPostReplyRepo;

    // main post crud
    public Page<RespMainPost> allPost(int pageNumber) {
        int pageSize = 20; // fixed page size of 20
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // Fetch paginated data from repository
        Page<MainPost> pagedMainPosts = mainPostRepo.findAll(pageable);

        // Transform MainPost entities into RespMainPost DTOs
        List<RespMainPost> allRespMainPosts = new ArrayList<>();
        pagedMainPosts.forEach(mainPost -> allRespMainPosts.add(
            new RespMainPost(
                mainPost.getTitle(),
                mainPost.getDescription(),
                mainPost.getImgData(),
                mainPost.getBookmarkCnt(),
                mainPost.getLikeCnt(),
                false, // 수정 예정
                false //수정 예정
            )
        ));

        // Return paginated result
        return new PageImpl<>(allRespMainPosts, pageable, pagedMainPosts.getTotalElements());
    }
}
