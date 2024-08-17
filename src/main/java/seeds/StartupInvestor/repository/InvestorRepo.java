package seeds.StartupInvestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seeds.StartupInvestor.domain.Investor;

@Repository
public interface InvestorRepo extends JpaRepository<Investor,Long> {

}
