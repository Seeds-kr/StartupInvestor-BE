package seeds.StartupInvestor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ceo_user_id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false, unique = true, length = 255)
    private String name;

    @Column(name = "address", nullable = false, unique = true, length = 255)
    private String address;

    @Column(name = "founded_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date foundedAt;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "latest_series_category_id", nullable = false)
    private SeriesCategory latestSeriesCategory;

    @Column(name = "is_possible_invest", nullable = false)
    private Boolean isPossibleInvest;

    @Column(name = "company_url", nullable = false, length = 255)
    private String companyUrl;

    @Column(name = "modified_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date modifiedAt;

    @Column(name = "introduction", nullable = false, columnDefinition = "TEXT")
    private String introduction;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_series_category_id", nullable = true)
    private SeriesCategory goalSeriesCategory;

    @Column(name = "goal_amount_minimum", nullable = true)
    private Long goalAmountMinimum;

    @Column(name = "goal_amount_maximum", nullable = true)
    private Long goalAmountMaximum;

    @Column(name = "product_name", nullable = true, length = 255)
    private String productName;

    @Column(name = "product_description", nullable = true, columnDefinition = "TEXT")
    private String productDescription;

    @Column(name = "product_image_url", nullable = true, length = 255)
    private String productImageUrl;

    @Column(name = "product_introduce_url", nullable = true, length = 255)
    private String productIntroduceUrl;

    @Column(name = "new_headline", nullable = true, length = 255)
    private String newHeadline;

    @Column(name = "new_url", nullable = true, length = 255)
    private String newUrl;

    @Column(name = "new_upload_at", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date newUploadAt;

    @Column(name = "news_company", nullable = true, length = 255)
    private String newsCompany;

    // Constructor with all fields including optional ones
    public Company(User user, String name, String address, Date foundedAt, Long amount,
                   SeriesCategory latestSeriesCategory, String companyUrl, String introduction,
                   SeriesCategory goalSeriesCategory, Long goalAmountMinimum, Long goalAmountMaximum,
                   String productName, String productDescription, String productImageUrl, String productIntroduceUrl,
                   String newHeadline, String newUrl, Date newUploadAt, String newsCompany) {
        this.user = user;
        this.name = name;
        this.address = address;
        this.foundedAt = foundedAt;
        this.amount = amount;
        this.latestSeriesCategory = latestSeriesCategory;
        this.isPossibleInvest = true;
        this.companyUrl = companyUrl;
        this.introduction = introduction;
        this.modifiedAt = new Date();  // Set the current date as default for modifiedAt
        this.goalSeriesCategory = goalSeriesCategory;
        this.goalAmountMinimum = goalAmountMinimum;
        this.goalAmountMaximum = goalAmountMaximum;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImageUrl = productImageUrl;
        this.productIntroduceUrl = productIntroduceUrl;
        this.newHeadline = newHeadline;
        this.newUrl = newUrl;
        this.newUploadAt = newUploadAt;
        this.newsCompany = newsCompany;
    }
}
