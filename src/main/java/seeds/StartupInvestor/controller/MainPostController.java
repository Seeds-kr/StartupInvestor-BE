package seeds.StartupInvestor.controller;

import java.util.Objects;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seeds.StartupInvestor.dto.response.RespMainPost;
import seeds.StartupInvestor.service.MainPostService;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class MainPostController {

    private final MainPostService mainPostService;

//    @GetMapping()
//    public ResponseEntity<Page<RespMainPost>> getAllPosts(
//        @RequestParam(defaultValue = "0") int page) {
//        Page<RespMainPost> posts = mainService.allPost(page);
//        return ResponseEntity.ok(posts);
//    }

    @GetMapping()
    public ResponseEntity<?> getAllPostsWithParam(
        @RequestParam(name = "page",defaultValue = "0") int page,
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
