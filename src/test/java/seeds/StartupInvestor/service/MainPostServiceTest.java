package seeds.StartupInvestor.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import seeds.StartupInvestor.dto.response.RespMainPost;
import seeds.StartupInvestor.global.exception.BusinessException;
import seeds.StartupInvestor.global.exception.ErrorCode;


@SpringBootTest
@ActiveProfiles("test")
class MainPostServiceTest {

    @Autowired
    private MainPostService mainPostService;

    @Test
    void testFindMainPostsByCriteria_InvalidPageNumber() {
        assertThatThrownBy(() -> mainPostService.findMainPostsByCriteria(
            "Technology", "Software", "AI", "Machine Learning", "Seed", true, "Innovation", -1))
            .isInstanceOf(BusinessException.class)
            .hasMessageContaining(ErrorCode.INVALID_PAGE_NUMBER.getMessage());
    }

    @Test
    void testFindMainPostsByCriteria_InvalidParameter_BusinessCategory() {
        assertThatThrownBy(() -> mainPostService.findMainPostsByCriteria(
            null, "Software", "AI", "Machine Learning", "Seed", true, "Innovation", 0))
            .isInstanceOf(BusinessException.class)
            .hasMessageContaining(ErrorCode.INVALID_PARAMETER.getMessage());
    }

    @Test
    void testFindMainPostsByCriteria_InvalidParameter_TechCategory() {
        assertThatThrownBy(() -> mainPostService.findMainPostsByCriteria(
            "Technology", "Software", null, "Machine Learning", "Seed", true, "Innovation", 0))
            .isInstanceOf(BusinessException.class)
            .hasMessageContaining(ErrorCode.INVALID_PARAMETER.getMessage());
    }

    @Test
    void testFindMainPostsByCriteria_Success() {
        String mainBusinessCategory = "IT";
        String subBusinessCategory = "데이터";
        String mainTechCategory = "AI";
        String subTechCategory = "광학문자인식(OCR)";
        String investmentStage = "PRE_SEED";
        Boolean investmentActive = true;
        String query = null;
        int page = 0;

        // Test the method
        Page<RespMainPost> result = mainPostService.findMainPostsByCriteria(
            mainBusinessCategory, subBusinessCategory, mainTechCategory, subTechCategory,
            investmentStage, investmentActive, query, page
        );

        // Verify and Assert
        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSizeGreaterThan(0);
    }

    @Test
    void testFindMainPostsByCriteria_empty_list() {
        String mainBusinessCategory = "IT";
        String subBusinessCategory = "없는서브카테고리";
        String mainTechCategory = "AI";
        String subTechCategory = "광학문자인식(OCR)";
        String investmentStage = "PRE_SEED";
        Boolean investmentActive = true;
        String query = null;
        int page = 0;

        assertThatThrownBy(() -> mainPostService.findMainPostsByCriteria(
            mainBusinessCategory, subBusinessCategory, mainTechCategory, subTechCategory,
            investmentStage, investmentActive, query, page))
            .hasMessage(
            ErrorCode.POST_NOT_FOUND_WITH_PARAMETER.getMessage()
            );
    }
}