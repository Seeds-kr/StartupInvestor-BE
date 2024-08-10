package seeds.StartupInvestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import seeds.StartupInvestor.domain.BusinessType;

@Repository
public interface BusinessTypeRepo extends JpaRepository<BusinessType, Long>,
        JpaSpecificationExecutor<BusinessType> {
}
