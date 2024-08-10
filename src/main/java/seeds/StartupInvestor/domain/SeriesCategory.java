package seeds.StartupInvestor.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seeds.StartupInvestor.domain.constant.SeriesCategoryConst;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "series_category")
public class SeriesCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "series_category_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private SeriesCategoryConst category;

    public SeriesCategory(SeriesCategoryConst category) {
        this.category = category;
    }
}