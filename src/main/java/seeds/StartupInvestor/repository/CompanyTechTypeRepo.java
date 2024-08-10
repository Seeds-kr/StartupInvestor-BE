package seeds.StartupInvestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import seeds.StartupInvestor.domain.CompanyTechType;

@Repository
public interface CompanyTechTypeRepo extends JpaRepository<CompanyTechType, Long> ,
        JpaSpecificationExecutor<CompanyTechType> {
}
