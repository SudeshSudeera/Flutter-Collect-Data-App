package com.dsmp.collectdatadsmpapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @Column(length = 80, name = "address_id")
    private String addressId;

    @Column(length = 250, name = "address")
    private String address;

    @Column( name = "longitude")
    private double longitude;

    @Column( name = "latitude")
    private double latitude;


    //    ---------- Mappings --------------

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "business_id" , unique = true)
    private Business business;

}
