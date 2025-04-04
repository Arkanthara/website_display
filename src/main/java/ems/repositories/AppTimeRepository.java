package ems.repositories;

import ems.models.AppTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppTimeRepository extends JpaRepository<AppTime, Long> {
    // Custom query methods
    List<AppTime> findBySerialnumber(String serialNumber);
    List<AppTime> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<AppTime> findByPressurized(Integer pressurized);
    List<AppTime> findByRunpzp1(Integer runPzP1);
    List<AppTime> findByBoostpzp1(Integer boostPzP1);
    List<AppTime> findByRunpzp2(Integer runPzP2);
    List<AppTime> findByBoostpzp2(Integer boostPzP2);
    List<AppTime> findByRunpzp3(Integer runPzP3);
    List<AppTime> findByBoostpzp3(Integer boostPzP3);
    List<AppTime> findByRunafwateronlyp1(Integer runAfWaterOnlyP1);
    List<AppTime> findByRunafwateronlyp2(Integer runAfWaterOnlyP2);
    List<AppTime> findByRunafwateronlyp3(Integer runAfWaterOnlyP3);
    List<AppTime> findByRunafp1(Integer runAfP1);
    List<AppTime> findByRunafp2(Integer runAfP2);
    List<AppTime> findByRunafp3(Integer runAfP3);
    List<AppTime> findByBoostafp1(Integer boostAfP1);
    List<AppTime> findByBoostafp2(Integer boostAfP2);
    List<AppTime> findByBoostafp3(Integer boostAfP3);
    List<AppTime> findByCleanpause(Integer cleanPause);
    List<AppTime> findAll();
}