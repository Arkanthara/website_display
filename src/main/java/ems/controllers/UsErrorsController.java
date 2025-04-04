package ems.controllers;

import ems.models.UsErrors;
import ems.services.UsErrorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/userrors")
public class UsErrorsController {

    @Autowired
    private UsErrorsService userrorsService;

    @GetMapping
    public List<UsErrors> getAllUsErrorss() {
        return userrorsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsErrors> getUsErrorsById(@PathVariable Long id) {
        UsErrors userrors = userrorsService.findById(id);
        if (userrors == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userrors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsErrors> createUsErrors(@RequestBody UsErrors userrors) {
        UsErrors created = userrorsService.save(userrors);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsErrors> updateUsErrors(@PathVariable Long id, @RequestBody UsErrors userrors) {
        UsErrors updated = userrorsService.save(userrors);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsErrors(@PathVariable Long id) {
        userrorsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<UsErrors> findBySerialnumber(@RequestParam String serialNumber) {
        return userrorsService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<UsErrors> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return userrorsService.findByTimeBetween(start, end);
    }

    @GetMapping("/findByTimeout")
    public List<UsErrors> findByTimeout(@RequestParam Integer timeout) {
        return userrorsService.findByTimeout(timeout);
    }

    @GetMapping("/findByNak")
    public List<UsErrors> findByNak(@RequestParam Integer nak) {
        return userrorsService.findByNak(nak);
    }

    @GetMapping("/findByFrameinv")
    public List<UsErrors> findByFrameinv(@RequestParam Integer frameinv) {
        return userrorsService.findByFrameinv(frameinv);
    }

    @GetMapping("/findByDatainv")
    public List<UsErrors> findByDatainv(@RequestParam Integer dataInv) {
        return userrorsService.findByDatainv(dataInv);
    }

}