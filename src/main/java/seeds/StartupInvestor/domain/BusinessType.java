package seeds.StartupInvestor.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seeds.StartupInvestor.domain.constant.BusinessTypeConst;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "business_type")
public class BusinessType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_type_id")
    private Long businessTypeId;

    @Column(name = "main_category", nullable = false, length = 50)
    private String mainCategory;

    @Column(name = "sub_category", nullable = false, length = 100)
    private String subCategory;

    public BusinessType(BusinessTypeConst businessTypeConst) {
        this.mainCategory = businessTypeConst.getMainCategory();
        this.subCategory = businessTypeConst.getSubCategory();
    }
}