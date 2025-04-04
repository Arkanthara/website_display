package ems.controllers;

import ems.models.HeaterErrors;
import ems.services.HeaterErrorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/heatererrors")
public class HeaterErrorsController {

    @Autowired
    private HeaterErrorsService heatererrorsService;

    @GetMapping
    public List<HeaterErrors> getAllHeaterErrorss() {
        return heatererrorsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeaterErrors> getHeaterErrorsById(@PathVariable Long id) {
        HeaterErrors heatererrors = heatererrorsService.findById(id);
        if (heatererrors == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(heatererrors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HeaterErrors> createHeaterErrors(@RequestBody HeaterErrors heatererrors) {
        HeaterErrors created = heatererrorsService.save(heatererrors);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HeaterErrors> updateHeaterErrors(@PathVariable Long id, @RequestBody HeaterErrors heatererrors) {
        HeaterErrors updated = heatererrorsService.save(heatererrors);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHeaterErrors(@PathVariable Long id) {
        heatererrorsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<HeaterErrors> findBySerialnumber(@RequestParam String serialNumber) {
        return heatererrorsService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<HeaterErrors> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return heatererrorsService.findByTimeBetween(start, end);
    }

    @GetMapping("/findByHwsafetytemp")
    public List<HeaterErrors> findByHwsafetytemp(@RequestParam Integer hwSafetyTemp) {
        return heatererrorsService.findByHwsafetytemp(hwSafetyTemp);
    }

    @GetMapping("/findBySensortc")
    public List<HeaterErrors> findBySensortc(@RequestParam Integer sensorTc) {
        return heatererrorsService.findBySensortc(sensorTc);
    }

    @GetMapping("/findByBurn")
    public List<HeaterErrors> findByBurn(@RequestParam Integer burn) {
        return heatererrorsService.findByBurn(burn);
    }

    @GetMapping("/findByTcdiff")
    public List<HeaterErrors> findByTcdiff(@RequestParam Integer tcDiff) {
        return heatererrorsService.findByTcdiff(tcDiff);
    }

    @GetMapping("/findByDeltatarget")
    public List<HeaterErrors> findByDeltatarget(@RequestParam Integer deltaTarget) {
        return heatererrorsService.findByDeltatarget(deltaTarget);
    }

    @GetMapping("/findByHot")
    public List<HeaterErrors> findByHot(@RequestParam Integer hot) {
        return heatererrorsService.findByHot(hot);
    }

    @GetMapping("/findByCold")
    public List<HeaterErrors> findByCold(@RequestParam Integer cold) {
        return heatererrorsService.findByCold(cold);
    }

    @GetMapping("/findByFull")
    public List<HeaterErrors> findByFull(@RequestParam Integer full) {
        return heatererrorsService.findByFull(full);
    }

}