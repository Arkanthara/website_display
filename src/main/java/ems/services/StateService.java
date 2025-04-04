package ems.services;

import ems.models.State;
import ems.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    public List<State> findAll() {
        return stateRepository.findAll();
    }

    public State findById(Long id) {
        return stateRepository.findById(id).orElse(null);
    }

    public State save(State state) {
        return stateRepository.save(state);
    }

    public void deleteById(Long id) {
        stateRepository.deleteById(id);
    }

    public List<State> findBySerialnumber(String serialNumber) {
        return stateRepository.findBySerialnumber(serialNumber);
    }

    public List<State> findByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return stateRepository.findByTimeBetween(start, end);
    }

    public List<State> findByInit(Integer init) {
        return stateRepository.findByInit(init);
    }

    public List<State> findByStandby(Integer standby) {
        return stateRepository.findByStandby(standby);
    }

    public List<State> findByFault(Integer fault) {
        return stateRepository.findByFault(fault);
    }

    public List<State> findByWarning(Integer warning) {
        return stateRepository.findByWarning(warning);
    }

    public List<State> findBySwupdate(Integer swUpdate) {
        return stateRepository.findBySwupdate(swUpdate);
    }

    public List<State> findByWifipairing(Integer wifiPairing) {
        return stateRepository.findByWifipairing(wifiPairing);
    }

    public List<State> findByBtpairing(Integer btPairing) {
        return stateRepository.findByBtpairing(btPairing);
    }

    public List<State> findByRest(Integer rest) {
        return stateRepository.findByRest(rest);
    }

    public List<State> findByCleaning(Integer cleaning) {
        return stateRepository.findByCleaning(cleaning);
    }

    public List<State> findByAf(Integer af) {
        return stateRepository.findByAf(af);
    }

    public List<State> findByPz(Integer pz) {
        return stateRepository.findByPz(pz);
    }

    public List<State> findByService(Integer service) {
        return stateRepository.findByService(service);
    }

}