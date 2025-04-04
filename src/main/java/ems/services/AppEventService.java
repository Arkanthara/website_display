package ems.services;

import ems.models.AppEvent;
import ems.repositories.AppEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppEventService {
    @Autowired
    private AppEventRepository appeventRepository;

    public List<AppEvent> findAll() {
        return appeventRepository.findAll();
    }

    public AppEvent findById(Long id) {
        return appeventRepository.findById(id).orElse(null);
    }

    public AppEvent save(AppEvent appevent) {
        return appeventRepository.save(appevent);
    }

    public void deleteById(Long id) {
        appeventRepository.deleteById(id);
    }

    public List<AppEvent> findBySerialnumber(String serialNumber) {
        return appeventRepository.findBySerialnumber(serialNumber);
    }

    public List<AppEvent> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return appeventRepository.findByTimeBetween(start, end);
    }

    public List<AppEvent> findByPressurization(Integer pressurization) {
        return appeventRepository.findByPressurization(pressurization);
    }

    public List<AppEvent> findByPairingstart(Integer pairingStart) {
        return appeventRepository.findByPairingstart(pairingStart);
    }

    public List<AppEvent> findByPairingcomplete(Integer pairingComplete) {
        return appeventRepository.findByPairingcomplete(pairingComplete);
    }

    public List<AppEvent> findByCleaningstart(Integer cleaningStart) {
        return appeventRepository.findByCleaningstart(cleaningStart);
    }

    public List<AppEvent> findByCleaningpause(Integer cleaningPause) {
        return appeventRepository.findByCleaningpause(cleaningPause);
    }

    public List<AppEvent> findByCleaningfinalized(Integer cleaningFinalized) {
        return appeventRepository.findByCleaningfinalized(cleaningFinalized);
    }

    public List<AppEvent> findByPedalpzp1(Integer pedalPzP1) {
        return appeventRepository.findByPedalpzp1(pedalPzP1);
    }

    public List<AppEvent> findByBoostpzp1(Integer boostPzP1) {
        return appeventRepository.findByBoostpzp1(boostPzP1);
    }

    public List<AppEvent> findByPedalpzp2(Integer pedalPzP2) {
        return appeventRepository.findByPedalpzp2(pedalPzP2);
    }

    public List<AppEvent> findByBoostpzp2(Integer boostPzP2) {
        return appeventRepository.findByBoostpzp2(boostPzP2);
    }

    public List<AppEvent> findByPedalpzp3(Integer pedalPzP3) {
        return appeventRepository.findByPedalpzp3(pedalPzP3);
    }

    public List<AppEvent> findByBoostpzp3(Integer boostPzP3) {
        return appeventRepository.findByBoostpzp3(boostPzP3);
    }

    public List<AppEvent> findByPedalafp1(Integer pedalAfP1) {
        return appeventRepository.findByPedalafp1(pedalAfP1);
    }

    public List<AppEvent> findByBoostafp1(Integer boostAfP1) {
        return appeventRepository.findByBoostafp1(boostAfP1);
    }

    public List<AppEvent> findByPedalafp2(Integer pedalAfP2) {
        return appeventRepository.findByPedalafp2(pedalAfP2);
    }

    public List<AppEvent> findByBoostafp2(Integer boostAfP2) {
        return appeventRepository.findByBoostafp2(boostAfP2);
    }

    public List<AppEvent> findByPedalafp3(Integer pedalAfP3) {
        return appeventRepository.findByPedalafp3(pedalAfP3);
    }

    public List<AppEvent> findByBoostafp3(Integer boostAfP3) {
        return appeventRepository.findByBoostafp3(boostAfP3);
    }

    public List<AppEvent> findByPedalafwop1(Integer pedalAfwoP1) {
        return appeventRepository.findByPedalafwop1(pedalAfwoP1);
    }

    public List<AppEvent> findByPedalafwop2(Integer pedalAfwoP2) {
        return appeventRepository.findByPedalafwop2(pedalAfwoP2);
    }

    public List<AppEvent> findByPedalafwop3(Integer pedalAfwoP3) {
        return appeventRepository.findByPedalafwop3(pedalAfwoP3);
    }

    public List<AppEvent> findByOnstart(Integer onStart) {
        return appeventRepository.findByOnstart(onStart);
    }

    public List<AppEvent> findByOnafter7h(Integer onAfter7H) {
        return appeventRepository.findByOnafter7h(onAfter7H);
    }

}