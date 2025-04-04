package ems.services;

import ems.models.PneumaticErrors;
import ems.repositories.PneumaticErrorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PneumaticErrorsService {
    @Autowired
    private PneumaticErrorsRepository pneumaticerrorsRepository;

    public List<PneumaticErrors> findAll() {
        return pneumaticerrorsRepository.findAll();
    }

    public PneumaticErrors findById(Long id) {
        return pneumaticerrorsRepository.findById(id).orElse(null);
    }

    public PneumaticErrors save(PneumaticErrors pneumaticerrors) {
        return pneumaticerrorsRepository.save(pneumaticerrors);
    }

    public void deleteById(Long id) {
        pneumaticerrorsRepository.deleteById(id);
    }

    public List<PneumaticErrors> findBySerialnumber(String serialNumber) {
        return pneumaticerrorsRepository.findBySerialnumber(serialNumber);
    }

    public List<PneumaticErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return pneumaticerrorsRepository.findByTimeBetween(start, end);
    }

    public List<PneumaticErrors> findBySensorpress(Integer sensorPress) {
        return pneumaticerrorsRepository.findBySensorpress(sensorPress);
    }

    public List<PneumaticErrors> findByOverpress(Integer overPress) {
        return pneumaticerrorsRepository.findByOverpress(overPress);
    }

    public List<PneumaticErrors> findByAfsat(Integer afsat) {
        return pneumaticerrorsRepository.findByAfsat(afsat);
    }

    public List<PneumaticErrors> findByClogged(Integer clogged) {
        return pneumaticerrorsRepository.findByClogged(clogged);
    }

    public List<PneumaticErrors> findByNochamber(Integer noChamber) {
        return pneumaticerrorsRepository.findByNochamber(noChamber);
    }

    public List<PneumaticErrors> findByNoair(Integer noAir) {
        return pneumaticerrorsRepository.findByNoair(noAir);
    }

    public List<PneumaticErrors> findByTimeoutup(Integer timeoutUp) {
        return pneumaticerrorsRepository.findByTimeoutup(timeoutUp);
    }

}