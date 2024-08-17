package seeds.StartupInvestor.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seeds.StartupInvestor.domain.MainPost;
import seeds.StartupInvestor.domain.MainPostBookmark;
import seeds.StartupInvestor.domain.User;

@Repository
public interface MainPostBookmarkRepo extends JpaRepository<MainPostBookmark,Long> {

    Optional<MainPostBookmark> findByUserAndMainPost(User user, MainPost post);
}
