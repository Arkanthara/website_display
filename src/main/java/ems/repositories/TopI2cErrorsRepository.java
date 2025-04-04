package ems.repositories;

import ems.models.TopI2cErrors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TopI2cErrorsRepository extends JpaRepository<TopI2cErrors, Long> {
    // Custom query methods
    List<TopI2cErrors> findBySerialnumber(String serialNumber);
    List<TopI2cErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<TopI2cErrors> findByTimeout(Integer timeout);
    List<TopI2cErrors> findByAddrnak(Integer addrnak);
    List<TopI2cErrors> findByOthers(Integer others);
    List<TopI2cErrors> findByReadko(Integer readko);
    List<TopI2cErrors> findByWriteko(Integer writeko);
    List<TopI2cErrors> findByFwriteko(Integer fwriteko);
    List<TopI2cErrors> findAll();
}