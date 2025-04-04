package ems.controllers;

import ems.models.MonitorErrors;
import ems.services.MonitorErrorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/monitorerrors")
public class MonitorErrorsController {

    @Autowired
    private MonitorErrorsService monitorerrorsService;

    @GetMapping
    public List<MonitorErrors> getAllMonitorErrorss() {
        return monitorerrorsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonitorErrors> getMonitorErrorsById(@PathVariable Long id) {
        MonitorErrors monitorerrors = monitorerrorsService.findById(id);
        if (monitorerrors == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(monitorerrors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MonitorErrors> createMonitorErrors(@RequestBody MonitorErrors monitorerrors) {
        MonitorErrors created = monitorerrorsService.save(monitorerrors);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonitorErrors> updateMonitorErrors(@PathVariable Long id, @RequestBody MonitorErrors monitorerrors) {
        MonitorErrors updated = monitorerrorsService.save(monitorerrors);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonitorErrors(@PathVariable Long id) {
        monitorerrorsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<MonitorErrors> findBySerialnumber(@RequestParam String serialNumber) {
        return monitorerrorsService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<MonitorErrors> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return monitorerrorsService.findByTimeBetween(start, end);
    }

    @GetMapping("/findBySensorpwr")
    public List<MonitorErrors> findBySensorpwr(@RequestParam Integer sensorPwr) {
        return monitorerrorsService.findBySensorpwr(sensorPwr);
    }

    @GetMapping("/findByNopwr")
    public List<MonitorErrors> findByNopwr(@RequestParam Integer noPwr) {
        return monitorerrorsService.findByNopwr(noPwr);
    }

    @GetMapping("/findByPowerloss")
    public List<MonitorErrors> findByPowerloss(@RequestParam Integer powerLoss) {
        return monitorerrorsService.findByPowerloss(powerLoss);
    }

    @GetMapping("/findByValveaccesserror")
    public List<MonitorErrors> findByValveaccesserror(@RequestParam Integer valveAccessError) {
        return monitorerrorsService.findByValveaccesserror(valveAccessError);
    }

    @GetMapping("/findByValveovercurrent")
    public List<MonitorErrors> findByValveovercurrent(@RequestParam Integer valveOverCurrent) {
        return monitorerrorsService.findByValveovercurrent(valveOverCurrent);
    }

    @GetMapping("/findByValveopenload")
    public List<MonitorErrors> findByValveopenload(@RequestParam Integer valveOpenLoad) {
        return monitorerrorsService.findByValveopenload(valveOpenLoad);
    }

    @GetMapping("/findByUsmodule")
    public List<MonitorErrors> findByUsmodule(@RequestParam Integer usModule) {
        return monitorerrorsService.findByUsmodule(usModule);
    }

}