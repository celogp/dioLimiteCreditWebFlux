package com.sw1tech.customercreditWebflux.entity;

import com.sw1tech.customercreditWebflux.enums.EDayGood;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EDayGood type;

    @Column(nullable = false)
    private double limitcredit;

}
