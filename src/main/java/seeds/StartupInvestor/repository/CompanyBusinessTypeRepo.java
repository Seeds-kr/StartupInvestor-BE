package seeds.StartupInvestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import seeds.StartupInvestor.domain.CompanyBusinessType;

@Repository
public interface CompanyBusinessTypeRepo extends JpaRepository<CompanyBusinessType, Long> ,
        JpaSpecificationExecutor<CompanyBusinessType> {
}
