package ems.repositories;

import ems.models.MonitorErrors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MonitorErrorsRepository extends JpaRepository<MonitorErrors, Long> {
    // Custom query methods
    List<MonitorErrors> findBySerialnumber(String serialNumber);
    List<MonitorErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<MonitorErrors> findBySensorpwr(Integer sensorPwr);
    List<MonitorErrors> findByNopwr(Integer noPwr);
    List<MonitorErrors> findByPowerloss(Integer powerLoss);
    List<MonitorErrors> findByValveaccesserror(Integer valveAccessError);
    List<MonitorErrors> findByValveovercurrent(Integer valveOverCurrent);
    List<MonitorErrors> findByValveopenload(Integer valveOpenLoad);
    List<MonitorErrors> findByUsmodule(Integer usModule);
    List<MonitorErrors> findAll();
}