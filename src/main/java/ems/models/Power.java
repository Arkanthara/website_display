package ems.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Power", uniqueConstraints = @UniqueConstraint(name = "uniquePower", columnNames = { "time", "serialNumber" }))
public class Power {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Serial Number")
    private String serialNumber;
    @Column(name = "Time")
    private LocalDateTime time;
    @Column(name = "Time Power On (s)")
    private Integer timePowerOnS;
    @Column(name = "Time On (s)")
    private Integer timeOnS;
    @Column(name = "Power On Counter")
    private Integer powerOnCounter;

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

    public Integer getTimepowerons() {
        return timePowerOnS;
    }

    public void setTimepowerons(Integer timePowerOnS) {
        this.timePowerOnS = timePowerOnS;
    }

    public Integer getTimeons() {
        return timeOnS;
    }

    public void setTimeons(Integer timeOnS) {
        this.timeOnS = timeOnS;
    }

    public Integer getPoweroncounter() {
        return powerOnCounter;
    }

    public void setPoweroncounter(Integer powerOnCounter) {
        this.powerOnCounter = powerOnCounter;
    }

}