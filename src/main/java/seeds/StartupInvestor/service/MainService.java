package seeds.StartupInvestor.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    private static final int PAGE_SIZE = 20;

    private final CompanyRepo companyRepo;
    private final MainPostRepo mainPostRepo;
    private final MainPostReplyRepo mainPostReplyRepo;

    // main post crud
    public Page<RespMainPost> allPost(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);

        // Fetch paginated data from repository
        Page<MainPost> pagedMainPosts = mainPostRepo.findAll(pageable);

        // Transform MainPost entities into RespMainPost DTOs
        return getRespMainPosts(pagedMainPosts, pageable);
    }

    public Page<RespMainPost> findMainPostsByCriteria(
        String institutionType,
        String preferredBusinessArea,
        String preferredTechnology,
        String preferredInvestmentStage,
        String businessArea,
        String technology,
        String investmentStage,
        String region,
        Boolean investmentActive,
        String query,
        int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);

        Specification<MainPost> specs = Specification.where(null);
        if (institutionType != null) specs = specs.and(MainPostSpecifications.hasInstitutionType(institutionType));
        if (preferredBusinessArea != null) specs = specs.and(MainPostSpecifications.hasPreferredBusinessArea(preferredBusinessArea));
        if (preferredTechnology != null) specs = specs.and(MainPostSpecifications.hasPreferredTechnology(preferredTechnology));
        if (preferredInvestmentStage != null) specs = specs.and(MainPostSpecifications.hasPreferredInvestmentStage(preferredInvestmentStage));
        if (businessArea != null) specs = specs.and(MainPostSpecifications.hasBusinessArea(businessArea));
        if (technology != null) specs = specs.and(MainPostSpecifications.hasTechnology(technology));
        if (investmentStage != null) specs = specs.and(MainPostSpecifications.hasInvestmentStage(investmentStage));
        if (region != null) specs = specs.and(MainPostSpecifications.inRegion(region));
        if (investmentActive != null) specs = specs.and(MainPostSpecifications.isInvestmentActive(investmentActive));
        if (query != null) specs = specs.and(MainPostSpecifications.matchesQuery(query));

        Page<MainPost> mainPostsPage = mainPostRepo.findAll(specs, pageable);

        return getRespMainPosts(mainPostsPage, pageable);
    }

    private static PageImpl<RespMainPost> getRespMainPosts(Page<MainPost> mainPostsPage,
        Pageable pageable) {
        List<RespMainPost> respMainPostsWithParams = new ArrayList<>();
        mainPostsPage.forEach(mainPost -> respMainPostsWithParams.add(
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

        return new PageImpl<>(respMainPostsWithParams, pageable, mainPostsPage.getTotalElements());
    }

    private class MainPostSpecifications {

        private static Specification<MainPost> hasInstitutionType(String institutionType) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("institutionType"), institutionType);
        }

        private static Specification<MainPost> hasPreferredBusinessArea(String businessArea) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("preferredBusinessArea"), businessArea);
        }

        private static Specification<MainPost> hasPreferredTechnology(String technology) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("preferredTechnology"), technology);
        }

        private static Specification<MainPost> hasPreferredInvestmentStage(String investmentStage) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("preferredInvestmentStage"), investmentStage);
        }

        private static Specification<MainPost> hasBusinessArea(String businessArea) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("businessArea"), businessArea);
        }

        private static Specification<MainPost> hasTechnology(String technology) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("technology"), technology);
        }

        private static Specification<MainPost> hasInvestmentStage(String investmentStage) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("investmentStage"), investmentStage);
        }

        private static Specification<MainPost> inRegion(String region) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("region"), region);
        }

        private static Specification<MainPost> isInvestmentActive(Boolean investmentActive) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("investmentActive"), investmentActive);
        }

        private static Specification<MainPost> matchesQuery(String searchQuery) {
            return (root, criteriaQuery, criteriaBuilder) -> {
                // 컬럼 'name'을 가져와 소문자로 변환 후, searchQuery를 소문자로 변환하여 포함하는지 비교
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%");
            };
        }

    }
}
