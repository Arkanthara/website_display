package ems.services;

import ems.models.ModemErrors;
import ems.repositories.ModemErrorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ModemErrorsService {
    @Autowired
    private ModemErrorsRepository modemerrorsRepository;

    public List<ModemErrors> findAll() {
        return modemerrorsRepository.findAll();
    }

    public ModemErrors findById(Long id) {
        return modemerrorsRepository.findById(id).orElse(null);
    }

    public ModemErrors save(ModemErrors modemerrors) {
        return modemerrorsRepository.save(modemerrors);
    }

    public void deleteById(Long id) {
        modemerrorsRepository.deleteById(id);
    }

    public List<ModemErrors> findBySerialnumber(String serialNumber) {
        return modemerrorsRepository.findBySerialnumber(serialNumber);
    }

    public List<ModemErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return modemerrorsRepository.findByTimeBetween(start, end);
    }

    public List<ModemErrors> findByTimeout(Integer timeout) {
        return modemerrorsRepository.findByTimeout(timeout);
    }

    public List<ModemErrors> findByHeader(Integer header) {
        return modemerrorsRepository.findByHeader(header);
    }

    public List<ModemErrors> findByFooter(Integer footer) {
        return modemerrorsRepository.findByFooter(footer);
    }

    public List<ModemErrors> findByCrc(Integer crc) {
        return modemerrorsRepository.findByCrc(crc);
    }

    public List<ModemErrors> findByCtx(Integer ctx) {
        return modemerrorsRepository.findByCtx(ctx);
    }

}