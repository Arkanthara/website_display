package ems.controllers;

import ems.models.MI2cErrors;
import ems.services.MI2cErrorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/mi2cerrors")
public class MI2cErrorsController {

    @Autowired
    private MI2cErrorsService mi2cerrorsService;

    @GetMapping
    public List<MI2cErrors> getAllMI2cErrorss() {
        return mi2cerrorsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MI2cErrors> getMI2cErrorsById(@PathVariable Long id) {
        MI2cErrors mi2cerrors = mi2cerrorsService.findById(id);
        if (mi2cerrors == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mi2cerrors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MI2cErrors> createMI2cErrors(@RequestBody MI2cErrors mi2cerrors) {
        MI2cErrors created = mi2cerrorsService.save(mi2cerrors);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MI2cErrors> updateMI2cErrors(@PathVariable Long id, @RequestBody MI2cErrors mi2cerrors) {
        MI2cErrors updated = mi2cerrorsService.save(mi2cerrors);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMI2cErrors(@PathVariable Long id) {
        mi2cerrorsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<MI2cErrors> findBySerialnumber(@RequestParam String serialNumber) {
        return mi2cerrorsService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<MI2cErrors> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return mi2cerrorsService.findByTimeBetween(start, end);
    }

    @GetMapping("/findByTimeout")
    public List<MI2cErrors> findByTimeout(@RequestParam Integer timeout) {
        return mi2cerrorsService.findByTimeout(timeout);
    }

    @GetMapping("/findByAddrnak")
    public List<MI2cErrors> findByAddrnak(@RequestParam Integer addrnak) {
        return mi2cerrorsService.findByAddrnak(addrnak);
    }

    @GetMapping("/findByOthers")
    public List<MI2cErrors> findByOthers(@RequestParam Integer others) {
        return mi2cerrorsService.findByOthers(others);
    }

    @GetMapping("/findByReadko")
    public List<MI2cErrors> findByReadko(@RequestParam Integer readko) {
        return mi2cerrorsService.findByReadko(readko);
    }

    @GetMapping("/findByWriteko")
    public List<MI2cErrors> findByWriteko(@RequestParam Integer writeko) {
        return mi2cerrorsService.findByWriteko(writeko);
    }

    @GetMapping("/findByFwriteko")
    public List<MI2cErrors> findByFwriteko(@RequestParam Integer fwriteko) {
        return mi2cerrorsService.findByFwriteko(fwriteko);
    }

}