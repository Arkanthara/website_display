package ems.controllers;

import ems.models.PzErrors;
import ems.services.PzErrorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pzerrors")
public class PzErrorsController {

    @Autowired
    private PzErrorsService pzerrorsService;

    @GetMapping
    public List<PzErrors> getAllPzErrorss() {
        return pzerrorsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PzErrors> getPzErrorsById(@PathVariable Long id) {
        PzErrors pzerrors = pzerrorsService.findById(id);
        if (pzerrors == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pzerrors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PzErrors> createPzErrors(@RequestBody PzErrors pzerrors) {
        PzErrors created = pzerrorsService.save(pzerrors);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PzErrors> updatePzErrors(@PathVariable Long id, @RequestBody PzErrors pzerrors) {
        PzErrors updated = pzerrorsService.save(pzerrors);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePzErrors(@PathVariable Long id) {
        pzerrorsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<PzErrors> findBySerialnumber(@RequestParam String serialNumber) {
        return pzerrorsService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<PzErrors> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return pzerrorsService.findByTimeBetween(start, end);
    }

    @GetMapping("/findByTimeout")
    public List<PzErrors> findByTimeout(@RequestParam Integer timeout) {
        return pzerrorsService.findByTimeout(timeout);
    }

    @GetMapping("/findByNak")
    public List<PzErrors> findByNak(@RequestParam Integer nak) {
        return pzerrorsService.findByNak(nak);
    }

    @GetMapping("/findByFrameinv")
    public List<PzErrors> findByFrameinv(@RequestParam Integer frameinv) {
        return pzerrorsService.findByFrameinv(frameinv);
    }

    @GetMapping("/findByDatainv")
    public List<PzErrors> findByDatainv(@RequestParam Integer dataInv) {
        return pzerrorsService.findByDatainv(dataInv);
    }

}