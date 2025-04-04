package ems.controllers;

import ems.models.Version;
import ems.services.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/version")
public class VersionController {

    @Autowired
    private VersionService versionService;

    @GetMapping
    public List<Version> getAllVersions() {
        return versionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Version> getVersionById(@PathVariable Long id) {
        Version version = versionService.findById(id);
        if (version == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(version, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Version> createVersion(@RequestBody Version version) {
        Version created = versionService.save(version);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Version> updateVersion(@PathVariable Long id, @RequestBody Version version) {
        Version updated = versionService.save(version);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVersion(@PathVariable Long id) {
        versionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<Version> findBySerialnumber(@RequestParam String serialNumber) {
        return versionService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<Version> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return versionService.findByTimeBetween(start, end);
    }

    @GetMapping("/findByVersionmain")
    public List<Version> findByVersionmain(@RequestParam String versionMain) {
        return versionService.findByVersionmain(versionMain);
    }

    @GetMapping("/findByVersionus")
    public List<Version> findByVersionus(@RequestParam String versionUs) {
        return versionService.findByVersionus(versionUs);
    }

    @GetMapping("/findByVersionaf")
    public List<Version> findByVersionaf(@RequestParam String versionAf) {
        return versionService.findByVersionaf(versionAf);
    }

    @GetMapping("/findByVersionpz")
    public List<Version> findByVersionpz(@RequestParam String versionPz) {
        return versionService.findByVersionpz(versionPz);
    }

    @GetMapping("/findByVersionesp32")
    public List<Version> findByVersionesp32(@RequestParam String versionEsp32) {
        return versionService.findByVersionesp32(versionEsp32);
    }

}