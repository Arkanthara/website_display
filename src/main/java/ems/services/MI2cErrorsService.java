package ems.services;

import ems.models.MI2cErrors;
import ems.repositories.MI2cErrorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MI2cErrorsService {
    @Autowired
    private MI2cErrorsRepository mi2cerrorsRepository;

    public List<MI2cErrors> findAll() {
        return mi2cerrorsRepository.findAll();
    }

    public MI2cErrors findById(Long id) {
        return mi2cerrorsRepository.findById(id).orElse(null);
    }

    public MI2cErrors save(MI2cErrors mi2cerrors) {
        return mi2cerrorsRepository.save(mi2cerrors);
    }

    public void deleteById(Long id) {
        mi2cerrorsRepository.deleteById(id);
    }

    public List<MI2cErrors> findBySerialnumber(String serialNumber) {
        return mi2cerrorsRepository.findBySerialnumber(serialNumber);
    }

    public List<MI2cErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return mi2cerrorsRepository.findByTimeBetween(start, end);
    }

    public List<MI2cErrors> findByTimeout(Integer timeout) {
        return mi2cerrorsRepository.findByTimeout(timeout);
    }

    public List<MI2cErrors> findByAddrnak(Integer addrnak) {
        return mi2cerrorsRepository.findByAddrnak(addrnak);
    }

    public List<MI2cErrors> findByOthers(Integer others) {
        return mi2cerrorsRepository.findByOthers(others);
    }

    public List<MI2cErrors> findByReadko(Integer readko) {
        return mi2cerrorsRepository.findByReadko(readko);
    }

    public List<MI2cErrors> findByWriteko(Integer writeko) {
        return mi2cerrorsRepository.findByWriteko(writeko);
    }

    public List<MI2cErrors> findByFwriteko(Integer fwriteko) {
        return mi2cerrorsRepository.findByFwriteko(fwriteko);
    }

}