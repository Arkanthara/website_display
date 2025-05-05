package ems.repositories;

import ems.models.Phony;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface PhonyRepository extends JpaRepository<Phony, Long> {
    List<Phony> findBySerialNumber(String serialNumber);
    List<Phony> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Phony> findByTime(LocalDateTime time);
    List<Phony> findByColumn1(Integer column1);
    List<Phony> findByColumn2(String column2);
    List<Phony> findByColumn3(String column3);
}