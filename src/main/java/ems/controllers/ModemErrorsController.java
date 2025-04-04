package ems.controllers;

import ems.models.ModemErrors;
import ems.services.ModemErrorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/modemerrors")
public class ModemErrorsController {

    @Autowired
    private ModemErrorsService modemerrorsService;

    @GetMapping
    public List<ModemErrors> getAllModemErrorss() {
        return modemerrorsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModemErrors> getModemErrorsById(@PathVariable Long id) {
        ModemErrors modemerrors = modemerrorsService.findById(id);
        if (modemerrors == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modemerrors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ModemErrors> createModemErrors(@RequestBody ModemErrors modemerrors) {
        ModemErrors created = modemerrorsService.save(modemerrors);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModemErrors> updateModemErrors(@PathVariable Long id, @RequestBody ModemErrors modemerrors) {
        ModemErrors updated = modemerrorsService.save(modemerrors);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModemErrors(@PathVariable Long id) {
        modemerrorsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<ModemErrors> findBySerialnumber(@RequestParam String serialNumber) {
        return modemerrorsService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<ModemErrors> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return modemerrorsService.findByTimeBetween(start, end);
    }

    @GetMapping("/findByTimeout")
    public List<ModemErrors> findByTimeout(@RequestParam Integer timeout) {
        return modemerrorsService.findByTimeout(timeout);
    }

    @GetMapping("/findByHeader")
    public List<ModemErrors> findByHeader(@RequestParam Integer header) {
        return modemerrorsService.findByHeader(header);
    }

    @GetMapping("/findByFooter")
    public List<ModemErrors> findByFooter(@RequestParam Integer footer) {
        return modemerrorsService.findByFooter(footer);
    }

    @GetMapping("/findByCrc")
    public List<ModemErrors> findByCrc(@RequestParam Integer crc) {
        return modemerrorsService.findByCrc(crc);
    }

    @GetMapping("/findByCtx")
    public List<ModemErrors> findByCtx(@RequestParam Integer ctx) {
        return modemerrorsService.findByCtx(ctx);
    }

}