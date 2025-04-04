package ems.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Metadata", uniqueConstraints = @UniqueConstraint(name = "uniqueMetadata", columnNames = { "time", "serialNumber" }))
public class Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Serial Number")
    private String serialNumber;
    @Column(name = "Time")
    private LocalDateTime time;
    @Column(name = "Data")
    private String data;
    @Column(name = "Pairing Status")
    private Boolean pairingStatus;
    @Column(name = "Stats Mapping Version")
    private String statsMappingVersion;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getPairingstatus() {
        return pairingStatus;
    }

    public void setPairingstatus(Boolean pairingStatus) {
        this.pairingStatus = pairingStatus;
    }

    public String getStatsmappingversion() {
        return statsMappingVersion;
    }

    public void setStatsmappingversion(String statsMappingVersion) {
        this.statsMappingVersion = statsMappingVersion;
    }

}