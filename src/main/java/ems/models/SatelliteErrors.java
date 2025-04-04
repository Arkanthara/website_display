package ems.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SatelliteErrors", uniqueConstraints = @UniqueConstraint(name = "uniqueSatelliteErrors", columnNames = { "time", "serialNumber" }))
public class SatelliteErrors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Serial Number")
    private String serialNumber;
    @Column(name = "Time")
    private LocalDateTime time;
    @Column(name = "Reboot AF")
    private Integer rebootAf;
    @Column(name = "Reboot PZ")
    private Integer rebootPz;
    @Column(name = "Lost AF")
    private Integer lostAf;
    @Column(name = "Lost PZ")
    private Integer lostPz;
    @Column(name = "No AF")
    private Integer noAf;
    @Column(name = "No PZ")
    private Integer noPz;
    @Column(name = "No SAT")
    private Integer noSat;

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

    public Integer getRebootaf() {
        return rebootAf;
    }

    public void setRebootaf(Integer rebootAf) {
        this.rebootAf = rebootAf;
    }

    public Integer getRebootpz() {
        return rebootPz;
    }

    public void setRebootpz(Integer rebootPz) {
        this.rebootPz = rebootPz;
    }

    public Integer getLostaf() {
        return lostAf;
    }

    public void setLostaf(Integer lostAf) {
        this.lostAf = lostAf;
    }

    public Integer getLostpz() {
        return lostPz;
    }

    public void setLostpz(Integer lostPz) {
        this.lostPz = lostPz;
    }

    public Integer getNoaf() {
        return noAf;
    }

    public void setNoaf(Integer noAf) {
        this.noAf = noAf;
    }

    public Integer getNopz() {
        return noPz;
    }

    public void setNopz(Integer noPz) {
        this.noPz = noPz;
    }

    public Integer getNosat() {
        return noSat;
    }

    public void setNosat(Integer noSat) {
        this.noSat = noSat;
    }

}