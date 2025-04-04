package ems.repositories;

import ems.models.OtherErrors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OtherErrorsRepository extends JpaRepository<OtherErrors, Long> {
    // Custom query methods
    List<OtherErrors> findBySerialnumber(String serialNumber);
    List<OtherErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<OtherErrors> findByToptouchreset(Integer topTouchReset);
    List<OtherErrors> findBySoundplayerr(Integer soundPlayErr);
    List<OtherErrors> findAll();
}