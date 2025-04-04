package ems.services;

import ems.models.Version;
import ems.repositories.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VersionService {
    @Autowired
    private VersionRepository versionRepository;

    public List<Version> findAll() {
        return versionRepository.findAll();
    }

    public Version findById(Long id) {
        return versionRepository.findById(id).orElse(null);
    }

    public Version save(Version version) {
        return versionRepository.save(version);
    }

    public void deleteById(Long id) {
        versionRepository.deleteById(id);
    }

    public List<Version> findBySerialnumber(String serialNumber) {
        return versionRepository.findBySerialnumber(serialNumber);
    }

    public List<Version> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return versionRepository.findByTimeBetween(start, end);
    }

    public List<Version> findByVersionmain(String versionMain) {
        return versionRepository.findByVersionmain(versionMain);
    }

    public List<Version> findByVersionus(String versionUs) {
        return versionRepository.findByVersionus(versionUs);
    }

    public List<Version> findByVersionaf(String versionAf) {
        return versionRepository.findByVersionaf(versionAf);
    }

    public List<Version> findByVersionpz(String versionPz) {
        return versionRepository.findByVersionpz(versionPz);
    }

    public List<Version> findByVersionesp32(String versionEsp32) {
        return versionRepository.findByVersionesp32(versionEsp32);
    }

}