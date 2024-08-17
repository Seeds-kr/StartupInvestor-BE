package seeds.StartupInvestor.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seeds.StartupInvestor.domain.MainPost;
import seeds.StartupInvestor.domain.MainPostLike;
import seeds.StartupInvestor.domain.User;

@Repository
public interface MainPostLikeRepo extends JpaRepository<MainPostLike, Long> {

    Optional<MainPostLike> findByUserAndMainPost(User user, MainPost post);

}
