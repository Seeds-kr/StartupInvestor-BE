package seeds.StartupInvestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import seeds.StartupInvestor.domain.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>,
        JpaSpecificationExecutor<User> {

}
