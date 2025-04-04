package ems.repositories;

import ems.models.Power;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PowerRepository extends JpaRepository<Power, Long> {
    // Custom query methods
    List<Power> findBySerialnumber(String serialNumber);
    List<Power> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Power> findByTimepowerons(Integer timePowerOnS);
    List<Power> findByTimeons(Integer timeOnS);
    List<Power> findByPoweroncounter(Integer powerOnCounter);
    List<Power> findAll();
}