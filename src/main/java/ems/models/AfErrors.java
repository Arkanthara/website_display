package ems.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AfErrors", uniqueConstraints = @UniqueConstraint(name = "uniqueAfErrors", columnNames = { "time", "serialNumber" }))
public class AfErrors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Serial Number")
    private String serialNumber;
    @Column(name = "Time")
    private LocalDateTime time;
    @Column(name = "Timeout")
    private Integer timeout;
    @Column(name = "NAK")
    private Integer nak;
    @Column(name = "frameInv")
    private Integer frameinv;
    @Column(name = "dataInv")
    private Integer dataInv;

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

    public Integer getNak() {
        return nak;
    }

    public void setNak(Integer nak) {
        this.nak = nak;
    }

    public Integer getFrameinv() {
        return frameinv;
    }

    public void setFrameinv(Integer frameinv) {
        this.frameinv = frameinv;
    }

    public Integer getDatainv() {
        return dataInv;
    }

    public void setDatainv(Integer dataInv) {
        this.dataInv = dataInv;
    }

}