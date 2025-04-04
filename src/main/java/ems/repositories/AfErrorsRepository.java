package ems.repositories;

import ems.models.AfErrors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AfErrorsRepository extends JpaRepository<AfErrors, Long> {
    // Custom query methods
    List<AfErrors> findBySerialnumber(String serialNumber);
    List<AfErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<AfErrors> findByTimeout(Integer timeout);
    List<AfErrors> findByNak(Integer nak);
    List<AfErrors> findByFrameinv(Integer frameinv);
    List<AfErrors> findByDatainv(Integer dataInv);
    List<AfErrors> findAll();
}