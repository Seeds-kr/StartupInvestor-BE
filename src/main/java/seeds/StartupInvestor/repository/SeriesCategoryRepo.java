package seeds.StartupInvestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import seeds.StartupInvestor.domain.SeriesCategory;

@Repository
public interface SeriesCategoryRepo extends JpaRepository<SeriesCategory, Long>,
        JpaSpecificationExecutor<SeriesCategory> {
}
