package com.dsmp.collectdatadsmpapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;




@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private String addressId;
    private String address;
    private double longitude;
    private double latitude;
    private BusinessDTO business;
}
