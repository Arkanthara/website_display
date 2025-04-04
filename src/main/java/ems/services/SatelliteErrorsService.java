package ems.services;

import ems.models.SatelliteErrors;
import ems.repositories.SatelliteErrorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SatelliteErrorsService {
    @Autowired
    private SatelliteErrorsRepository satelliteerrorsRepository;

    public List<SatelliteErrors> findAll() {
        return satelliteerrorsRepository.findAll();
    }

    public SatelliteErrors findById(Long id) {
        return satelliteerrorsRepository.findById(id).orElse(null);
    }

    public SatelliteErrors save(SatelliteErrors satelliteerrors) {
        return satelliteerrorsRepository.save(satelliteerrors);
    }

    public void deleteById(Long id) {
        satelliteerrorsRepository.deleteById(id);
    }

    public List<SatelliteErrors> findBySerialnumber(String serialNumber) {
        return satelliteerrorsRepository.findBySerialnumber(serialNumber);
    }

    public List<SatelliteErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return satelliteerrorsRepository.findByTimeBetween(start, end);
    }

    public List<SatelliteErrors> findByRebootaf(Integer rebootAf) {
        return satelliteerrorsRepository.findByRebootaf(rebootAf);
    }

    public List<SatelliteErrors> findByRebootpz(Integer rebootPz) {
        return satelliteerrorsRepository.findByRebootpz(rebootPz);
    }

    public List<SatelliteErrors> findByLostaf(Integer lostAf) {
        return satelliteerrorsRepository.findByLostaf(lostAf);
    }

    public List<SatelliteErrors> findByLostpz(Integer lostPz) {
        return satelliteerrorsRepository.findByLostpz(lostPz);
    }

    public List<SatelliteErrors> findByNoaf(Integer noAf) {
        return satelliteerrorsRepository.findByNoaf(noAf);
    }

    public List<SatelliteErrors> findByNopz(Integer noPz) {
        return satelliteerrorsRepository.findByNopz(noPz);
    }

    public List<SatelliteErrors> findByNosat(Integer noSat) {
        return satelliteerrorsRepository.findByNosat(noSat);
    }

}