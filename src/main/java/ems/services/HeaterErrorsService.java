package ems.services;

import ems.models.HeaterErrors;
import ems.repositories.HeaterErrorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HeaterErrorsService {
    @Autowired
    private HeaterErrorsRepository heatererrorsRepository;

    public List<HeaterErrors> findAll() {
        return heatererrorsRepository.findAll();
    }

    public HeaterErrors findById(Long id) {
        return heatererrorsRepository.findById(id).orElse(null);
    }

    public HeaterErrors save(HeaterErrors heatererrors) {
        return heatererrorsRepository.save(heatererrors);
    }

    public void deleteById(Long id) {
        heatererrorsRepository.deleteById(id);
    }

    public List<HeaterErrors> findBySerialnumber(String serialNumber) {
        return heatererrorsRepository.findBySerialnumber(serialNumber);
    }

    public List<HeaterErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return heatererrorsRepository.findByTimeBetween(start, end);
    }

    public List<HeaterErrors> findByHwsafetytemp(Integer hwSafetyTemp) {
        return heatererrorsRepository.findByHwsafetytemp(hwSafetyTemp);
    }

    public List<HeaterErrors> findBySensortc(Integer sensorTc) {
        return heatererrorsRepository.findBySensortc(sensorTc);
    }

    public List<HeaterErrors> findByBurn(Integer burn) {
        return heatererrorsRepository.findByBurn(burn);
    }

    public List<HeaterErrors> findByTcdiff(Integer tcDiff) {
        return heatererrorsRepository.findByTcdiff(tcDiff);
    }

    public List<HeaterErrors> findByDeltatarget(Integer deltaTarget) {
        return heatererrorsRepository.findByDeltatarget(deltaTarget);
    }

    public List<HeaterErrors> findByHot(Integer hot) {
        return heatererrorsRepository.findByHot(hot);
    }

    public List<HeaterErrors> findByCold(Integer cold) {
        return heatererrorsRepository.findByCold(cold);
    }

    public List<HeaterErrors> findByFull(Integer full) {
        return heatererrorsRepository.findByFull(full);
    }

}