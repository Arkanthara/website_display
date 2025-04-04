package ems.repositories;

import ems.models.UsErrors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsErrorsRepository extends JpaRepository<UsErrors, Long> {
    // Custom query methods
    List<UsErrors> findBySerialnumber(String serialNumber);
    List<UsErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<UsErrors> findByTimeout(Integer timeout);
    List<UsErrors> findByNak(Integer nak);
    List<UsErrors> findByFrameinv(Integer frameinv);
    List<UsErrors> findByDatainv(Integer dataInv);
    List<UsErrors> findAll();
}