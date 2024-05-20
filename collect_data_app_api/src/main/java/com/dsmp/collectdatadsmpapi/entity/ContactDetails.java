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
@Table(name = "contact_details")
public class ContactDetails {

    @Id
    @Column(length = 80, name = "contact_id")
    private String contactId;

    @Column(length = 45, name = "home")
    private String home;

    @Column(length = 45, name = "mobile", nullable = false,unique = true )
    private String mobile;



    //    ---------- Mappings --------------

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "business_id" , unique = true)
    private Business business;
}