package seeds.StartupInvestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seeds.StartupInvestor.domain.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Long> {

}
