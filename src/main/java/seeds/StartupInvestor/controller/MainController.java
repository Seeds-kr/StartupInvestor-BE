package seeds.StartupInvestor.controller;

import java.util.Objects;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seeds.StartupInvestor.domain.MainPost;
import seeds.StartupInvestor.dto.response.RespMainPost;
import seeds.StartupInvestor.repository.MainPostRepo;
import seeds.StartupInvestor.service.MainService;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;
    private final MainPostRepo mainPostRepo;

    @GetMapping()
    public ResponseEntity<Page<RespMainPost>> getAllPosts(
        @RequestParam(defaultValue = "0") int page) {
        Page<RespMainPost> posts = mainService.allPost(page);
        return ResponseEntity.ok(posts);
    }

    @GetMapping()
    public ResponseEntity<?> getAllPostsWithParam(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(required = false) String institutionType,
        @RequestParam(required = false) String preferredBusinessArea,
        @RequestParam(required = false) String preferredTechnology,
        @RequestParam(required = false) String preferredInvestmentStage,
        @RequestParam(required = false) String businessArea,
        @RequestParam(required = false) String technology,
        @RequestParam(required = false) String investmentStage,
        @RequestParam(required = false) String region,
        @RequestParam(required = false) Boolean investmentActive,
        @RequestParam(required = false) String query) {

        // 초기 디폴트
        Page<RespMainPost> posts = mainService.allPost(page);

        if (page == 0 && Stream.of(institutionType, preferredBusinessArea, preferredTechnology,
            preferredInvestmentStage,
            businessArea, technology, investmentStage, region, investmentActive, query).allMatch(
            Objects::isNull)) {
            return ResponseEntity.status(HttpStatus.OK).body(posts);
        }

        // 파라미터 값들이 있을 때
        return ResponseEntity.status(HttpStatus.OK).body(
            mainService.findMainPostsByCriteria(
                institutionType,
                preferredBusinessArea,
                preferredTechnology,
                preferredInvestmentStage,
                businessArea,
                technology,
                investmentStage,
                region,
                investmentActive,
                query,
                page
            )
        );
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getDetailPost(@PathVariable("postId") Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //user가 있다는 가정

}
