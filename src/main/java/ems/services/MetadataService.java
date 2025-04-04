package ems.services;

import ems.models.Metadata;
import ems.repositories.MetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MetadataService {
    @Autowired
    private MetadataRepository metadataRepository;

    public List<Metadata> findAll() {
        return metadataRepository.findAll();
    }

    public Metadata findById(Long id) {
        return metadataRepository.findById(id).orElse(null);
    }

    public Metadata save(Metadata metadata) {
        return metadataRepository.save(metadata);
    }

    public void deleteById(Long id) {
        metadataRepository.deleteById(id);
    }

    public List<Metadata> findBySerialnumber(String serialNumber) {
        return metadataRepository.findBySerialnumber(serialNumber);
    }

    public List<Metadata> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return metadataRepository.findByTimeBetween(start, end);
    }

    public List<Metadata> findByData(String data) {
        return metadataRepository.findByData(data);
    }

    public List<Metadata> findByPairingstatus(Boolean pairingStatus) {
        return metadataRepository.findByPairingstatus(pairingStatus);
    }

    public List<Metadata> findByStatsmappingversion(String statsMappingVersion) {
        return metadataRepository.findByStatsmappingversion(statsMappingVersion);
    }

}