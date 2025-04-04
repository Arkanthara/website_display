package ems.repositories;

import ems.models.MI2cErrors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MI2cErrorsRepository extends JpaRepository<MI2cErrors, Long> {
    // Custom query methods
    List<MI2cErrors> findBySerialnumber(String serialNumber);
    List<MI2cErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<MI2cErrors> findByTimeout(Integer timeout);
    List<MI2cErrors> findByAddrnak(Integer addrnak);
    List<MI2cErrors> findByOthers(Integer others);
    List<MI2cErrors> findByReadko(Integer readko);
    List<MI2cErrors> findByWriteko(Integer writeko);
    List<MI2cErrors> findByFwriteko(Integer fwriteko);
    List<MI2cErrors> findAll();
}