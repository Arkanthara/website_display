package ems.services;

import ems.models.AdcErrors;
import ems.repositories.AdcErrorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdcErrorsService {
    @Autowired
    private AdcErrorsRepository adcerrorsRepository;

    public List<AdcErrors> findAll() {
        return adcerrorsRepository.findAll();
    }

    public AdcErrors findById(Long id) {
        return adcerrorsRepository.findById(id).orElse(null);
    }

    public AdcErrors save(AdcErrors adcerrors) {
        return adcerrorsRepository.save(adcerrors);
    }

    public void deleteById(Long id) {
        adcerrorsRepository.deleteById(id);
    }

    public List<AdcErrors> findBySerialnumber(String serialNumber) {
        return adcerrorsRepository.findBySerialnumber(serialNumber);
    }

    public List<AdcErrors> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return adcerrorsRepository.findByTimeBetween(start, end);
    }

    public List<AdcErrors> findBySamplingerror(Integer samplingError) {
        return adcerrorsRepository.findBySamplingerror(samplingError);
    }

    public List<AdcErrors> findByFullfepressure(Integer fullFEPressure) {
        return adcerrorsRepository.findByFullfepressure(fullFEPressure);
    }

    public List<AdcErrors> findByFullfetempin(Integer fullFETempIn) {
        return adcerrorsRepository.findByFullfetempin(fullFETempIn);
    }

    public List<AdcErrors> findByFullfetempout(Integer fullFETempOut) {
        return adcerrorsRepository.findByFullfetempout(fullFETempOut);
    }

    public List<AdcErrors> findByFullfeumain(Integer fullFEUmain) {
        return adcerrorsRepository.findByFullfeumain(fullFEUmain);
    }

    public List<AdcErrors> findByFilterepressure(Integer filterEPressure) {
        return adcerrorsRepository.findByFilterepressure(filterEPressure);
    }

    public List<AdcErrors> findByFilteretempin(Integer filterETempIn) {
        return adcerrorsRepository.findByFilteretempin(filterETempIn);
    }

    public List<AdcErrors> findByFilteretempout(Integer filterETempOut) {
        return adcerrorsRepository.findByFilteretempout(filterETempOut);
    }

    public List<AdcErrors> findByFiltereumain(Integer filterEUmain) {
        return adcerrorsRepository.findByFiltereumain(filterEUmain);
    }

    public List<AdcErrors> findBySampleepressure(Integer sampleEPressure) {
        return adcerrorsRepository.findBySampleepressure(sampleEPressure);
    }

    public List<AdcErrors> findBySampleetempin(Integer sampleETempIn) {
        return adcerrorsRepository.findBySampleetempin(sampleETempIn);
    }

    public List<AdcErrors> findBySampleetempout(Integer sampleETempOut) {
        return adcerrorsRepository.findBySampleetempout(sampleETempOut);
    }

    public List<AdcErrors> findBySampleeumain(Integer sampleEUmain) {
        return adcerrorsRepository.findBySampleeumain(sampleEUmain);
    }

    public List<AdcErrors> findByNosamepressure(Integer noSamEPressure) {
        return adcerrorsRepository.findByNosamepressure(noSamEPressure);
    }

    public List<AdcErrors> findByNosametempin(Integer noSamETempIn) {
        return adcerrorsRepository.findByNosametempin(noSamETempIn);
    }

    public List<AdcErrors> findByNosametempout(Integer noSamETempOut) {
        return adcerrorsRepository.findByNosametempout(noSamETempOut);
    }

    public List<AdcErrors> findByNosameumain(Integer noSamEUmain) {
        return adcerrorsRepository.findByNosameumain(noSamEUmain);
    }

}