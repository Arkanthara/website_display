package ems.repositories;

import ems.models.PzErrors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PzErrorsRepository extends JpaRepository<PzErrors, Long> {
    // Custom query methods
    List<PzErrors> findBySerialnumber(String serialNumber);
    List<PzErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<PzErrors> findByTimeout(Integer timeout);
    List<PzErrors> findByNak(Integer nak);
    List<PzErrors> findByFrameinv(Integer frameinv);
    List<PzErrors> findByDatainv(Integer dataInv);
    List<PzErrors> findAll();
}