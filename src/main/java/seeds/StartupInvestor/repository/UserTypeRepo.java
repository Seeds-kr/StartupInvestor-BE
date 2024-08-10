package seeds.StartupInvestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import seeds.StartupInvestor.domain.UserType;

@Repository
public interface UserTypeRepo extends JpaRepository<UserType, Long> ,
        JpaSpecificationExecutor<UserType> {

}
