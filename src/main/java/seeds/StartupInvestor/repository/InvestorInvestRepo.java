package seeds.StartupInvestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seeds.StartupInvestor.domain.InvestorInvest;

@Repository
public interface InvestorInvestRepo extends JpaRepository<InvestorInvest, Long> {

}
