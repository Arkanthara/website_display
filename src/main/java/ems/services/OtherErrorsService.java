package ems.services;

import ems.models.OtherErrors;
import ems.repositories.OtherErrorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OtherErrorsService {
    @Autowired
    private OtherErrorsRepository othererrorsRepository;

    public List<OtherErrors> findAll() {
        return othererrorsRepository.findAll();
    }

    public OtherErrors findById(Long id) {
        return othererrorsRepository.findById(id).orElse(null);
    }

    public OtherErrors save(OtherErrors othererrors) {
        return othererrorsRepository.save(othererrors);
    }

    public void deleteById(Long id) {
        othererrorsRepository.deleteById(id);
    }

    public List<OtherErrors> findBySerialnumber(String serialNumber) {
        return othererrorsRepository.findBySerialnumber(serialNumber);
    }

    public List<OtherErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return othererrorsRepository.findByTimeBetween(start, end);
    }

    public List<OtherErrors> findByToptouchreset(Integer topTouchReset) {
        return othererrorsRepository.findByToptouchreset(topTouchReset);
    }

    public List<OtherErrors> findBySoundplayerr(Integer soundPlayErr) {
        return othererrorsRepository.findBySoundplayerr(soundPlayErr);
    }

}