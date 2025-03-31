package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Entity representing a trade.
 */
@Entity
@Table(name = "trade")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trade {
    // Unique identifier for the trade
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TradeId")
    private Integer tradeId;

    // Account associated with the trade
    @NotBlank(message = "Account is mandatory")
    @Column(name = "account")
    private String account;

    // Type of trade (e.g. buy, sell)
    @NotBlank(message = "Type is mandatory")
    @Column(name = "type")
    private String type;

    // Quantity of the security being bought
    @Column(name = "buyQuantity")
    private Double buyQuantity;

    // Quantity of the security being sold
    @Column(name = "sellQuantity")
    private Double sellQuantity;

    // Price at which the security is being bought
    @Column(name = "buyPrice")
    private Double buyPrice;

    // Price at which the security is being sold
    @Column(name = "sellPrice")
    private Double sellPrice;

    // Date on which the trade was executed
    @Column(name = "tradeDate")
    private Timestamp tradeDate;

    // Security being traded (e.g. stock, bond)
    @Column(name = "security")
    private String security;

    // Status of the trade (e.g. pending, executed)
    @Column(name = "status")
    private String status;

    // Trader who executed the trade
    @Column(name = "trader")
    private String trader;

    // Benchmark used for the trade
    @Column(name = "benchmark")
    private String benchmark;

    // Book in which the trade is recorded
    @Column(name = "book")
    private String book;

    // Name of the user who created the trade
    @Column(name = "creationName")
    private String creationName;

    // Date on which the trade was created
    @Column(name = "creationDate")
    private Timestamp creationDate;

    // Name of the user who last revised the trade
    @Column(name = "revisionName")
    private String revisionName;

    // Date on which the trade was last revised
    @Column(name = "revisionDate")
    private Timestamp revisionDate;

    // Name of the deal associated with the trade
    @Column(name = "dealName")
    private String dealName;

    // Type of deal associated with the trade
    @Column(name = "dealType")
    private String dealType;

    // ID of the source list associated with the trade
    @Column(name = "sourceListId")
    private String sourceListId;

    // Side of the trade (e.g. buy, sell)
    @Column(name = "side")
    private String side;
}
