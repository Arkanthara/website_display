package ems.controllers;

import ems.models.SatelliteErrors;
import ems.services.SatelliteErrorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/satelliteerrors")
public class SatelliteErrorsController {

    @Autowired
    private SatelliteErrorsService satelliteerrorsService;

    @GetMapping
    public List<SatelliteErrors> getAllSatelliteErrorss() {
        return satelliteerrorsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SatelliteErrors> getSatelliteErrorsById(@PathVariable Long id) {
        SatelliteErrors satelliteerrors = satelliteerrorsService.findById(id);
        if (satelliteerrors == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(satelliteerrors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SatelliteErrors> createSatelliteErrors(@RequestBody SatelliteErrors satelliteerrors) {
        SatelliteErrors created = satelliteerrorsService.save(satelliteerrors);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SatelliteErrors> updateSatelliteErrors(@PathVariable Long id, @RequestBody SatelliteErrors satelliteerrors) {
        SatelliteErrors updated = satelliteerrorsService.save(satelliteerrors);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSatelliteErrors(@PathVariable Long id) {
        satelliteerrorsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<SatelliteErrors> findBySerialnumber(@RequestParam String serialNumber) {
        return satelliteerrorsService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<SatelliteErrors> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return satelliteerrorsService.findByTimeBetween(start, end);
    }

    @GetMapping("/findByRebootaf")
    public List<SatelliteErrors> findByRebootaf(@RequestParam Integer rebootAf) {
        return satelliteerrorsService.findByRebootaf(rebootAf);
    }

    @GetMapping("/findByRebootpz")
    public List<SatelliteErrors> findByRebootpz(@RequestParam Integer rebootPz) {
        return satelliteerrorsService.findByRebootpz(rebootPz);
    }

    @GetMapping("/findByLostaf")
    public List<SatelliteErrors> findByLostaf(@RequestParam Integer lostAf) {
        return satelliteerrorsService.findByLostaf(lostAf);
    }

    @GetMapping("/findByLostpz")
    public List<SatelliteErrors> findByLostpz(@RequestParam Integer lostPz) {
        return satelliteerrorsService.findByLostpz(lostPz);
    }

    @GetMapping("/findByNoaf")
    public List<SatelliteErrors> findByNoaf(@RequestParam Integer noAf) {
        return satelliteerrorsService.findByNoaf(noAf);
    }

    @GetMapping("/findByNopz")
    public List<SatelliteErrors> findByNopz(@RequestParam Integer noPz) {
        return satelliteerrorsService.findByNopz(noPz);
    }

    @GetMapping("/findByNosat")
    public List<SatelliteErrors> findByNosat(@RequestParam Integer noSat) {
        return satelliteerrorsService.findByNosat(noSat);
    }

}