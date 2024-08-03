package seeds.StartupInvestor.global.dummy;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import seeds.StartupInvestor.domain.MainPost;
import seeds.StartupInvestor.domain.User;
import seeds.StartupInvestor.domain.UserType;
import seeds.StartupInvestor.domain.constant.UserTypeConst;
import seeds.StartupInvestor.repository.MainPostRepo;
import seeds.StartupInvestor.repository.UserRepo;
import seeds.StartupInvestor.repository.UserTypeRepo;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("dev")
public class dummy {

    private final MainPostRepo mainPostRepo;
    private final UserRepo userRepo;
    private final UserTypeRepo userTypeRepo;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initDataForTest() {
        UserType userType1 = new UserType(UserTypeConst.ADMIN.toString());
        UserType userType2 = new UserType(UserTypeConst.NOTHING.toString());
        UserType userType3 = new UserType(UserTypeConst.COMPANY.toString());
        UserType userType4 = new UserType(UserTypeConst.INVESTOR.toString());

        userTypeRepo.save(userType1);
        userTypeRepo.save(userType2);
        userTypeRepo.save(userType3);
        userTypeRepo.save(userType4);

        UserType userTypeInput = userTypeRepo.findById(1L).get();

        User user = new User(
            userTypeInput,
            "Personal Type Example",
            "user for test",
            "1q2w3e4r!!",
            "user@example.com",
            1234567890L,
            false,
            null // profile image data
        );

        userRepo.save(user);

        User user1 = userRepo.findById(1L).get();

        for (int i = 1; i <= 25; i++) {
            MainPost mainPost = new MainPost(
                user1,
                "Title %d".formatted(i),
                "Description of post %d".formatted(i),
                null // Assuming no image data for the dummy data
            );
            mainPostRepo.save(mainPost);
        }

    }
}
