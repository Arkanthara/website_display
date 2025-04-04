package ems.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "HeaterErrors", uniqueConstraints = @UniqueConstraint(name = "uniqueHeaterErrors", columnNames = { "time", "serialNumber" }))
public class HeaterErrors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Serial Number")
    private String serialNumber;
    @Column(name = "Time")
    private LocalDateTime time;
    @Column(name = "Hw Safety Temp")
    private Integer hwSafetyTemp;
    @Column(name = "Sensor Tc")
    private Integer sensorTc;
    @Column(name = "Burn")
    private Integer burn;
    @Column(name = "Tc Diff")
    private Integer tcDiff;
    @Column(name = "Delta Target")
    private Integer deltaTarget;
    @Column(name = "Hot")
    private Integer hot;
    @Column(name = "Cold")
    private Integer cold;
    @Column(name = "Full")
    private Integer full;

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

    public Integer getHwsafetytemp() {
        return hwSafetyTemp;
    }

    public void setHwsafetytemp(Integer hwSafetyTemp) {
        this.hwSafetyTemp = hwSafetyTemp;
    }

    public Integer getSensortc() {
        return sensorTc;
    }

    public void setSensortc(Integer sensorTc) {
        this.sensorTc = sensorTc;
    }

    public Integer getBurn() {
        return burn;
    }

    public void setBurn(Integer burn) {
        this.burn = burn;
    }

    public Integer getTcdiff() {
        return tcDiff;
    }

    public void setTcdiff(Integer tcDiff) {
        this.tcDiff = tcDiff;
    }

    public Integer getDeltatarget() {
        return deltaTarget;
    }

    public void setDeltatarget(Integer deltaTarget) {
        this.deltaTarget = deltaTarget;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getCold() {
        return cold;
    }

    public void setCold(Integer cold) {
        this.cold = cold;
    }

    public Integer getFull() {
        return full;
    }

    public void setFull(Integer full) {
        this.full = full;
    }

}