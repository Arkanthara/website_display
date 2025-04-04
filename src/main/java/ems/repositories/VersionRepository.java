package ems.repositories;

import ems.models.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VersionRepository extends JpaRepository<Version, Long> {
    // Custom query methods
    List<Version> findBySerialnumber(String serialNumber);
    List<Version> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Version> findByVersionmain(String versionMain);
    List<Version> findByVersionus(String versionUs);
    List<Version> findByVersionaf(String versionAf);
    List<Version> findByVersionpz(String versionPz);
    List<Version> findByVersionesp32(String versionEsp32);
    List<Version> findAll();
}