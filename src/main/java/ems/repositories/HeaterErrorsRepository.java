package ems.repositories;

import ems.models.HeaterErrors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HeaterErrorsRepository extends JpaRepository<HeaterErrors, Long> {
    // Custom query methods
    List<HeaterErrors> findBySerialnumber(String serialNumber);
    List<HeaterErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<HeaterErrors> findByHwsafetytemp(Integer hwSafetyTemp);
    List<HeaterErrors> findBySensortc(Integer sensorTc);
    List<HeaterErrors> findByBurn(Integer burn);
    List<HeaterErrors> findByTcdiff(Integer tcDiff);
    List<HeaterErrors> findByDeltatarget(Integer deltaTarget);
    List<HeaterErrors> findByHot(Integer hot);
    List<HeaterErrors> findByCold(Integer cold);
    List<HeaterErrors> findByFull(Integer full);
    List<HeaterErrors> findAll();
}