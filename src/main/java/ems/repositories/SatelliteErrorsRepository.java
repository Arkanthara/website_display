package ems.repositories;

import ems.models.SatelliteErrors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SatelliteErrorsRepository extends JpaRepository<SatelliteErrors, Long> {
    // Custom query methods
    List<SatelliteErrors> findBySerialnumber(String serialNumber);
    List<SatelliteErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<SatelliteErrors> findByRebootaf(Integer rebootAf);
    List<SatelliteErrors> findByRebootpz(Integer rebootPz);
    List<SatelliteErrors> findByLostaf(Integer lostAf);
    List<SatelliteErrors> findByLostpz(Integer lostPz);
    List<SatelliteErrors> findByNoaf(Integer noAf);
    List<SatelliteErrors> findByNopz(Integer noPz);
    List<SatelliteErrors> findByNosat(Integer noSat);
    List<SatelliteErrors> findAll();
}