package ems.controllers;

import ems.models.Phony;
import ems.services.PhonyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/phony")
public class PhonyController {

    @Autowired
    private PhonyService phonyService;

    @GetMapping
    public List<Phony> getAll() {
        return phonyService.findAll();
    }

    @PostMapping
    public ResponseEntity<Phony> create(@RequestBody Phony obj) {
        return new ResponseEntity<>(phonyService.save(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Phony> update(@PathVariable Long id, @RequestBody Phony obj) {
        return new ResponseEntity<>(phonyService.save(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        phonyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialNumber")
    public List<Phony> findBySerialNumber(@RequestParam String serialNumber) {
        return phonyService.findBySerialNumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<Phony> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return phonyService.findByTimeBetween(start, end);
    }

    @GetMapping("/findByTime")
    public List<Phony> findByTime(@RequestParam LocalDateTime time) {
        return phonyService.findByTime(time);
    }

    @GetMapping("/findByColumn1")
    public List<Phony> findByColumn1(@RequestParam Integer column1) {
        return phonyService.findByColumn1(column1);
    }

    @GetMapping("/findByColumn2")
    public List<Phony> findByColumn2(@RequestParam String column2) {
        return phonyService.findByColumn2(column2);
    }

    @GetMapping("/findByColumn3")
    public List<Phony> findByColumn3(@RequestParam String column3) {
        return phonyService.findByColumn3(column3);
    }
}