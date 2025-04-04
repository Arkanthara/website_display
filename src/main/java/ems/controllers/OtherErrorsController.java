package ems.controllers;

import ems.models.OtherErrors;
import ems.services.OtherErrorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/othererrors")
public class OtherErrorsController {

    @Autowired
    private OtherErrorsService othererrorsService;

    @GetMapping
    public List<OtherErrors> getAllOtherErrorss() {
        return othererrorsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OtherErrors> getOtherErrorsById(@PathVariable Long id) {
        OtherErrors othererrors = othererrorsService.findById(id);
        if (othererrors == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(othererrors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OtherErrors> createOtherErrors(@RequestBody OtherErrors othererrors) {
        OtherErrors created = othererrorsService.save(othererrors);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OtherErrors> updateOtherErrors(@PathVariable Long id, @RequestBody OtherErrors othererrors) {
        OtherErrors updated = othererrorsService.save(othererrors);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOtherErrors(@PathVariable Long id) {
        othererrorsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<OtherErrors> findBySerialnumber(@RequestParam String serialNumber) {
        return othererrorsService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<OtherErrors> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return othererrorsService.findByTimeBetween(start, end);
    }

    @GetMapping("/findByToptouchreset")
    public List<OtherErrors> findByToptouchreset(@RequestParam Integer topTouchReset) {
        return othererrorsService.findByToptouchreset(topTouchReset);
    }

    @GetMapping("/findBySoundplayerr")
    public List<OtherErrors> findBySoundplayerr(@RequestParam Integer soundPlayErr) {
        return othererrorsService.findBySoundplayerr(soundPlayErr);
    }

}