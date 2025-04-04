package ems.services;

import ems.models.PzErrors;
import ems.repositories.PzErrorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PzErrorsService {
    @Autowired
    private PzErrorsRepository pzerrorsRepository;

    public List<PzErrors> findAll() {
        return pzerrorsRepository.findAll();
    }

    public PzErrors findById(Long id) {
        return pzerrorsRepository.findById(id).orElse(null);
    }

    public PzErrors save(PzErrors pzerrors) {
        return pzerrorsRepository.save(pzerrors);
    }

    public void deleteById(Long id) {
        pzerrorsRepository.deleteById(id);
    }

    public List<PzErrors> findBySerialnumber(String serialNumber) {
        return pzerrorsRepository.findBySerialnumber(serialNumber);
    }

    public List<PzErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return pzerrorsRepository.findByTimeBetween(start, end);
    }

    public List<PzErrors> findByTimeout(Integer timeout) {
        return pzerrorsRepository.findByTimeout(timeout);
    }

    public List<PzErrors> findByNak(Integer nak) {
        return pzerrorsRepository.findByNak(nak);
    }

    public List<PzErrors> findByFrameinv(Integer frameinv) {
        return pzerrorsRepository.findByFrameinv(frameinv);
    }

    public List<PzErrors> findByDatainv(Integer dataInv) {
        return pzerrorsRepository.findByDatainv(dataInv);
    }

}