package ems.repositories;

import ems.models.ModemErrors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ModemErrorsRepository extends JpaRepository<ModemErrors, Long> {
    // Custom query methods
    List<ModemErrors> findBySerialnumber(String serialNumber);
    List<ModemErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<ModemErrors> findByTimeout(Integer timeout);
    List<ModemErrors> findByHeader(Integer header);
    List<ModemErrors> findByFooter(Integer footer);
    List<ModemErrors> findByCrc(Integer crc);
    List<ModemErrors> findByCtx(Integer ctx);
    List<ModemErrors> findAll();
}