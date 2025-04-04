package ems.controllers;

import ems.models.AppTime;
import ems.services.AppTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/apptime")
public class AppTimeController {

    @Autowired
    private AppTimeService apptimeService;

    @GetMapping
    public List<AppTime> getAllAppTimes() {
        return apptimeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppTime> getAppTimeById(@PathVariable Long id) {
        AppTime apptime = apptimeService.findById(id);
        if (apptime == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(apptime, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AppTime> createAppTime(@RequestBody AppTime apptime) {
        AppTime created = apptimeService.save(apptime);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppTime> updateAppTime(@PathVariable Long id, @RequestBody AppTime apptime) {
        AppTime updated = apptimeService.save(apptime);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppTime(@PathVariable Long id) {
        apptimeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<AppTime> findBySerialnumber(@RequestParam String serialNumber) {
        return apptimeService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<AppTime> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return apptimeService.findByTimeBetween(start, end);
    }

    @GetMapping("/findByPressurized")
    public List<AppTime> findByPressurized(@RequestParam Integer pressurized) {
        return apptimeService.findByPressurized(pressurized);
    }

    @GetMapping("/findByRunpzp1")
    public List<AppTime> findByRunpzp1(@RequestParam Integer runPzP1) {
        return apptimeService.findByRunpzp1(runPzP1);
    }

    @GetMapping("/findByBoostpzp1")
    public List<AppTime> findByBoostpzp1(@RequestParam Integer boostPzP1) {
        return apptimeService.findByBoostpzp1(boostPzP1);
    }

    @GetMapping("/findByRunpzp2")
    public List<AppTime> findByRunpzp2(@RequestParam Integer runPzP2) {
        return apptimeService.findByRunpzp2(runPzP2);
    }

    @GetMapping("/findByBoostpzp2")
    public List<AppTime> findByBoostpzp2(@RequestParam Integer boostPzP2) {
        return apptimeService.findByBoostpzp2(boostPzP2);
    }

    @GetMapping("/findByRunpzp3")
    public List<AppTime> findByRunpzp3(@RequestParam Integer runPzP3) {
        return apptimeService.findByRunpzp3(runPzP3);
    }

    @GetMapping("/findByBoostpzp3")
    public List<AppTime> findByBoostpzp3(@RequestParam Integer boostPzP3) {
        return apptimeService.findByBoostpzp3(boostPzP3);
    }

    @GetMapping("/findByRunafwateronlyp1")
    public List<AppTime> findByRunafwateronlyp1(@RequestParam Integer runAfWaterOnlyP1) {
        return apptimeService.findByRunafwateronlyp1(runAfWaterOnlyP1);
    }

    @GetMapping("/findByRunafwateronlyp2")
    public List<AppTime> findByRunafwateronlyp2(@RequestParam Integer runAfWaterOnlyP2) {
        return apptimeService.findByRunafwateronlyp2(runAfWaterOnlyP2);
    }

    @GetMapping("/findByRunafwateronlyp3")
    public List<AppTime> findByRunafwateronlyp3(@RequestParam Integer runAfWaterOnlyP3) {
        return apptimeService.findByRunafwateronlyp3(runAfWaterOnlyP3);
    }

    @GetMapping("/findByRunafp1")
    public List<AppTime> findByRunafp1(@RequestParam Integer runAfP1) {
        return apptimeService.findByRunafp1(runAfP1);
    }

    @GetMapping("/findByRunafp2")
    public List<AppTime> findByRunafp2(@RequestParam Integer runAfP2) {
        return apptimeService.findByRunafp2(runAfP2);
    }

    @GetMapping("/findByRunafp3")
    public List<AppTime> findByRunafp3(@RequestParam Integer runAfP3) {
        return apptimeService.findByRunafp3(runAfP3);
    }

    @GetMapping("/findByBoostafp1")
    public List<AppTime> findByBoostafp1(@RequestParam Integer boostAfP1) {
        return apptimeService.findByBoostafp1(boostAfP1);
    }

    @GetMapping("/findByBoostafp2")
    public List<AppTime> findByBoostafp2(@RequestParam Integer boostAfP2) {
        return apptimeService.findByBoostafp2(boostAfP2);
    }

    @GetMapping("/findByBoostafp3")
    public List<AppTime> findByBoostafp3(@RequestParam Integer boostAfP3) {
        return apptimeService.findByBoostafp3(boostAfP3);
    }

    @GetMapping("/findByCleanpause")
    public List<AppTime> findByCleanpause(@RequestParam Integer cleanPause) {
        return apptimeService.findByCleanpause(cleanPause);
    }

}