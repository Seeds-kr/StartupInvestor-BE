package seeds.StartupInvestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seeds.StartupInvestor.domain.MainPost;

@Repository
public interface MainPostRepo extends JpaRepository<MainPost, Long> {

}
