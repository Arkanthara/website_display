package ems.controllers;

import ems.models.AfErrors;
import ems.services.AfErrorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/aferrors")
public class AfErrorsController {

    @Autowired
    private AfErrorsService aferrorsService;

    @GetMapping
    public List<AfErrors> getAllAfErrorss() {
        return aferrorsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AfErrors> getAfErrorsById(@PathVariable Long id) {
        AfErrors aferrors = aferrorsService.findById(id);
        if (aferrors == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aferrors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AfErrors> createAfErrors(@RequestBody AfErrors aferrors) {
        AfErrors created = aferrorsService.save(aferrors);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AfErrors> updateAfErrors(@PathVariable Long id, @RequestBody AfErrors aferrors) {
        AfErrors updated = aferrorsService.save(aferrors);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAfErrors(@PathVariable Long id) {
        aferrorsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<AfErrors> findBySerialnumber(@RequestParam String serialNumber) {
        return aferrorsService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<AfErrors> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return aferrorsService.findByTimeBetween(start, end);
    }

    @GetMapping("/findByTimeout")
    public List<AfErrors> findByTimeout(@RequestParam Integer timeout) {
        return aferrorsService.findByTimeout(timeout);
    }

    @GetMapping("/findByNak")
    public List<AfErrors> findByNak(@RequestParam Integer nak) {
        return aferrorsService.findByNak(nak);
    }

    @GetMapping("/findByFrameinv")
    public List<AfErrors> findByFrameinv(@RequestParam Integer frameinv) {
        return aferrorsService.findByFrameinv(frameinv);
    }

    @GetMapping("/findByDatainv")
    public List<AfErrors> findByDatainv(@RequestParam Integer dataInv) {
        return aferrorsService.findByDatainv(dataInv);
    }

}