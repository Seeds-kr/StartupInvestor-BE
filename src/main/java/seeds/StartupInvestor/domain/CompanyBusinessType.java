package seeds.StartupInvestor.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "company_business_type")
public class CompanyBusinessType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_business_type_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_type_id", nullable = false)
    private BusinessType businessType;

    public CompanyBusinessType(Company company, BusinessType businessType) {
        this.company = company;
        this.businessType = businessType;
    }
}