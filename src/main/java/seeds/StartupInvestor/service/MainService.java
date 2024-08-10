package seeds.StartupInvestor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seeds.StartupInvestor.domain.*;
import seeds.StartupInvestor.dto.response.RespMainPost;
import seeds.StartupInvestor.repository.MainPostRepo;

@Service
@Transactional
@RequiredArgsConstructor
public class MainService {
    private static final int PAGE_SIZE = 20;

    private final MainPostRepo mainPostRepo;

    @PersistenceContext
    private EntityManager entityManager;

    // main post crud
    public Page<RespMainPost> allPost(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);

        // Fetch paginated data from repository
        Page<MainPost> pagedMainPosts = mainPostRepo.findAll(pageable);

        // Transform MainPost entities into RespMainPost DTOs
        return getRespMainPosts(pagedMainPosts, pageable);
    }



    public Page<RespMainPost> findMainPostsByCriteria(
            String mainBusinessCategory,
            String subBusinessCategory,
            String mainTechCategory,
            String subTechCategory,
            String investmentStage,
            Boolean investmentActive,
            String query,
            int page) {

        StringBuilder sql = new StringBuilder("SELECT mp.* FROM main_post mp ");
        sql.append("JOIN user u ON mp.user_id = u.user_id ");
        sql.append("JOIN company c ON u.user_id = c.ceo_user_id ");

        if (mainBusinessCategory != null || subBusinessCategory != null) {
            sql.append("JOIN company_business_type cbt ON c.company_id = cbt.company_id ");
            sql.append("JOIN business_type bt ON cbt.business_type_id = bt.business_type_id ");
        }

        if (mainTechCategory != null || subTechCategory != null) {
            sql.append("JOIN company_tech_type ctt ON c.company_id = ctt.company_id ");
            sql.append("JOIN tech_type tt ON ctt.tech_type_id = tt.tech_type_id ");
        }

        if (investmentStage != null) {
            sql.append("JOIN series_category sc ON c.latest_series_category_id = sc.series_category_id ");
        }

        sql.append("WHERE 1=1 ");

        if (mainBusinessCategory != null && subBusinessCategory != null) {
            sql.append("AND bt.main_category = :mainBusinessCategory ");
            sql.append("AND bt.sub_category = :subBusinessCategory ");
        } else if (mainBusinessCategory != null) {
            sql.append("AND bt.main_category = :mainBusinessCategory ");
        }

        if (mainTechCategory != null && subTechCategory != null) {
            sql.append("AND tt.main_category = :mainTechCategory ");
            sql.append("AND tt.sub_category = :subTechCategory ");
        } else if (mainTechCategory != null) {
            sql.append("AND tt.main_category = :mainTechCategory ");
        }

        if (investmentStage != null) {
            sql.append("AND sc.category = :investmentStage ");
        }

        if (investmentActive != null) {
            sql.append("AND c.is_possible_invest = :investmentActive ");
        }

        if (query != null && !query.isEmpty()) {
            sql.append("AND (LOWER(mp.title) LIKE :query OR LOWER(mp.description) LIKE :query) ");
        }

        Query jpaQuery = entityManager.createNativeQuery(sql.toString(), MainPost.class);

        if (mainBusinessCategory != null) jpaQuery.setParameter("mainBusinessCategory", mainBusinessCategory);
        if (subBusinessCategory != null) jpaQuery.setParameter("subBusinessCategory", subBusinessCategory);
        if (mainTechCategory != null) jpaQuery.setParameter("mainTechCategory", mainTechCategory);
        if (subTechCategory != null) jpaQuery.setParameter("subTechCategory", subTechCategory);
        if (investmentStage != null) jpaQuery.setParameter("investmentStage", investmentStage);
        if (investmentActive != null) jpaQuery.setParameter("investmentActive", investmentActive);
        if (query != null && !query.isEmpty()) jpaQuery.setParameter("query", "%" + query.toLowerCase() + "%");

        int totalRows = jpaQuery.getResultList().size();

        jpaQuery.setFirstResult(page * PAGE_SIZE);
        jpaQuery.setMaxResults(PAGE_SIZE);

        List<MainPost> mainPostsPage = jpaQuery.getResultList();

        List<RespMainPost> respMainPostsWithParams = mainPostsPage.stream().map(
                mainPost -> new RespMainPost(
                        mainPost.getTitle(),
                        mainPost.getDescription(),
                        mainPost.getImgData(),
                        mainPost.getBookmarkCnt(),
                        mainPost.getLikeCnt(),
                        false, // 수정 예정
                        false // 수정 예정
                )
        ).collect(Collectors.toList());

        return new PageImpl<>(respMainPostsWithParams, PageRequest.of(page, PAGE_SIZE), totalRows);
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

    private static class MainPostSpecifications {

        // Business Area corresponds to BusinessType
        private static Specification<MainPost> hasBusinessArea(String mainCategory, String subCategory) {
            return (root, query, criteriaBuilder) -> {
                Join<MainPost, User> userJoin = root.join("user");
                Join<Company, User> companyJoin = userJoin.join("user");

                Join<Company, CompanyBusinessType> companyBusinessTypeJoin = companyJoin.join("businessTypes");
                Join<CompanyBusinessType, BusinessType> businessTypeJoin = companyBusinessTypeJoin.join("businessType");

                if (mainCategory != null && subCategory != null) {
                    return criteriaBuilder.and(
                            criteriaBuilder.equal(businessTypeJoin.get("mainCategory"), mainCategory),
                            criteriaBuilder.equal(businessTypeJoin.get("subCategory"), subCategory)
                    );
                } else if (mainCategory != null) {
                    return criteriaBuilder.equal(businessTypeJoin.get("mainCategory"), mainCategory);
                } else {
                    return criteriaBuilder.conjunction();
                }
            };
        }
        // Technology corresponds to TechType
        private static Specification<MainPost> hasTechnology(String mainCategory, String subCategory) {
            return (root, query, criteriaBuilder) -> {
                Join<MainPost, User> userJoin = root.join("user");
                Join<User, Company> companyJoin = userJoin.join("company");
                Join<Company, CompanyTechType> companyTechTypeJoin = companyJoin.join("techTypes");
                Join<CompanyTechType, TechType> techTypeJoin = companyTechTypeJoin.join("techType");

                if (mainCategory != null && subCategory != null) {
                    return criteriaBuilder.and(
                            criteriaBuilder.equal(techTypeJoin.get("mainCategory"), mainCategory),
                            criteriaBuilder.equal(techTypeJoin.get("subCategory"), subCategory)
                    );
                } else if (mainCategory != null) {
                    return criteriaBuilder.equal(techTypeJoin.get("mainCategory"), mainCategory);
                } else {
                    return criteriaBuilder.conjunction(); // No filtering if both parameters are null
                }
            };
        }

        // Investment Stage corresponds to SeriesCategory
        private static Specification<MainPost> hasInvestmentStage(String investmentStage) {
            return (root, query, criteriaBuilder) -> {
                Join<MainPost, User> userJoin = root.join("user");
                Join<User, Company> companyJoin = userJoin.join("company");
                Join<Company, SeriesCategory> seriesCategoryJoin = companyJoin.join("latestSeriesCategory");
                return criteriaBuilder.equal(seriesCategoryJoin.get("category"), investmentStage);
            };
        }

        // Investment Active corresponds to isPossibleInvest in Company
        private static Specification<MainPost> isInvestmentActive(Boolean investmentActive) {
            return (root, query, criteriaBuilder) -> {
                Join<MainPost, User> userJoin = root.join("user");
                Join<User, Company> companyJoin = userJoin.join("company");
                return criteriaBuilder.equal(companyJoin.get("isPossibleInvest"), investmentActive);
            };
        }

        // Matching the query string to the post title or description
        private static Specification<MainPost> matchesQuery(String searchQuery) {
            return (root, criteriaQuery, criteriaBuilder) -> {
                String likePattern = "%" + searchQuery.toLowerCase() + "%";
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), likePattern),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), likePattern)
                );
            };
        }
    }

}
