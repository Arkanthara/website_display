package ems.services;

import ems.models.AppTime;
import ems.repositories.AppTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppTimeService {
    @Autowired
    private AppTimeRepository apptimeRepository;

    public List<AppTime> findAll() {
        return apptimeRepository.findAll();
    }

    public AppTime findById(Long id) {
        return apptimeRepository.findById(id).orElse(null);
    }

    public AppTime save(AppTime apptime) {
        return apptimeRepository.save(apptime);
    }

    public void deleteById(Long id) {
        apptimeRepository.deleteById(id);
    }

    public List<AppTime> findBySerialnumber(String serialNumber) {
        return apptimeRepository.findBySerialnumber(serialNumber);
    }

    public List<AppTime> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return apptimeRepository.findByTimeBetween(start, end);
    }

    public List<AppTime> findByPressurized(Integer pressurized) {
        return apptimeRepository.findByPressurized(pressurized);
    }

    public List<AppTime> findByRunpzp1(Integer runPzP1) {
        return apptimeRepository.findByRunpzp1(runPzP1);
    }

    public List<AppTime> findByBoostpzp1(Integer boostPzP1) {
        return apptimeRepository.findByBoostpzp1(boostPzP1);
    }

    public List<AppTime> findByRunpzp2(Integer runPzP2) {
        return apptimeRepository.findByRunpzp2(runPzP2);
    }

    public List<AppTime> findByBoostpzp2(Integer boostPzP2) {
        return apptimeRepository.findByBoostpzp2(boostPzP2);
    }

    public List<AppTime> findByRunpzp3(Integer runPzP3) {
        return apptimeRepository.findByRunpzp3(runPzP3);
    }

    public List<AppTime> findByBoostpzp3(Integer boostPzP3) {
        return apptimeRepository.findByBoostpzp3(boostPzP3);
    }

    public List<AppTime> findByRunafwateronlyp1(Integer runAfWaterOnlyP1) {
        return apptimeRepository.findByRunafwateronlyp1(runAfWaterOnlyP1);
    }

    public List<AppTime> findByRunafwateronlyp2(Integer runAfWaterOnlyP2) {
        return apptimeRepository.findByRunafwateronlyp2(runAfWaterOnlyP2);
    }

    public List<AppTime> findByRunafwateronlyp3(Integer runAfWaterOnlyP3) {
        return apptimeRepository.findByRunafwateronlyp3(runAfWaterOnlyP3);
    }

    public List<AppTime> findByRunafp1(Integer runAfP1) {
        return apptimeRepository.findByRunafp1(runAfP1);
    }

    public List<AppTime> findByRunafp2(Integer runAfP2) {
        return apptimeRepository.findByRunafp2(runAfP2);
    }

    public List<AppTime> findByRunafp3(Integer runAfP3) {
        return apptimeRepository.findByRunafp3(runAfP3);
    }

    public List<AppTime> findByBoostafp1(Integer boostAfP1) {
        return apptimeRepository.findByBoostafp1(boostAfP1);
    }

    public List<AppTime> findByBoostafp2(Integer boostAfP2) {
        return apptimeRepository.findByBoostafp2(boostAfP2);
    }

    public List<AppTime> findByBoostafp3(Integer boostAfP3) {
        return apptimeRepository.findByBoostafp3(boostAfP3);
    }

    public List<AppTime> findByCleanpause(Integer cleanPause) {
        return apptimeRepository.findByCleanpause(cleanPause);
    }

}