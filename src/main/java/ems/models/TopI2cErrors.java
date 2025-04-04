package ems.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TopI2cErrors", uniqueConstraints = @UniqueConstraint(name = "uniqueTopI2cErrors", columnNames = { "time", "serialNumber" }))
public class TopI2cErrors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Serial Number")
    private String serialNumber;
    @Column(name = "Time")
    private LocalDateTime time;
    @Column(name = "Timeout")
    private Integer timeout;
    @Column(name = "addrnak")
    private Integer addrnak;
    @Column(name = "others")
    private Integer others;
    @Column(name = "readKO")
    private Integer readko;
    @Column(name = "writeKO")
    private Integer writeko;
    @Column(name = "FWriteKO")
    private Integer fwriteko;

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

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getAddrnak() {
        return addrnak;
    }

    public void setAddrnak(Integer addrnak) {
        this.addrnak = addrnak;
    }

    public Integer getOthers() {
        return others;
    }

    public void setOthers(Integer others) {
        this.others = others;
    }

    public Integer getReadko() {
        return readko;
    }

    public void setReadko(Integer readko) {
        this.readko = readko;
    }

    public Integer getWriteko() {
        return writeko;
    }

    public void setWriteko(Integer writeko) {
        this.writeko = writeko;
    }

    public Integer getFwriteko() {
        return fwriteko;
    }

    public void setFwriteko(Integer fwriteko) {
        this.fwriteko = fwriteko;
    }

}