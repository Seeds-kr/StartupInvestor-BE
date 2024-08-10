package seeds.StartupInvestor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seeds.StartupInvestor.domain.Company;
import seeds.StartupInvestor.domain.MainPost;
import seeds.StartupInvestor.domain.User;
import seeds.StartupInvestor.repository.CompanyRepo;
import seeds.StartupInvestor.repository.MainPostRepo;
import seeds.StartupInvestor.repository.UserRepo;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CompanyRepo companyRepo;
    private final UserRepo userRepo;
    private final MainPostRepo mainPostRepo;

    @GetMapping("/post")
    public ResponseEntity<List<MainPost>> getAllPosts() {
        List<MainPost> all = mainPostRepo.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/company")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> all = companyRepo.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllusers() {
        List<User> all = userRepo.findAll();
        return ResponseEntity.ok(all);
    }

}
