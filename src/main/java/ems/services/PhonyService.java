package ems.services;

import ems.models.Phony;
import ems.repositories.PhonyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PhonyService {
    @Autowired private PhonyRepository phonyRepository;

    public List<Phony> findAll() {
        return phonyRepository.findAll();
    }

    public Phony save(Phony obj) {
        return phonyRepository.save(obj);
    }
    public void deleteById(Long id) {
        phonyRepository.deleteById(id);
    }

    public List<Phony> findBySerialNumber(String val) {
        return phonyRepository.findBySerialNumber(val);
    }

    public List<Phony> findByTimeBetween(LocalDateTime s, LocalDateTime e) {
        return phonyRepository.findByTimeBetween(s, e);
    }

    public List<Phony> findByTime(LocalDateTime val) {
        return phonyRepository.findByTime(val);
    }

    public List<Phony> findByColumn1(Integer val) {
        return phonyRepository.findByColumn1(val);
    }

    public List<Phony> findByColumn2(String val) {
        return phonyRepository.findByColumn2(val);
    }

    public List<Phony> findByColumn3(String val) {
        return phonyRepository.findByColumn3(val);
    }
}