package seeds.StartupInvestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seeds.StartupInvestor.domain.InvestorMember;

@Repository
public interface InvestorMemberRepo extends JpaRepository<InvestorMember, Long> {

}
