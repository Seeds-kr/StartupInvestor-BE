package seeds.StartupInvestor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "investor")
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investor_id")
    private Long investorId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ceo_user_id", nullable = false)
    private User user;

    @Column(name = "url", length = 255)
    private String url;

    @Column(name = "founded_date")
    @Temporal(TemporalType.DATE)
    private Date foundedDate;

    @Column(name = "introduction", columnDefinition = "TEXT")
    private String introduction;

    @Column(name = "modified_at")
    @Temporal(TemporalType.DATE)
    private Date modifiedAt;

    @Column(name = "invest_type", length = 255)
    private String investType;

    @Column(name = "total_amount")
    private Long totalAmount;

    @Column(name = "total_invest_cnt")
    private Integer totalInvestCnt;
}