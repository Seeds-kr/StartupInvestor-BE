package seeds.StartupInvestor.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seeds.StartupInvestor.domain.constant.TechTypeConst;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tech_type")
public class TechType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tech_type_id")
    private Long id;

    @Column(name = "main_category", nullable = false, length = 50)
    private String mainCategory;

    @Column(name = "sub_category", nullable = false, length = 100)
    private String subCategory;

    public TechType(TechTypeConst techTypeConst) {
        this.mainCategory = techTypeConst.getMainCategory();
        this.subCategory = techTypeConst.getSubCategory();
    }
}
