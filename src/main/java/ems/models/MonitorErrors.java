package ems.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MonitorErrors", uniqueConstraints = @UniqueConstraint(name = "uniqueMonitorErrors", columnNames = { "time", "serialNumber" }))
public class MonitorErrors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Serial Number")
    private String serialNumber;
    @Column(name = "Time")
    private LocalDateTime time;
    @Column(name = "Sensor Pwr")
    private Integer sensorPwr;
    @Column(name = "No Pwr")
    private Integer noPwr;
    @Column(name = "Power Loss")
    private Integer powerLoss;
    @Column(name = "Valve Access Error")
    private Integer valveAccessError;
    @Column(name = "Valve Over Current")
    private Integer valveOverCurrent;
    @Column(name = "Valve Open Load")
    private Integer valveOpenLoad;
    @Column(name = "Us Module")
    private Integer usModule;

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

    public Integer getSensorpwr() {
        return sensorPwr;
    }

    public void setSensorpwr(Integer sensorPwr) {
        this.sensorPwr = sensorPwr;
    }

    public Integer getNopwr() {
        return noPwr;
    }

    public void setNopwr(Integer noPwr) {
        this.noPwr = noPwr;
    }

    public Integer getPowerloss() {
        return powerLoss;
    }

    public void setPowerloss(Integer powerLoss) {
        this.powerLoss = powerLoss;
    }

    public Integer getValveaccesserror() {
        return valveAccessError;
    }

    public void setValveaccesserror(Integer valveAccessError) {
        this.valveAccessError = valveAccessError;
    }

    public Integer getValveovercurrent() {
        return valveOverCurrent;
    }

    public void setValveovercurrent(Integer valveOverCurrent) {
        this.valveOverCurrent = valveOverCurrent;
    }

    public Integer getValveopenload() {
        return valveOpenLoad;
    }

    public void setValveopenload(Integer valveOpenLoad) {
        this.valveOpenLoad = valveOpenLoad;
    }

    public Integer getUsmodule() {
        return usModule;
    }

    public void setUsmodule(Integer usModule) {
        this.usModule = usModule;
    }

}