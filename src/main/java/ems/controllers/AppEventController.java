package ems.controllers;

import ems.models.AppEvent;
import ems.services.AppEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appevent")
public class AppEventController {

    @Autowired
    private AppEventService appeventService;

    @GetMapping
    public List<AppEvent> getAllAppEvents() {
        return appeventService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppEvent> getAppEventById(@PathVariable Long id) {
        AppEvent appevent = appeventService.findById(id);
        if (appevent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(appevent, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AppEvent> createAppEvent(@RequestBody AppEvent appevent) {
        AppEvent created = appeventService.save(appevent);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppEvent> updateAppEvent(@PathVariable Long id, @RequestBody AppEvent appevent) {
        AppEvent updated = appeventService.save(appevent);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppEvent(@PathVariable Long id) {
        appeventService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<AppEvent> findBySerialnumber(@RequestParam String serialNumber) {
        return appeventService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<AppEvent> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return appeventService.findByTimeBetween(start, end);
    }

    @GetMapping("/findByPressurization")
    public List<AppEvent> findByPressurization(@RequestParam Integer pressurization) {
        return appeventService.findByPressurization(pressurization);
    }

    @GetMapping("/findByPairingstart")
    public List<AppEvent> findByPairingstart(@RequestParam Integer pairingStart) {
        return appeventService.findByPairingstart(pairingStart);
    }

    @GetMapping("/findByPairingcomplete")
    public List<AppEvent> findByPairingcomplete(@RequestParam Integer pairingComplete) {
        return appeventService.findByPairingcomplete(pairingComplete);
    }

    @GetMapping("/findByCleaningstart")
    public List<AppEvent> findByCleaningstart(@RequestParam Integer cleaningStart) {
        return appeventService.findByCleaningstart(cleaningStart);
    }

    @GetMapping("/findByCleaningpause")
    public List<AppEvent> findByCleaningpause(@RequestParam Integer cleaningPause) {
        return appeventService.findByCleaningpause(cleaningPause);
    }

    @GetMapping("/findByCleaningfinalized")
    public List<AppEvent> findByCleaningfinalized(@RequestParam Integer cleaningFinalized) {
        return appeventService.findByCleaningfinalized(cleaningFinalized);
    }

    @GetMapping("/findByPedalpzp1")
    public List<AppEvent> findByPedalpzp1(@RequestParam Integer pedalPzP1) {
        return appeventService.findByPedalpzp1(pedalPzP1);
    }

    @GetMapping("/findByBoostpzp1")
    public List<AppEvent> findByBoostpzp1(@RequestParam Integer boostPzP1) {
        return appeventService.findByBoostpzp1(boostPzP1);
    }

    @GetMapping("/findByPedalpzp2")
    public List<AppEvent> findByPedalpzp2(@RequestParam Integer pedalPzP2) {
        return appeventService.findByPedalpzp2(pedalPzP2);
    }

    @GetMapping("/findByBoostpzp2")
    public List<AppEvent> findByBoostpzp2(@RequestParam Integer boostPzP2) {
        return appeventService.findByBoostpzp2(boostPzP2);
    }

    @GetMapping("/findByPedalpzp3")
    public List<AppEvent> findByPedalpzp3(@RequestParam Integer pedalPzP3) {
        return appeventService.findByPedalpzp3(pedalPzP3);
    }

    @GetMapping("/findByBoostpzp3")
    public List<AppEvent> findByBoostpzp3(@RequestParam Integer boostPzP3) {
        return appeventService.findByBoostpzp3(boostPzP3);
    }

    @GetMapping("/findByPedalafp1")
    public List<AppEvent> findByPedalafp1(@RequestParam Integer pedalAfP1) {
        return appeventService.findByPedalafp1(pedalAfP1);
    }

    @GetMapping("/findByBoostafp1")
    public List<AppEvent> findByBoostafp1(@RequestParam Integer boostAfP1) {
        return appeventService.findByBoostafp1(boostAfP1);
    }

    @GetMapping("/findByPedalafp2")
    public List<AppEvent> findByPedalafp2(@RequestParam Integer pedalAfP2) {
        return appeventService.findByPedalafp2(pedalAfP2);
    }

    @GetMapping("/findByBoostafp2")
    public List<AppEvent> findByBoostafp2(@RequestParam Integer boostAfP2) {
        return appeventService.findByBoostafp2(boostAfP2);
    }

    @GetMapping("/findByPedalafp3")
    public List<AppEvent> findByPedalafp3(@RequestParam Integer pedalAfP3) {
        return appeventService.findByPedalafp3(pedalAfP3);
    }

    @GetMapping("/findByBoostafp3")
    public List<AppEvent> findByBoostafp3(@RequestParam Integer boostAfP3) {
        return appeventService.findByBoostafp3(boostAfP3);
    }

    @GetMapping("/findByPedalafwop1")
    public List<AppEvent> findByPedalafwop1(@RequestParam Integer pedalAfwoP1) {
        return appeventService.findByPedalafwop1(pedalAfwoP1);
    }

    @GetMapping("/findByPedalafwop2")
    public List<AppEvent> findByPedalafwop2(@RequestParam Integer pedalAfwoP2) {
        return appeventService.findByPedalafwop2(pedalAfwoP2);
    }

    @GetMapping("/findByPedalafwop3")
    public List<AppEvent> findByPedalafwop3(@RequestParam Integer pedalAfwoP3) {
        return appeventService.findByPedalafwop3(pedalAfwoP3);
    }

    @GetMapping("/findByOnstart")
    public List<AppEvent> findByOnstart(@RequestParam Integer onStart) {
        return appeventService.findByOnstart(onStart);
    }

    @GetMapping("/findByOnafter7h")
    public List<AppEvent> findByOnafter7h(@RequestParam Integer onAfter7H) {
        return appeventService.findByOnafter7h(onAfter7H);
    }

}