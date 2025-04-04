package ems.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "OtherErrors", uniqueConstraints = @UniqueConstraint(name = "uniqueOtherErrors", columnNames = { "time", "serialNumber" }))
public class OtherErrors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Serial Number")
    private String serialNumber;
    @Column(name = "Time")
    private LocalDateTime time;
    @Column(name = "Top Touch Reset")
    private Integer topTouchReset;
    @Column(name = "Sound Play Err")
    private Integer soundPlayErr;

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

    public Integer getToptouchreset() {
        return topTouchReset;
    }

    public void setToptouchreset(Integer topTouchReset) {
        this.topTouchReset = topTouchReset;
    }

    public Integer getSoundplayerr() {
        return soundPlayErr;
    }

    public void setSoundplayerr(Integer soundPlayErr) {
        this.soundPlayErr = soundPlayErr;
    }

}