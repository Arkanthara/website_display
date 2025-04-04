package ems.services;

import ems.models.TopI2cErrors;
import ems.repositories.TopI2cErrorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopI2cErrorsService {
    @Autowired
    private TopI2cErrorsRepository topi2cerrorsRepository;

    public List<TopI2cErrors> findAll() {
        return topi2cerrorsRepository.findAll();
    }

    public TopI2cErrors findById(Long id) {
        return topi2cerrorsRepository.findById(id).orElse(null);
    }

    public TopI2cErrors save(TopI2cErrors topi2cerrors) {
        return topi2cerrorsRepository.save(topi2cerrors);
    }

    public void deleteById(Long id) {
        topi2cerrorsRepository.deleteById(id);
    }

    public List<TopI2cErrors> findBySerialnumber(String serialNumber) {
        return topi2cerrorsRepository.findBySerialnumber(serialNumber);
    }

    public List<TopI2cErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return topi2cerrorsRepository.findByTimeBetween(start, end);
    }

    public List<TopI2cErrors> findByTimeout(Integer timeout) {
        return topi2cerrorsRepository.findByTimeout(timeout);
    }

    public List<TopI2cErrors> findByAddrnak(Integer addrnak) {
        return topi2cerrorsRepository.findByAddrnak(addrnak);
    }

    public List<TopI2cErrors> findByOthers(Integer others) {
        return topi2cerrorsRepository.findByOthers(others);
    }

    public List<TopI2cErrors> findByReadko(Integer readko) {
        return topi2cerrorsRepository.findByReadko(readko);
    }

    public List<TopI2cErrors> findByWriteko(Integer writeko) {
        return topi2cerrorsRepository.findByWriteko(writeko);
    }

    public List<TopI2cErrors> findByFwriteko(Integer fwriteko) {
        return topi2cerrorsRepository.findByFwriteko(fwriteko);
    }

}