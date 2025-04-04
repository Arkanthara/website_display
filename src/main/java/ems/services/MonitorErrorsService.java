package ems.services;

import ems.models.MonitorErrors;
import ems.repositories.MonitorErrorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MonitorErrorsService {
    @Autowired
    private MonitorErrorsRepository monitorerrorsRepository;

    public List<MonitorErrors> findAll() {
        return monitorerrorsRepository.findAll();
    }

    public MonitorErrors findById(Long id) {
        return monitorerrorsRepository.findById(id).orElse(null);
    }

    public MonitorErrors save(MonitorErrors monitorerrors) {
        return monitorerrorsRepository.save(monitorerrors);
    }

    public void deleteById(Long id) {
        monitorerrorsRepository.deleteById(id);
    }

    public List<MonitorErrors> findBySerialnumber(String serialNumber) {
        return monitorerrorsRepository.findBySerialnumber(serialNumber);
    }

    public List<MonitorErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return monitorerrorsRepository.findByTimeBetween(start, end);
    }

    public List<MonitorErrors> findBySensorpwr(Integer sensorPwr) {
        return monitorerrorsRepository.findBySensorpwr(sensorPwr);
    }

    public List<MonitorErrors> findByNopwr(Integer noPwr) {
        return monitorerrorsRepository.findByNopwr(noPwr);
    }

    public List<MonitorErrors> findByPowerloss(Integer powerLoss) {
        return monitorerrorsRepository.findByPowerloss(powerLoss);
    }

    public List<MonitorErrors> findByValveaccesserror(Integer valveAccessError) {
        return monitorerrorsRepository.findByValveaccesserror(valveAccessError);
    }

    public List<MonitorErrors> findByValveovercurrent(Integer valveOverCurrent) {
        return monitorerrorsRepository.findByValveovercurrent(valveOverCurrent);
    }

    public List<MonitorErrors> findByValveopenload(Integer valveOpenLoad) {
        return monitorerrorsRepository.findByValveopenload(valveOpenLoad);
    }

    public List<MonitorErrors> findByUsmodule(Integer usModule) {
        return monitorerrorsRepository.findByUsmodule(usModule);
    }

}