package mts.homework.sivelkaev.application.model.repository;

import mts.homework.sivelkaev.application.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
