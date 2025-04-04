package ems.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ModemErrors", uniqueConstraints = @UniqueConstraint(name = "uniqueModemErrors", columnNames = { "time", "serialNumber" }))
public class ModemErrors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Serial Number")
    private String serialNumber;
    @Column(name = "Time")
    private LocalDateTime time;
    @Column(name = "Timeout")
    private Integer timeout;
    @Column(name = "Header")
    private Integer header;
    @Column(name = "Footer")
    private Integer footer;
    @Column(name = "CRC")
    private Integer crc;
    @Column(name = "CTX")
    private Integer ctx;

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

    public Integer getHeader() {
        return header;
    }

    public void setHeader(Integer header) {
        this.header = header;
    }

    public Integer getFooter() {
        return footer;
    }

    public void setFooter(Integer footer) {
        this.footer = footer;
    }

    public Integer getCrc() {
        return crc;
    }

    public void setCrc(Integer crc) {
        this.crc = crc;
    }

    public Integer getCtx() {
        return ctx;
    }

    public void setCtx(Integer ctx) {
        this.ctx = ctx;
    }

}