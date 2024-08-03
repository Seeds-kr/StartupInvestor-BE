package seeds.StartupInvestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seeds.StartupInvestor.domain.MainPostReply;

@Repository
public interface MainPostReplyRepo extends JpaRepository<MainPostReply, Long> {

}
