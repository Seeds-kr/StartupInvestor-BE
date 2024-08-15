package seeds.StartupInvestor.global.dummy;

import java.util.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import seeds.StartupInvestor.domain.*;
import seeds.StartupInvestor.domain.constant.BusinessTypeConst;
import seeds.StartupInvestor.domain.constant.SeriesCategoryConst;
import seeds.StartupInvestor.domain.constant.TechTypeConst;
import seeds.StartupInvestor.domain.constant.UserTypeConst;
import seeds.StartupInvestor.repository.*;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
@Profile({"dev", "test"})
public class dummy {

    private final MainPostRepo mainPostRepo;
    private final UserRepo userRepo;
    private final UserTypeRepo userTypeRepo;
    private final SeriesCategoryRepo seriesCategoryRepo;
    private final CompanyRepo companyRepo;
    private final TechTypeRepo techTypeRepo;
    private final BusinessTypeRepo businessTypeRepo;
    private final CompanyBusinessTypeRepo companyBusinessTypeRepo;
    private final CompanyTechTypeRepo companyTechTypeRepo;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initDataForTest() {
        settingUserType();
        UserType userTypeAdmin = userTypeRepo.findById(1L).orElseThrow(NullPointerException::new);
        UserType userTypeCompany = userTypeRepo.findById(3L).orElseThrow(NullPointerException::new);


        User user1 = settingAdminUser(userTypeAdmin);
        settingCompanyUserTen(userTypeCompany);

        settingSeriesCategory();

        settingTechTypeRepo();

        settingBusinessTypeRepo();

        settingCompany();

        settingMainPost();
    }

    private void settingCompanyUserTen(UserType userTypeCompany) {
        for(int i=1;i<=30;i++){
            User user = new User(
                    userTypeCompany,
                    "Personal Type Example",
                    "userCompany%d".formatted(i),
                    "1q2w3e4r!!",
                    "userCompany%d@example.com".formatted(i),
                    1234567890L,
                    false,
                    null // profile image data
            );
            userRepo.save(user);
        }
    }

    private void settingBusinessTypeRepo() {
        for(BusinessTypeConst businessTypeConst : BusinessTypeConst.values()) {
            BusinessType businessType = new BusinessType(businessTypeConst);
            businessTypeRepo.save(businessType);
        }
    }

    private void settingTechTypeRepo() {
        for (TechTypeConst techTypeConst : TechTypeConst.values()) {
            TechType techType = new TechType(techTypeConst);
            techTypeRepo.save(techType);
        }
    }

    private void settingCompany() {
        Calendar calendar = new GregorianCalendar(1999, Calendar.OCTOBER, 11);
        Date foundedAt = calendar.getTime();

        Long amount = 100000000L;  // 1억
        String companyUrl = "https://www.example.com";
        String introduction = "This is a sample introduction of the company.";
        Long goalAmountMinimum = 50000000L;  // 5천만
        Long goalAmountMaximum = 200000000L; // 2억
        String productDescription = "This is a sample product description.";
        String productImageUrl = "https://www.example.com/product.jpg";
        String productIntroduceUrl = "https://www.example.com/product/intro";
        String newHeadline = "Company achieves new milestone";
        String newUrl = "https://www.example.com/news";
        calendar.set(2024, Calendar.AUGUST, 10); // 2024년 8월 10일
        Date newUploadAt = calendar.getTime();
        String newsCompany = "Sample News Company";

        for (int i = 1; i <= 25; i++) {
            String uniqueName = "Sample Company " + i;
            String uniqueAddress = "1234 Sample Address " + i;
            String productName = "Sample Product " + i;

            long lastest_series_category = (i + 1) / 5 + 1;
            long goal_series_cateogry = (i + 1) / 5 + 2;

            SeriesCategory seriesCategory1 = seriesCategoryRepo.findById(lastest_series_category).orElseThrow(NullPointerException::new);
            SeriesCategory seriesCategory2 = seriesCategoryRepo.findById(goal_series_cateogry).orElseThrow(NullPointerException::new);


            // Company 객체 생성
            Company company = new Company(
                    userRepo.findById((long)(i+1)).get(),
                    uniqueName,
                    uniqueAddress,
                    foundedAt,
                    amount,
                    seriesCategory1,
                    companyUrl,
                    introduction,
                    seriesCategory2,
                    goalAmountMinimum,
                    goalAmountMaximum,
                    productName,
                    productDescription,
                    productImageUrl,
                    productIntroduceUrl,
                    newHeadline,
                    newUrl,
                    newUploadAt,
                    newsCompany
            );

            companyRepo.save(company);

            // ID 값을 다양하게 설정하기 위한 계산
            long techTypeId = (i + 1) / 5 + 1;
            long businessTypeId = (i + 1) / 5 + 1;

            // CompanyTechType 설정
            CompanyTechType companyTechType = new CompanyTechType(
                    company,
                    techTypeRepo.findById(techTypeId).get()
            );

            companyTechTypeRepo.save(companyTechType);

            // CompanyBusinessType 설정
            CompanyBusinessType companyBusinessType = new CompanyBusinessType(
                    company,
                    businessTypeRepo.findById(businessTypeId).get()
            );

            companyBusinessTypeRepo.save(companyBusinessType);
        }
    }

    private void settingMainPost() {
        for (int i = 1; i <= 25; i++) {
            MainPost mainPost = new MainPost(
                    userRepo.findById((long) (i+1)).get(),
                "Title %d".formatted(i),
                "Description of post %d".formatted(i),
                null // Assuming no image data for the dummy data
            );
            mainPostRepo.save(mainPost);
        }
    }

    private void settingSeriesCategory() {
        for (SeriesCategoryConst categoryConst : SeriesCategoryConst.values()) {
            SeriesCategory seriesCategory = new SeriesCategory(categoryConst);
            seriesCategoryRepo.save(seriesCategory);
        }
    }

    private User settingAdminUser(UserType userTypeInput) {
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

        return userRepo.findById(1L).orElseThrow(NullPointerException::new);
    }

    private UserType settingUserType() {
        UserType userType1 = new UserType(UserTypeConst.ADMIN.toString());
        UserType userType2 = new UserType(UserTypeConst.NOTHING.toString());
        UserType userType3 = new UserType(UserTypeConst.COMPANY.toString());
        UserType userType4 = new UserType(UserTypeConst.INVESTOR.toString());

        userTypeRepo.save(userType1);
        userTypeRepo.save(userType2);
        userTypeRepo.save(userType3);
        userTypeRepo.save(userType4);

        return userTypeRepo.findById(1L).orElseThrow(NullPointerException::new);
    }
}
