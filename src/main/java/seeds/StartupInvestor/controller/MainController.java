package seeds.StartupInvestor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seeds.StartupInvestor.dto.response.RespMainPost;
import seeds.StartupInvestor.service.MainService;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping()
    public ResponseEntity<Page<RespMainPost>> getAllPosts(
        @RequestParam(defaultValue = "0") int page) {
        Page<RespMainPost> posts = mainService.allPost(page);
        return ResponseEntity.ok(posts);
    }

//    @GetMapping("/")
//    public ResponseEntity<?> getAllPostsWithParam(
//        @RequestParam(required = false) String query) {
//
//        return ResponseEntity.status(HttpStatus.OK).body(null);
//    }
//
//    @GetMapping("/{postId}")
//    public ResponseEntity<?> getDetailPost(@PathVariable("postId") Long postId) {
//        return ResponseEntity.status(HttpStatus.OK).body(null);
//    }

    //user가 있다는 가정

}
