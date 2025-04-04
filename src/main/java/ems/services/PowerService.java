package ems.services;

import ems.models.Power;
import ems.repositories.PowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PowerService {
    @Autowired
    private PowerRepository powerRepository;

    public List<Power> findAll() {
        return powerRepository.findAll();
    }

    public Power findById(Long id) {
        return powerRepository.findById(id).orElse(null);
    }

    public Power save(Power power) {
        return powerRepository.save(power);
    }

    public void deleteById(Long id) {
        powerRepository.deleteById(id);
    }

    public List<Power> findBySerialnumber(String serialNumber) {
        return powerRepository.findBySerialnumber(serialNumber);
    }

    public List<Power> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return powerRepository.findByTimeBetween(start, end);
    }

    public List<Power> findByTimepowerons(Integer timePowerOnS) {
        return powerRepository.findByTimepowerons(timePowerOnS);
    }

    public List<Power> findByTimeons(Integer timeOnS) {
        return powerRepository.findByTimeons(timeOnS);
    }

    public List<Power> findByPoweroncounter(Integer powerOnCounter) {
        return powerRepository.findByPoweroncounter(powerOnCounter);
    }

}