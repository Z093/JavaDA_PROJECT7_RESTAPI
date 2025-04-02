package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "curvepoint")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "CurveId")
    @NotNull(message = "CurveId is mandatory")
    private Integer curveId;

    @Column(name = "asOfDate")
    private Timestamp asOfDate;
    
    @Column(name = "term")
    @NotNull(message = "Term is mandatory")
    private Double term;

    @Column(name = "value")
    @NotNull(message = "Value is mandatory")
    private Double value;

    @Column(name = "creationDate")
    private Timestamp creationDate;
}
