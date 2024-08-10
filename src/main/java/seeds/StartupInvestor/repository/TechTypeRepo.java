package seeds.StartupInvestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import seeds.StartupInvestor.domain.TechType;

@Repository
public interface TechTypeRepo extends JpaRepository<TechType, Long> ,
        JpaSpecificationExecutor<TechType> {
}
