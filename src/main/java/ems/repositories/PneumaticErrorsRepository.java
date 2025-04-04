package ems.repositories;

import ems.models.PneumaticErrors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PneumaticErrorsRepository extends JpaRepository<PneumaticErrors, Long> {
    // Custom query methods
    List<PneumaticErrors> findBySerialnumber(String serialNumber);
    List<PneumaticErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<PneumaticErrors> findBySensorpress(Integer sensorPress);
    List<PneumaticErrors> findByOverpress(Integer overPress);
    List<PneumaticErrors> findByAfsat(Integer afsat);
    List<PneumaticErrors> findByClogged(Integer clogged);
    List<PneumaticErrors> findByNochamber(Integer noChamber);
    List<PneumaticErrors> findByNoair(Integer noAir);
    List<PneumaticErrors> findByTimeoutup(Integer timeoutUp);
    List<PneumaticErrors> findAll();
}