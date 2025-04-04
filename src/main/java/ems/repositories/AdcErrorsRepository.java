package ems.repositories;

import ems.models.AdcErrors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdcErrorsRepository extends JpaRepository<AdcErrors, Long> {
    // Custom query methods
    List<AdcErrors> findBySerialnumber(String serialNumber);
    List<AdcErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    List<AdcErrors> findBySamplingerror(Integer samplingError);
    List<AdcErrors> findByFullfepressure(Integer fullFEPressure);
    List<AdcErrors> findByFullfetempin(Integer fullFETempIn);
    List<AdcErrors> findByFullfetempout(Integer fullFETempOut);
    List<AdcErrors> findByFullfeumain(Integer fullFEUmain);
    List<AdcErrors> findByFilterepressure(Integer filterEPressure);
    List<AdcErrors> findByFilteretempin(Integer filterETempIn);
    List<AdcErrors> findByFilteretempout(Integer filterETempOut);
    List<AdcErrors> findByFiltereumain(Integer filterEUmain);
    List<AdcErrors> findBySampleepressure(Integer sampleEPressure);
    List<AdcErrors> findBySampleetempin(Integer sampleETempIn);
    List<AdcErrors> findBySampleetempout(Integer sampleETempOut);
    List<AdcErrors> findBySampleeumain(Integer sampleEUmain);
    List<AdcErrors> findByNosamepressure(Integer noSamEPressure);
    List<AdcErrors> findByNosametempin(Integer noSamETempIn);
    List<AdcErrors> findByNosametempout(Integer noSamETempOut);
    List<AdcErrors> findByNosameumain(Integer noSamEUmain);
    List<AdcErrors> findAll();
}