package ems.repositories;

import ems.models.AppEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppEventRepository extends JpaRepository<AppEvent, Long> {
    // Custom query methods
    List<AppEvent> findBySerialnumber(String serialNumber);
    List<AppEvent> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<AppEvent> findByPressurization(Integer pressurization);
    List<AppEvent> findByPairingstart(Integer pairingStart);
    List<AppEvent> findByPairingcomplete(Integer pairingComplete);
    List<AppEvent> findByCleaningstart(Integer cleaningStart);
    List<AppEvent> findByCleaningpause(Integer cleaningPause);
    List<AppEvent> findByCleaningfinalized(Integer cleaningFinalized);
    List<AppEvent> findByPedalpzp1(Integer pedalPzP1);
    List<AppEvent> findByBoostpzp1(Integer boostPzP1);
    List<AppEvent> findByPedalpzp2(Integer pedalPzP2);
    List<AppEvent> findByBoostpzp2(Integer boostPzP2);
    List<AppEvent> findByPedalpzp3(Integer pedalPzP3);
    List<AppEvent> findByBoostpzp3(Integer boostPzP3);
    List<AppEvent> findByPedalafp1(Integer pedalAfP1);
    List<AppEvent> findByBoostafp1(Integer boostAfP1);
    List<AppEvent> findByPedalafp2(Integer pedalAfP2);
    List<AppEvent> findByBoostafp2(Integer boostAfP2);
    List<AppEvent> findByPedalafp3(Integer pedalAfP3);
    List<AppEvent> findByBoostafp3(Integer boostAfP3);
    List<AppEvent> findByPedalafwop1(Integer pedalAfwoP1);
    List<AppEvent> findByPedalafwop2(Integer pedalAfwoP2);
    List<AppEvent> findByPedalafwop3(Integer pedalAfwoP3);
    List<AppEvent> findByOnstart(Integer onStart);
    List<AppEvent> findByOnafter7h(Integer onAfter7H);
    List<AppEvent> findAll();
}