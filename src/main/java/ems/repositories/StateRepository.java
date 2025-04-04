package ems.repositories;

import ems.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    // Custom query methods
    List<State> findBySerialnumber(String serialNumber);
    List<State> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<State> findByInit(Integer init);
    List<State> findByStandby(Integer standby);
    List<State> findByFault(Integer fault);
    List<State> findByWarning(Integer warning);
    List<State> findBySwupdate(Integer swUpdate);
    List<State> findByWifipairing(Integer wifiPairing);
    List<State> findByBtpairing(Integer btPairing);
    List<State> findByRest(Integer rest);
    List<State> findByCleaning(Integer cleaning);
    List<State> findByAf(Integer af);
    List<State> findByPz(Integer pz);
    List<State> findByService(Integer service);
    List<State> findAll();
}