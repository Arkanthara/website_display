package ems.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "phony")
public class Phony {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "[Serial Number]")
    private String serialNumber;
    @Column(name = "[Time]", unique=true)
    private LocalDateTime time;
    @Column(name = "[Column 1]")
    private Integer column1;
    @Column(name = "[Column 2]")
    private String column2;
    @Column(name = "[Column 3]")
    private String column3;

    // Getters and Setters")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getColumn1() {
        return this.column1;
    }

    public void setColumn1(Integer column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return this.column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return this.column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

}