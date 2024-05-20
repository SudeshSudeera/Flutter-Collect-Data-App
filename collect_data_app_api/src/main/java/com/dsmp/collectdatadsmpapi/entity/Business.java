package com.dsmp.collectdatadsmpapi.entity;

import com.oznhub.oznhubcollectdataserviceapi.entity.core.FileResource;
import com.dsmp.collectdatadsmpapi.enums.BranchType;
import com.dsmp.collectdatadsmpapi.enums.BusinessOwnerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "business")
public class Business {

    @Id
    @Column(length = 80, name = "business_id")
    private String businessId;

    @Column(length = 250, name = "business_name", nullable = false)
    private String businessName;


    @Column(length = 450, name = "business_type")
    private String businessType;

    @Column(name = "business_owner_type")
    @Enumerated(EnumType.STRING)
    private BusinessOwnerType businessOwnerType;

    @Embedded
    private FileResource fileResource;

    @Column( name = "register_date")
    private Date registerDate;

    @OneToOne(mappedBy = "business", cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(mappedBy = "business", cascade = CascadeType.ALL)
    private ContactDetails contactDetails;

}
