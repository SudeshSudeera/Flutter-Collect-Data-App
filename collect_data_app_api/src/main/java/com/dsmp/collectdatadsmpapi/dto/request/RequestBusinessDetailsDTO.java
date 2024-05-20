package com.oznhub.oznhubcollectdataserviceapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class RequestBusinessDetailsDTO {
    private String businessName;
    private String businessType;
    private String businessOwnerType;
    private Date registerDate;
    private String address;
    private double longitude;
    private double latitude;
    private String home;
    private String mobile;
}
