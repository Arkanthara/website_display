package ems.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PneumaticErrors", uniqueConstraints = @UniqueConstraint(name = "uniquePneumaticErrors", columnNames = { "time", "serialNumber" }))
public class PneumaticErrors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Serial Number")
    private String serialNumber;
    @Column(name = "Time")
    private LocalDateTime time;
    @Column(name = "Sensor Press")
    private Integer sensorPress;
    @Column(name = "Over Press")
    private Integer overPress;
    @Column(name = "Afsat")
    private Integer afsat;
    @Column(name = "Clogged")
    private Integer clogged;
    @Column(name = "No Chamber")
    private Integer noChamber;
    @Column(name = "No Air")
    private Integer noAir;
    @Column(name = "Timeout Up")
    private Integer timeoutUp;

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

    public Integer getSensorpress() {
        return sensorPress;
    }

    public void setSensorpress(Integer sensorPress) {
        this.sensorPress = sensorPress;
    }

    public Integer getOverpress() {
        return overPress;
    }

    public void setOverpress(Integer overPress) {
        this.overPress = overPress;
    }

    public Integer getAfsat() {
        return afsat;
    }

    public void setAfsat(Integer afsat) {
        this.afsat = afsat;
    }

    public Integer getClogged() {
        return clogged;
    }

    public void setClogged(Integer clogged) {
        this.clogged = clogged;
    }

    public Integer getNochamber() {
        return noChamber;
    }

    public void setNochamber(Integer noChamber) {
        this.noChamber = noChamber;
    }

    public Integer getNoair() {
        return noAir;
    }

    public void setNoair(Integer noAir) {
        this.noAir = noAir;
    }

    public Integer getTimeoutup() {
        return timeoutUp;
    }

    public void setTimeoutup(Integer timeoutUp) {
        this.timeoutUp = timeoutUp;
    }

}