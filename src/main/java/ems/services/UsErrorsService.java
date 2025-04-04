package ems.services;

import ems.models.UsErrors;
import ems.repositories.UsErrorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsErrorsService {
    @Autowired
    private UsErrorsRepository userrorsRepository;

    public List<UsErrors> findAll() {
        return userrorsRepository.findAll();
    }

    public UsErrors findById(Long id) {
        return userrorsRepository.findById(id).orElse(null);
    }

    public UsErrors save(UsErrors userrors) {
        return userrorsRepository.save(userrors);
    }

    public void deleteById(Long id) {
        userrorsRepository.deleteById(id);
    }

    public List<UsErrors> findBySerialnumber(String serialNumber) {
        return userrorsRepository.findBySerialnumber(serialNumber);
    }

    public List<UsErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return userrorsRepository.findByTimeBetween(start, end);
    }

    public List<UsErrors> findByTimeout(Integer timeout) {
        return userrorsRepository.findByTimeout(timeout);
    }

    public List<UsErrors> findByNak(Integer nak) {
        return userrorsRepository.findByNak(nak);
    }

    public List<UsErrors> findByFrameinv(Integer frameinv) {
        return userrorsRepository.findByFrameinv(frameinv);
    }

    public List<UsErrors> findByDatainv(Integer dataInv) {
        return userrorsRepository.findByDatainv(dataInv);
    }

}