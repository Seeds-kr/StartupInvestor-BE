package seeds.StartupInvestor.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "ceo", nullable = false, length = 255)
    private String ceo;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "founded_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date foundedAt;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "latest_series_category", nullable = false, length = 255)
    private String latestSeriesCategory;

    @Column(name = "is_possible_invest", nullable = false)
    private Boolean isPossibleInvest;

    @Column(name = "company_url", nullable = false, length = 255)
    private String companyUrl;

    @Column(name = "modified_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date modifiedAt;

    @Column(name = "introduction", nullable = false, columnDefinition = "TEXT")
    private String introduction;

    // Add other fields based on the table structure shown

    // Standard getters and setters
}
