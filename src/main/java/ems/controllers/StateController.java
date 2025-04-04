package ems.controllers;

import ems.models.State;
import ems.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> getAllStates() {
        return stateService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> getStateById(@PathVariable Long id) {
        State state = stateService.findById(id);
        if (state == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(state, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<State> createState(@RequestBody State state) {
        State created = stateService.save(state);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<State> updateState(@PathVariable Long id, @RequestBody State state) {
        State updated = stateService.save(state);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteState(@PathVariable Long id) {
        stateService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<State> findBySerialnumber(@RequestParam String serialNumber) {
        return stateService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<State> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return stateService.findByTimeBetween(start, end);
    }

    @GetMapping("/findByInit")
    public List<State> findByInit(@RequestParam Integer init) {
        return stateService.findByInit(init);
    }

    @GetMapping("/findByStandby")
    public List<State> findByStandby(@RequestParam Integer standby) {
        return stateService.findByStandby(standby);
    }

    @GetMapping("/findByFault")
    public List<State> findByFault(@RequestParam Integer fault) {
        return stateService.findByFault(fault);
    }

    @GetMapping("/findByWarning")
    public List<State> findByWarning(@RequestParam Integer warning) {
        return stateService.findByWarning(warning);
    }

    @GetMapping("/findBySwupdate")
    public List<State> findBySwupdate(@RequestParam Integer swUpdate) {
        return stateService.findBySwupdate(swUpdate);
    }

    @GetMapping("/findByWifipairing")
    public List<State> findByWifipairing(@RequestParam Integer wifiPairing) {
        return stateService.findByWifipairing(wifiPairing);
    }

    @GetMapping("/findByBtpairing")
    public List<State> findByBtpairing(@RequestParam Integer btPairing) {
        return stateService.findByBtpairing(btPairing);
    }

    @GetMapping("/findByRest")
    public List<State> findByRest(@RequestParam Integer rest) {
        return stateService.findByRest(rest);
    }

    @GetMapping("/findByCleaning")
    public List<State> findByCleaning(@RequestParam Integer cleaning) {
        return stateService.findByCleaning(cleaning);
    }

    @GetMapping("/findByAf")
    public List<State> findByAf(@RequestParam Integer af) {
        return stateService.findByAf(af);
    }

    @GetMapping("/findByPz")
    public List<State> findByPz(@RequestParam Integer pz) {
        return stateService.findByPz(pz);
    }

    @GetMapping("/findByService")
    public List<State> findByService(@RequestParam Integer service) {
        return stateService.findByService(service);
    }

}