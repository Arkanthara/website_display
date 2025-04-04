package ems.controllers;

import ems.models.PneumaticErrors;
import ems.services.PneumaticErrorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pneumaticerrors")
public class PneumaticErrorsController {

    @Autowired
    private PneumaticErrorsService pneumaticerrorsService;

    @GetMapping
    public List<PneumaticErrors> getAllPneumaticErrorss() {
        return pneumaticerrorsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PneumaticErrors> getPneumaticErrorsById(@PathVariable Long id) {
        PneumaticErrors pneumaticerrors = pneumaticerrorsService.findById(id);
        if (pneumaticerrors == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pneumaticerrors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PneumaticErrors> createPneumaticErrors(@RequestBody PneumaticErrors pneumaticerrors) {
        PneumaticErrors created = pneumaticerrorsService.save(pneumaticerrors);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PneumaticErrors> updatePneumaticErrors(@PathVariable Long id, @RequestBody PneumaticErrors pneumaticerrors) {
        PneumaticErrors updated = pneumaticerrorsService.save(pneumaticerrors);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePneumaticErrors(@PathVariable Long id) {
        pneumaticerrorsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<PneumaticErrors> findBySerialnumber(@RequestParam String serialNumber) {
        return pneumaticerrorsService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<PneumaticErrors> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return pneumaticerrorsService.findByTimeBetween(start, end);
    }

    @GetMapping("/findBySensorpress")
    public List<PneumaticErrors> findBySensorpress(@RequestParam Integer sensorPress) {
        return pneumaticerrorsService.findBySensorpress(sensorPress);
    }

    @GetMapping("/findByOverpress")
    public List<PneumaticErrors> findByOverpress(@RequestParam Integer overPress) {
        return pneumaticerrorsService.findByOverpress(overPress);
    }

    @GetMapping("/findByAfsat")
    public List<PneumaticErrors> findByAfsat(@RequestParam Integer afsat) {
        return pneumaticerrorsService.findByAfsat(afsat);
    }

    @GetMapping("/findByClogged")
    public List<PneumaticErrors> findByClogged(@RequestParam Integer clogged) {
        return pneumaticerrorsService.findByClogged(clogged);
    }

    @GetMapping("/findByNochamber")
    public List<PneumaticErrors> findByNochamber(@RequestParam Integer noChamber) {
        return pneumaticerrorsService.findByNochamber(noChamber);
    }

    @GetMapping("/findByNoair")
    public List<PneumaticErrors> findByNoair(@RequestParam Integer noAir) {
        return pneumaticerrorsService.findByNoair(noAir);
    }

    @GetMapping("/findByTimeoutup")
    public List<PneumaticErrors> findByTimeoutup(@RequestParam Integer timeoutUp) {
        return pneumaticerrorsService.findByTimeoutup(timeoutUp);
    }

}