package ems.controllers;

import ems.models.Power;
import ems.services.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/power")
public class PowerController {

    @Autowired
    private PowerService powerService;

    @GetMapping
    public List<Power> getAllPowers() {
        return powerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Power> getPowerById(@PathVariable Long id) {
        Power power = powerService.findById(id);
        if (power == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(power, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Power> createPower(@RequestBody Power power) {
        Power created = powerService.save(power);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Power> updatePower(@PathVariable Long id, @RequestBody Power power) {
        Power updated = powerService.save(power);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePower(@PathVariable Long id) {
        powerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<Power> findBySerialnumber(@RequestParam String serialNumber) {
        return powerService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<Power> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return powerService.findByTimeBetween(start, end);
    }

    @GetMapping("/findByTimepowerons")
    public List<Power> findByTimepowerons(@RequestParam Integer timePowerOnS) {
        return powerService.findByTimepowerons(timePowerOnS);
    }

    @GetMapping("/findByTimeons")
    public List<Power> findByTimeons(@RequestParam Integer timeOnS) {
        return powerService.findByTimeons(timeOnS);
    }

    @GetMapping("/findByPoweroncounter")
    public List<Power> findByPoweroncounter(@RequestParam Integer powerOnCounter) {
        return powerService.findByPoweroncounter(powerOnCounter);
    }

}