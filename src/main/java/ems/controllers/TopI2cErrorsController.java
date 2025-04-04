package ems.controllers;

import ems.models.TopI2cErrors;
import ems.services.TopI2cErrorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/topi2cerrors")
public class TopI2cErrorsController {

    @Autowired
    private TopI2cErrorsService topi2cerrorsService;

    @GetMapping
    public List<TopI2cErrors> getAllTopI2cErrorss() {
        return topi2cerrorsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopI2cErrors> getTopI2cErrorsById(@PathVariable Long id) {
        TopI2cErrors topi2cerrors = topi2cerrorsService.findById(id);
        if (topi2cerrors == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(topi2cerrors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TopI2cErrors> createTopI2cErrors(@RequestBody TopI2cErrors topi2cerrors) {
        TopI2cErrors created = topi2cerrorsService.save(topi2cerrors);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopI2cErrors> updateTopI2cErrors(@PathVariable Long id, @RequestBody TopI2cErrors topi2cerrors) {
        TopI2cErrors updated = topi2cerrorsService.save(topi2cerrors);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopI2cErrors(@PathVariable Long id) {
        topi2cerrorsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<TopI2cErrors> findBySerialnumber(@RequestParam String serialNumber) {
        return topi2cerrorsService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<TopI2cErrors> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return topi2cerrorsService.findByTimeBetween(start, end);
    }

    @GetMapping("/findByTimeout")
    public List<TopI2cErrors> findByTimeout(@RequestParam Integer timeout) {
        return topi2cerrorsService.findByTimeout(timeout);
    }

    @GetMapping("/findByAddrnak")
    public List<TopI2cErrors> findByAddrnak(@RequestParam Integer addrnak) {
        return topi2cerrorsService.findByAddrnak(addrnak);
    }

    @GetMapping("/findByOthers")
    public List<TopI2cErrors> findByOthers(@RequestParam Integer others) {
        return topi2cerrorsService.findByOthers(others);
    }

    @GetMapping("/findByReadko")
    public List<TopI2cErrors> findByReadko(@RequestParam Integer readko) {
        return topi2cerrorsService.findByReadko(readko);
    }

    @GetMapping("/findByWriteko")
    public List<TopI2cErrors> findByWriteko(@RequestParam Integer writeko) {
        return topi2cerrorsService.findByWriteko(writeko);
    }

    @GetMapping("/findByFwriteko")
    public List<TopI2cErrors> findByFwriteko(@RequestParam Integer fwriteko) {
        return topi2cerrorsService.findByFwriteko(fwriteko);
    }

}