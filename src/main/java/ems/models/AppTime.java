package ems.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AppTime", uniqueConstraints = @UniqueConstraint(name = "uniqueAppTime", columnNames = { "time", "serialNumber" }))
public class AppTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Serial Number")
    private String serialNumber;
    @Column(name = "Time")
    private LocalDateTime time;
    @Column(name = "Pressurized")
    private Integer pressurized;
    @Column(name = "Run PZ P1")
    private Integer runPzP1;
    @Column(name = "Boost PZ P1")
    private Integer boostPzP1;
    @Column(name = "Run PZ P2")
    private Integer runPzP2;
    @Column(name = "Boost PZ P2")
    private Integer boostPzP2;
    @Column(name = "Run PZ P3")
    private Integer runPzP3;
    @Column(name = "Boost PZ P3")
    private Integer boostPzP3;
    @Column(name = "Run AF Water Only P1")
    private Integer runAfWaterOnlyP1;
    @Column(name = "Run AF Water Only P2")
    private Integer runAfWaterOnlyP2;
    @Column(name = "Run AF Water Only P3")
    private Integer runAfWaterOnlyP3;
    @Column(name = "Run AF P1")
    private Integer runAfP1;
    @Column(name = "Run AF P2")
    private Integer runAfP2;
    @Column(name = "Run AF P3")
    private Integer runAfP3;
    @Column(name = "Boost AF P1")
    private Integer boostAfP1;
    @Column(name = "Boost AF P2")
    private Integer boostAfP2;
    @Column(name = "Boost AF P3")
    private Integer boostAfP3;
    @Column(name = "Clean Pause")
    private Integer cleanPause;

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

    public Integer getPressurized() {
        return pressurized;
    }

    public void setPressurized(Integer pressurized) {
        this.pressurized = pressurized;
    }

    public Integer getRunpzp1() {
        return runPzP1;
    }

    public void setRunpzp1(Integer runPzP1) {
        this.runPzP1 = runPzP1;
    }

    public Integer getBoostpzp1() {
        return boostPzP1;
    }

    public void setBoostpzp1(Integer boostPzP1) {
        this.boostPzP1 = boostPzP1;
    }

    public Integer getRunpzp2() {
        return runPzP2;
    }

    public void setRunpzp2(Integer runPzP2) {
        this.runPzP2 = runPzP2;
    }

    public Integer getBoostpzp2() {
        return boostPzP2;
    }

    public void setBoostpzp2(Integer boostPzP2) {
        this.boostPzP2 = boostPzP2;
    }

    public Integer getRunpzp3() {
        return runPzP3;
    }

    public void setRunpzp3(Integer runPzP3) {
        this.runPzP3 = runPzP3;
    }

    public Integer getBoostpzp3() {
        return boostPzP3;
    }

    public void setBoostpzp3(Integer boostPzP3) {
        this.boostPzP3 = boostPzP3;
    }

    public Integer getRunafwateronlyp1() {
        return runAfWaterOnlyP1;
    }

    public void setRunafwateronlyp1(Integer runAfWaterOnlyP1) {
        this.runAfWaterOnlyP1 = runAfWaterOnlyP1;
    }

    public Integer getRunafwateronlyp2() {
        return runAfWaterOnlyP2;
    }

    public void setRunafwateronlyp2(Integer runAfWaterOnlyP2) {
        this.runAfWaterOnlyP2 = runAfWaterOnlyP2;
    }

    public Integer getRunafwateronlyp3() {
        return runAfWaterOnlyP3;
    }

    public void setRunafwateronlyp3(Integer runAfWaterOnlyP3) {
        this.runAfWaterOnlyP3 = runAfWaterOnlyP3;
    }

    public Integer getRunafp1() {
        return runAfP1;
    }

    public void setRunafp1(Integer runAfP1) {
        this.runAfP1 = runAfP1;
    }

    public Integer getRunafp2() {
        return runAfP2;
    }

    public void setRunafp2(Integer runAfP2) {
        this.runAfP2 = runAfP2;
    }

    public Integer getRunafp3() {
        return runAfP3;
    }

    public void setRunafp3(Integer runAfP3) {
        this.runAfP3 = runAfP3;
    }

    public Integer getBoostafp1() {
        return boostAfP1;
    }

    public void setBoostafp1(Integer boostAfP1) {
        this.boostAfP1 = boostAfP1;
    }

    public Integer getBoostafp2() {
        return boostAfP2;
    }

    public void setBoostafp2(Integer boostAfP2) {
        this.boostAfP2 = boostAfP2;
    }

    public Integer getBoostafp3() {
        return boostAfP3;
    }

    public void setBoostafp3(Integer boostAfP3) {
        this.boostAfP3 = boostAfP3;
    }

    public Integer getCleanpause() {
        return cleanPause;
    }

    public void setCleanpause(Integer cleanPause) {
        this.cleanPause = cleanPause;
    }

}