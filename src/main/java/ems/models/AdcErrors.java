package ems.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AdcErrors", uniqueConstraints = @UniqueConstraint(name = "uniqueAdcErrors", columnNames = { "time", "serialNumber" }))
public class AdcErrors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Serial Number")
    private String serialNumber;
    @Column(name = "Time")
    private LocalDateTime time;
    @Column(name = "Sampling Error")
    private Integer samplingError;
    @Column(name = "full f e PRESSURE")
    private Integer fullFEPressure;
    @Column(name = "full f e TEMP IN")
    private Integer fullFETempIn;
    @Column(name = "full f e TEMP OUT")
    private Integer fullFETempOut;
    @Column(name = "full f e UMAIN")
    private Integer fullFEUmain;
    @Column(name = "filter e PRESSURE")
    private Integer filterEPressure;
    @Column(name = "filter e TEMP IN")
    private Integer filterETempIn;
    @Column(name = "filter e TEMP OUT")
    private Integer filterETempOut;
    @Column(name = "filter e UMAIN")
    private Integer filterEUmain;
    @Column(name = "sample e PRESSURE")
    private Integer sampleEPressure;
    @Column(name = "sample e TEMP IN")
    private Integer sampleETempIn;
    @Column(name = "sample e TEMP OUT")
    private Integer sampleETempOut;
    @Column(name = "sample e UMAIN")
    private Integer sampleEUmain;
    @Column(name = "no sam e PRESSURE")
    private Integer noSamEPressure;
    @Column(name = "no sam e TEMP IN")
    private Integer noSamETempIn;
    @Column(name = "no sam e TEMP OUT")
    private Integer noSamETempOut;
    @Column(name = "no sam e UMAIN")
    private Integer noSamEUmain;

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

    public Integer getSamplingerror() {
        return samplingError;
    }

    public void setSamplingerror(Integer samplingError) {
        this.samplingError = samplingError;
    }

    public Integer getFullfepressure() {
        return fullFEPressure;
    }

    public void setFullfepressure(Integer fullFEPressure) {
        this.fullFEPressure = fullFEPressure;
    }

    public Integer getFullfetempin() {
        return fullFETempIn;
    }

    public void setFullfetempin(Integer fullFETempIn) {
        this.fullFETempIn = fullFETempIn;
    }

    public Integer getFullfetempout() {
        return fullFETempOut;
    }

    public void setFullfetempout(Integer fullFETempOut) {
        this.fullFETempOut = fullFETempOut;
    }

    public Integer getFullfeumain() {
        return fullFEUmain;
    }

    public void setFullfeumain(Integer fullFEUmain) {
        this.fullFEUmain = fullFEUmain;
    }

    public Integer getFilterepressure() {
        return filterEPressure;
    }

    public void setFilterepressure(Integer filterEPressure) {
        this.filterEPressure = filterEPressure;
    }

    public Integer getFilteretempin() {
        return filterETempIn;
    }

    public void setFilteretempin(Integer filterETempIn) {
        this.filterETempIn = filterETempIn;
    }

    public Integer getFilteretempout() {
        return filterETempOut;
    }

    public void setFilteretempout(Integer filterETempOut) {
        this.filterETempOut = filterETempOut;
    }

    public Integer getFiltereumain() {
        return filterEUmain;
    }

    public void setFiltereumain(Integer filterEUmain) {
        this.filterEUmain = filterEUmain;
    }

    public Integer getSampleepressure() {
        return sampleEPressure;
    }

    public void setSampleepressure(Integer sampleEPressure) {
        this.sampleEPressure = sampleEPressure;
    }

    public Integer getSampleetempin() {
        return sampleETempIn;
    }

    public void setSampleetempin(Integer sampleETempIn) {
        this.sampleETempIn = sampleETempIn;
    }

    public Integer getSampleetempout() {
        return sampleETempOut;
    }

    public void setSampleetempout(Integer sampleETempOut) {
        this.sampleETempOut = sampleETempOut;
    }

    public Integer getSampleeumain() {
        return sampleEUmain;
    }

    public void setSampleeumain(Integer sampleEUmain) {
        this.sampleEUmain = sampleEUmain;
    }

    public Integer getNosamepressure() {
        return noSamEPressure;
    }

    public void setNosamepressure(Integer noSamEPressure) {
        this.noSamEPressure = noSamEPressure;
    }

    public Integer getNosametempin() {
        return noSamETempIn;
    }

    public void setNosametempin(Integer noSamETempIn) {
        this.noSamETempIn = noSamETempIn;
    }

    public Integer getNosametempout() {
        return noSamETempOut;
    }

    public void setNosametempout(Integer noSamETempOut) {
        this.noSamETempOut = noSamETempOut;
    }

    public Integer getNosameumain() {
        return noSamEUmain;
    }

    public void setNosameumain(Integer noSamEUmain) {
        this.noSamEUmain = noSamEUmain;
    }

}