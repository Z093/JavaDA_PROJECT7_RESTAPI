package com.nnk.springboot.domain;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CurvePoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "CurveId")
    private Byte curveId;

    @Column(name = "asOfDate")
    private LocalDateTime asOfDate;

    @Column(name = "term")
    private Double term;

    @Column(name = "value")
    private Double value;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getCurveId() {
        return curveId;
    }

    public void setCurveId(Byte curveId) {
        this.curveId = curveId;
    }

    public LocalDateTime getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(LocalDateTime asOfDate) {
        this.asOfDate = asOfDate;
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}

