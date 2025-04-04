package ems.repositories;

import ems.models.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MetadataRepository extends JpaRepository<Metadata, Long> {
    // Custom query methods
    List<Metadata> findBySerialnumber(String serialNumber);
    List<Metadata> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Metadata> findByData(String data);
    List<Metadata> findByPairingstatus(Boolean pairingStatus);
    List<Metadata> findByStatsmappingversion(String statsMappingVersion);
    List<Metadata> findAll();
}