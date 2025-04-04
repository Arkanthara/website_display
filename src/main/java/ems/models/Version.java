package ems.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Version", uniqueConstraints = @UniqueConstraint(name = "uniqueVersion", columnNames = { "time", "serialNumber" }))
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Serial Number")
    private String serialNumber;
    @Column(name = "Time")
    private LocalDateTime time;
    @Column(name = "version MAIN")
    private String versionMain;
    @Column(name = "version US")
    private String versionUs;
    @Column(name = "version AF")
    private String versionAf;
    @Column(name = "version PZ")
    private String versionPz;
    @Column(name = "version ESP32")
    private String versionEsp32;

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

    public String getVersionmain() {
        return versionMain;
    }

    public void setVersionmain(String versionMain) {
        this.versionMain = versionMain;
    }

    public String getVersionus() {
        return versionUs;
    }

    public void setVersionus(String versionUs) {
        this.versionUs = versionUs;
    }

    public String getVersionaf() {
        return versionAf;
    }

    public void setVersionaf(String versionAf) {
        this.versionAf = versionAf;
    }

    public String getVersionpz() {
        return versionPz;
    }

    public void setVersionpz(String versionPz) {
        this.versionPz = versionPz;
    }

    public String getVersionesp32() {
        return versionEsp32;
    }

    public void setVersionesp32(String versionEsp32) {
        this.versionEsp32 = versionEsp32;
    }

}