package ems.services;

import ems.models.AfErrors;
import ems.repositories.AfErrorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AfErrorsService {
    @Autowired
    private AfErrorsRepository aferrorsRepository;

    public List<AfErrors> findAll() {
        return aferrorsRepository.findAll();
    }

    public AfErrors findById(Long id) {
        return aferrorsRepository.findById(id).orElse(null);
    }

    public AfErrors save(AfErrors aferrors) {
        return aferrorsRepository.save(aferrors);
    }

    public void deleteById(Long id) {
        aferrorsRepository.deleteById(id);
    }

    public List<AfErrors> findBySerialnumber(String serialNumber) {
        return aferrorsRepository.findBySerialnumber(serialNumber);
    }

    public List<AfErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return aferrorsRepository.findByTimeBetween(start, end);
    }

    public List<AfErrors> findByTimeout(Integer timeout) {
        return aferrorsRepository.findByTimeout(timeout);
    }

    public List<AfErrors> findByNak(Integer nak) {
        return aferrorsRepository.findByNak(nak);
    }

    public List<AfErrors> findByFrameinv(Integer frameinv) {
        return aferrorsRepository.findByFrameinv(frameinv);
    }

    public List<AfErrors> findByDatainv(Integer dataInv) {
        return aferrorsRepository.findByDatainv(dataInv);
    }

}