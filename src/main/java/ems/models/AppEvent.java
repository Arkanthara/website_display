package ems.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AppEvent", uniqueConstraints = @UniqueConstraint(name = "uniqueAppEvent", columnNames = { "time", "serialNumber" }))
public class AppEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Serial Number")
    private String serialNumber;
    @Column(name = "Time")
    private LocalDateTime time;
    @Column(name = "Pressurization")
    private Integer pressurization;
    @Column(name = "Pairing Start")
    private Integer pairingStart;
    @Column(name = "Pairing Complete")
    private Integer pairingComplete;
    @Column(name = "Cleaning Start")
    private Integer cleaningStart;
    @Column(name = "Cleaning Pause")
    private Integer cleaningPause;
    @Column(name = "Cleaning Finalized")
    private Integer cleaningFinalized;
    @Column(name = "Pedal PZ P1")
    private Integer pedalPzP1;
    @Column(name = "Boost PZ P1")
    private Integer boostPzP1;
    @Column(name = "Pedal PZ P2")
    private Integer pedalPzP2;
    @Column(name = "Boost PZ P2")
    private Integer boostPzP2;
    @Column(name = "Pedal PZ P3")
    private Integer pedalPzP3;
    @Column(name = "Boost PZ P3")
    private Integer boostPzP3;
    @Column(name = "Pedal AF P1")
    private Integer pedalAfP1;
    @Column(name = "Boost AF P1")
    private Integer boostAfP1;
    @Column(name = "Pedal AF P2")
    private Integer pedalAfP2;
    @Column(name = "Boost AF P2")
    private Integer boostAfP2;
    @Column(name = "Pedal AF P3")
    private Integer pedalAfP3;
    @Column(name = "Boost AF P3")
    private Integer boostAfP3;
    @Column(name = "Pedal AFWO P1")
    private Integer pedalAfwoP1;
    @Column(name = "Pedal AFWO P2")
    private Integer pedalAfwoP2;
    @Column(name = "Pedal AFWO P3")
    private Integer pedalAfwoP3;
    @Column(name = "On Start")
    private Integer onStart;
    @Column(name = "On After 7h")
    private Integer onAfter7H;

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

    public Integer getPressurization() {
        return pressurization;
    }

    public void setPressurization(Integer pressurization) {
        this.pressurization = pressurization;
    }

    public Integer getPairingstart() {
        return pairingStart;
    }

    public void setPairingstart(Integer pairingStart) {
        this.pairingStart = pairingStart;
    }

    public Integer getPairingcomplete() {
        return pairingComplete;
    }

    public void setPairingcomplete(Integer pairingComplete) {
        this.pairingComplete = pairingComplete;
    }

    public Integer getCleaningstart() {
        return cleaningStart;
    }

    public void setCleaningstart(Integer cleaningStart) {
        this.cleaningStart = cleaningStart;
    }

    public Integer getCleaningpause() {
        return cleaningPause;
    }

    public void setCleaningpause(Integer cleaningPause) {
        this.cleaningPause = cleaningPause;
    }

    public Integer getCleaningfinalized() {
        return cleaningFinalized;
    }

    public void setCleaningfinalized(Integer cleaningFinalized) {
        this.cleaningFinalized = cleaningFinalized;
    }

    public Integer getPedalpzp1() {
        return pedalPzP1;
    }

    public void setPedalpzp1(Integer pedalPzP1) {
        this.pedalPzP1 = pedalPzP1;
    }

    public Integer getBoostpzp1() {
        return boostPzP1;
    }

    public void setBoostpzp1(Integer boostPzP1) {
        this.boostPzP1 = boostPzP1;
    }

    public Integer getPedalpzp2() {
        return pedalPzP2;
    }

    public void setPedalpzp2(Integer pedalPzP2) {
        this.pedalPzP2 = pedalPzP2;
    }

    public Integer getBoostpzp2() {
        return boostPzP2;
    }

    public void setBoostpzp2(Integer boostPzP2) {
        this.boostPzP2 = boostPzP2;
    }

    public Integer getPedalpzp3() {
        return pedalPzP3;
    }

    public void setPedalpzp3(Integer pedalPzP3) {
        this.pedalPzP3 = pedalPzP3;
    }

    public Integer getBoostpzp3() {
        return boostPzP3;
    }

    public void setBoostpzp3(Integer boostPzP3) {
        this.boostPzP3 = boostPzP3;
    }

    public Integer getPedalafp1() {
        return pedalAfP1;
    }

    public void setPedalafp1(Integer pedalAfP1) {
        this.pedalAfP1 = pedalAfP1;
    }

    public Integer getBoostafp1() {
        return boostAfP1;
    }

    public void setBoostafp1(Integer boostAfP1) {
        this.boostAfP1 = boostAfP1;
    }

    public Integer getPedalafp2() {
        return pedalAfP2;
    }

    public void setPedalafp2(Integer pedalAfP2) {
        this.pedalAfP2 = pedalAfP2;
    }

    public Integer getBoostafp2() {
        return boostAfP2;
    }

    public void setBoostafp2(Integer boostAfP2) {
        this.boostAfP2 = boostAfP2;
    }

    public Integer getPedalafp3() {
        return pedalAfP3;
    }

    public void setPedalafp3(Integer pedalAfP3) {
        this.pedalAfP3 = pedalAfP3;
    }

    public Integer getBoostafp3() {
        return boostAfP3;
    }

    public void setBoostafp3(Integer boostAfP3) {
        this.boostAfP3 = boostAfP3;
    }

    public Integer getPedalafwop1() {
        return pedalAfwoP1;
    }

    public void setPedalafwop1(Integer pedalAfwoP1) {
        this.pedalAfwoP1 = pedalAfwoP1;
    }

    public Integer getPedalafwop2() {
        return pedalAfwoP2;
    }

    public void setPedalafwop2(Integer pedalAfwoP2) {
        this.pedalAfwoP2 = pedalAfwoP2;
    }

    public Integer getPedalafwop3() {
        return pedalAfwoP3;
    }

    public void setPedalafwop3(Integer pedalAfwoP3) {
        this.pedalAfwoP3 = pedalAfwoP3;
    }

    public Integer getOnstart() {
        return onStart;
    }

    public void setOnstart(Integer onStart) {
        this.onStart = onStart;
    }

    public Integer getOnafter7h() {
        return onAfter7H;
    }

    public void setOnafter7h(Integer onAfter7H) {
        this.onAfter7H = onAfter7H;
    }

}