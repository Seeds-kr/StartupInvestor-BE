package seeds.StartupInvestor.service;

import static seeds.StartupInvestor.global.exception.ErrorCode.CONTENT_IS_EMPTY;
import static seeds.StartupInvestor.global.exception.ErrorCode.POST_NOT_FOUND;
import static seeds.StartupInvestor.global.exception.ErrorCode.USER_NOT_FOUND;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seeds.StartupInvestor.domain.MainPost;
import seeds.StartupInvestor.domain.MainPostBookmark;
import seeds.StartupInvestor.domain.MainPostLike;
import seeds.StartupInvestor.domain.MainPostReply;
import seeds.StartupInvestor.domain.User;
import seeds.StartupInvestor.dto.request.ReqMainPostReplyDto;
import seeds.StartupInvestor.dto.response.RespMainPost;
import seeds.StartupInvestor.global.exception.BusinessException;
import seeds.StartupInvestor.global.exception.ErrorCode;
import seeds.StartupInvestor.repository.MainPostBookmarkRepo;
import seeds.StartupInvestor.repository.MainPostLikeRepo;
import seeds.StartupInvestor.repository.MainPostReplyRepo;
import seeds.StartupInvestor.repository.MainPostRepo;
import seeds.StartupInvestor.repository.UserRepo;

@Service
@Transactional
@RequiredArgsConstructor
public class MainPostService {

    private static final int PAGE_SIZE = 20;

    private final MainPostRepo mainPostRepo;
    private final MainPostBookmarkRepo mainPostBookmarkRepo;
    private final MainPostLikeRepo mainPostLikeRepo;
    private final MainPostReplyRepo mainPostReplyRepo;
    private final UserRepo userRepo;

    @PersistenceContext
    private EntityManager entityManager;

    // main post crud
    public Page<RespMainPost> allPost(int pageNumber) {
        if (pageNumber < 0) {
            throw new BusinessException(ErrorCode.INVALID_PAGE_NUMBER);
        }

        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);

        Page<MainPost> pagedMainPosts = mainPostRepo.findAll(pageable);

        return getRespMainPosts(pagedMainPosts, pageable);
    }

    public void doMainPostBookmark(Long userId, Long postId) {
        getUserAndMainPost result = getGetUserAndMainPost(userId, postId);

        Optional<MainPostBookmark> existingBookmark = mainPostBookmarkRepo.findByUserAndMainPost(
            result.user(), result.post());

        if (existingBookmark.isPresent()) {
            mainPostBookmarkRepo.delete(existingBookmark.get());
        } else {
            MainPostBookmark bookmark = new MainPostBookmark(
                result.user(),
                result.post(),
                new Date()
            );
            mainPostBookmarkRepo.save(bookmark);
        }
    }

    public void doMainPostLike(Long userId, Long postId) {
        getUserAndMainPost result = getGetUserAndMainPost(userId, postId);

        Optional<MainPostLike> existingLike = mainPostLikeRepo.findByUserAndMainPost(result.user, result.post);

        if (existingLike.isPresent()) {
            mainPostLikeRepo.delete(existingLike.get());
        } else {
            MainPostLike like = new MainPostLike(
                result.user,
                result.post,
                new Date()
            );
            mainPostLikeRepo.save(like);
        }
    }

    public void addCompanyMainPostReply(Long userId, Long postId, ReqMainPostReplyDto mainPostReplyDto) {

        getUserAndMainPost result = getGetUserAndMainPost(userId, postId);

        if(mainPostReplyDto.getContent().isEmpty()){
            throw new BusinessException(CONTENT_IS_EMPTY);
        }

        MainPostReply reply = new MainPostReply(
            result.user,
            result.post,
            mainPostReplyDto.getContent()
        );

        mainPostReplyRepo.save(reply);
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
        if (page < 0) {
            throw new BusinessException(ErrorCode.INVALID_PAGE_NUMBER);
        }

        // 추가적인 유효성 검사
        if (mainBusinessCategory == null && subBusinessCategory != null) {
            throw new BusinessException(ErrorCode.INVALID_PARAMETER);
        }

        if (mainTechCategory == null && subTechCategory != null) {
            throw new BusinessException(ErrorCode.INVALID_PARAMETER);
        }

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
            sql.append(
                "JOIN series_category sc ON c.latest_series_category_id = sc.series_category_id ");
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

        Query jpaQuery = null;
        try {
            jpaQuery = entityManager.createNativeQuery(sql.toString(), MainPost.class);

        } catch (NullPointerException e) {
            throw new BusinessException(ErrorCode.POST_NOT_FOUND_WITH_PARAMETER);
        }

        if (mainBusinessCategory != null) {
            jpaQuery.setParameter("mainBusinessCategory", mainBusinessCategory);
        }
        if (subBusinessCategory != null) {
            jpaQuery.setParameter("subBusinessCategory", subBusinessCategory);
        }
        if (mainTechCategory != null) {
            jpaQuery.setParameter("mainTechCategory", mainTechCategory);
        }
        if (subTechCategory != null) {
            jpaQuery.setParameter("subTechCategory", subTechCategory);
        }
        if (investmentStage != null) {
            jpaQuery.setParameter("investmentStage", investmentStage);
        }
        if (investmentActive != null) {
            jpaQuery.setParameter("investmentActive", investmentActive);
        }
        if (query != null && !query.isEmpty()) {
            jpaQuery.setParameter("query", "%" + query.toLowerCase() + "%");
        }

        int totalRows = jpaQuery.getResultList().size();

        jpaQuery.setFirstResult(page * PAGE_SIZE);
        jpaQuery.setMaxResults(PAGE_SIZE);

        List<MainPost> mainPostsPage = jpaQuery.getResultList();
        if (mainPostsPage.isEmpty()) {
            throw new BusinessException(ErrorCode.POST_NOT_FOUND_WITH_PARAMETER);
        }

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

    private getUserAndMainPost getGetUserAndMainPost(Long userId, Long postId) {
        User user = userRepo.findById(userId)
            .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
        MainPost post = mainPostRepo.findById(postId)
            .orElseThrow(() -> new BusinessException(POST_NOT_FOUND));
        return new getUserAndMainPost(user, post);
    }

    private record getUserAndMainPost(User user, MainPost post) {

    }
}
