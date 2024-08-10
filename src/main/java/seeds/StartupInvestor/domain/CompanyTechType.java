package seeds.StartupInvestor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seeds.StartupInvestor.domain.constant.TechTypeConst;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "company_tech_type")
public class CompanyTechType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_tech_type_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_type_id", nullable = false)
    private TechType techType;

    public CompanyTechType(Company company, TechType techType) {
        this.company = company;
        this.techType = techType;
    }
}