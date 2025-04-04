package ems.controllers;

import ems.models.AdcErrors;
import ems.services.AdcErrorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/adcerrors")
public class AdcErrorsController {

    @Autowired
    private AdcErrorsService adcerrorsService;

    @GetMapping
    public List<AdcErrors> getAllAdcErrorss() {
        return adcerrorsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdcErrors> getAdcErrorsById(@PathVariable Long id) {
        AdcErrors adcerrors = adcerrorsService.findById(id);
        if (adcerrors == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(adcerrors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AdcErrors> createAdcErrors(@RequestBody AdcErrors adcerrors) {
        AdcErrors created = adcerrorsService.save(adcerrors);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdcErrors> updateAdcErrors(@PathVariable Long id, @RequestBody AdcErrors adcerrors) {
        AdcErrors updated = adcerrorsService.save(adcerrors);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdcErrors(@PathVariable Long id) {
        adcerrorsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<AdcErrors> findBySerialnumber(@RequestParam String serialNumber) {
        return adcerrorsService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<AdcErrors> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return adcerrorsService.findByTimeBetween(start, end);
    }

    @GetMapping("/findBySamplingerror")
    public List<AdcErrors> findBySamplingerror(@RequestParam Integer samplingError) {
        return adcerrorsService.findBySamplingerror(samplingError);
    }

    @GetMapping("/findByFullfepressure")
    public List<AdcErrors> findByFullfepressure(@RequestParam Integer fullFEPressure) {
        return adcerrorsService.findByFullfepressure(fullFEPressure);
    }

    @GetMapping("/findByFullfetempin")
    public List<AdcErrors> findByFullfetempin(@RequestParam Integer fullFETempIn) {
        return adcerrorsService.findByFullfetempin(fullFETempIn);
    }

    @GetMapping("/findByFullfetempout")
    public List<AdcErrors> findByFullfetempout(@RequestParam Integer fullFETempOut) {
        return adcerrorsService.findByFullfetempout(fullFETempOut);
    }

    @GetMapping("/findByFullfeumain")
    public List<AdcErrors> findByFullfeumain(@RequestParam Integer fullFEUmain) {
        return adcerrorsService.findByFullfeumain(fullFEUmain);
    }

    @GetMapping("/findByFilterepressure")
    public List<AdcErrors> findByFilterepressure(@RequestParam Integer filterEPressure) {
        return adcerrorsService.findByFilterepressure(filterEPressure);
    }

    @GetMapping("/findByFilteretempin")
    public List<AdcErrors> findByFilteretempin(@RequestParam Integer filterETempIn) {
        return adcerrorsService.findByFilteretempin(filterETempIn);
    }

    @GetMapping("/findByFilteretempout")
    public List<AdcErrors> findByFilteretempout(@RequestParam Integer filterETempOut) {
        return adcerrorsService.findByFilteretempout(filterETempOut);
    }

    @GetMapping("/findByFiltereumain")
    public List<AdcErrors> findByFiltereumain(@RequestParam Integer filterEUmain) {
        return adcerrorsService.findByFiltereumain(filterEUmain);
    }

    @GetMapping("/findBySampleepressure")
    public List<AdcErrors> findBySampleepressure(@RequestParam Integer sampleEPressure) {
        return adcerrorsService.findBySampleepressure(sampleEPressure);
    }

    @GetMapping("/findBySampleetempin")
    public List<AdcErrors> findBySampleetempin(@RequestParam Integer sampleETempIn) {
        return adcerrorsService.findBySampleetempin(sampleETempIn);
    }

    @GetMapping("/findBySampleetempout")
    public List<AdcErrors> findBySampleetempout(@RequestParam Integer sampleETempOut) {
        return adcerrorsService.findBySampleetempout(sampleETempOut);
    }

    @GetMapping("/findBySampleeumain")
    public List<AdcErrors> findBySampleeumain(@RequestParam Integer sampleEUmain) {
        return adcerrorsService.findBySampleeumain(sampleEUmain);
    }

    @GetMapping("/findByNosamepressure")
    public List<AdcErrors> findByNosamepressure(@RequestParam Integer noSamEPressure) {
        return adcerrorsService.findByNosamepressure(noSamEPressure);
    }

    @GetMapping("/findByNosametempin")
    public List<AdcErrors> findByNosametempin(@RequestParam Integer noSamETempIn) {
        return adcerrorsService.findByNosametempin(noSamETempIn);
    }

    @GetMapping("/findByNosametempout")
    public List<AdcErrors> findByNosametempout(@RequestParam Integer noSamETempOut) {
        return adcerrorsService.findByNosametempout(noSamETempOut);
    }

    @GetMapping("/findByNosameumain")
    public List<AdcErrors> findByNosameumain(@RequestParam Integer noSamEUmain) {
        return adcerrorsService.findByNosameumain(noSamEUmain);
    }

}