package seeds.StartupInvestor.controller;

import java.util.Objects;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seeds.StartupInvestor.dto.request.ReqMainPostReplyDto;
import seeds.StartupInvestor.dto.response.RespMainPost;
import seeds.StartupInvestor.service.MainPostService;

@RestController
@RequestMapping("/v1/board")
@RequiredArgsConstructor
public class MainPostController {

    private final MainPostService mainPostService;

    //(작업 예정)
    @GetMapping("/investor")
    public ResponseEntity<?> getAllInvestorPostsWithParam() {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/startup/{postId}/bookmark")
    public ResponseEntity<?> companyMainPostBookmark(@PathVariable(name = "postId") long postId) {
        mainPostService.doMainPostBookmark(1L, postId);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/startup/{postId}/like")
    public ResponseEntity<?> companyMainPostLike(@PathVariable(name = "postId") long postId) {
        mainPostService.doMainPostLike(1L, postId);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/startup/{postId}/reply")
    public ResponseEntity<?> addCompanyMainPostReply(@PathVariable(name = "postId") long postId,
        ReqMainPostReplyDto mainPostReplyDto) {
        mainPostService.addCompanyMainPostReply(1L, postId, mainPostReplyDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/startup")
    public ResponseEntity<?> getAllCompanyPostsWithParam(
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "mbt", required = false) String mbt,  // main business type
        @RequestParam(name = "sbt", required = false) String sbt,  // sub business type
        @RequestParam(name = "mtt", required = false) String mtt,  // main tech type
        @RequestParam(name = "stt", required = false) String stt,  // sub tech type
        @RequestParam(name = "sc", required = false) String sc,    // series category
        @RequestParam(name = "ia", required = false) Boolean ia,   // investment active
        @RequestParam(name = "q", required = false) String q       // query (검색)
    ) {

        // 초기 디폴트
        Page<RespMainPost> posts = mainPostService.allPost(page);

        if (page == 0 && Stream.of(
            mbt,
            sbt,
            mtt,
            stt,
            sc,
            ia,
            q).allMatch(
            Objects::isNull)) {
            return ResponseEntity.status(HttpStatus.OK).body(posts);
        }

        // 파라미터 값들이 있을 때
        return ResponseEntity.status(HttpStatus.OK).body(
            mainPostService.findMainPostsByCriteria(
                mbt,
                sbt,
                mtt,
                stt,
                sc,
                ia,
                q,
                page
            )
        );
    }


}
