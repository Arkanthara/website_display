package ems.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "State", uniqueConstraints = @UniqueConstraint(name = "uniqueState", columnNames = { "time", "serialNumber" }))
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Serial Number")
    private String serialNumber;
    @Column(name = "Time")
    private LocalDateTime time;
    @Column(name = "Init")
    private Integer init;
    @Column(name = "Standby")
    private Integer standby;
    @Column(name = "Fault")
    private Integer fault;
    @Column(name = "Warning")
    private Integer warning;
    @Column(name = "Software Update")
    private Integer swUpdate;
    @Column(name = "Wifi Pairing")
    private Integer wifiPairing;
    @Column(name = "Bluetooth Pairing")
    private Integer btPairing;
    @Column(name = "Rest")
    private Integer rest;
    @Column(name = "Cleaning")
    private Integer cleaning;
    @Column(name = "Airflow")
    private Integer af;
    @Column(name = "Piezon")
    private Integer pz;
    @Column(name = "Service")
    private Integer service;

    // Getters and Setters
    public String getSerialnumber() {
        return serialNumber;
    }

    public void setSerialnumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getInit() {
        return init;
    }

    public void setInit(Integer init) {
        this.init = init;
    }

    public Integer getStandby() {
        return standby;
    }

    public void setStandby(Integer standby) {
        this.standby = standby;
    }

    public Integer getFault() {
        return fault;
    }

    public void setFault(Integer fault) {
        this.fault = fault;
    }

    public Integer getWarning() {
        return warning;
    }

    public void setWarning(Integer warning) {
        this.warning = warning;
    }

    public Integer getSwupdate() {
        return swUpdate;
    }

    public void setSwupdate(Integer swUpdate) {
        this.swUpdate = swUpdate;
    }

    public Integer getWifipairing() {
        return wifiPairing;
    }

    public void setWifipairing(Integer wifiPairing) {
        this.wifiPairing = wifiPairing;
    }

    public Integer getBtpairing() {
        return btPairing;
    }

    public void setBtpairing(Integer btPairing) {
        this.btPairing = btPairing;
    }

    public Integer getRest() {
        return rest;
    }

    public void setRest(Integer rest) {
        this.rest = rest;
    }

    public Integer getCleaning() {
        return cleaning;
    }

    public void setCleaning(Integer cleaning) {
        this.cleaning = cleaning;
    }

    public Integer getAf() {
        return af;
    }

    public void setAf(Integer af) {
        this.af = af;
    }

    public Integer getPz() {
        return pz;
    }

    public void setPz(Integer pz) {
        this.pz = pz;
    }

    public Integer getService() {
        return service;
    }

    public void setService(Integer service) {
        this.service = service;
    }

}