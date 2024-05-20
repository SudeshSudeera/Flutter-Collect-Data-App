package com.dsmp.collectdatadsmpapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAddressDetailsDTO {
    private String addressId;
    private String address;
    private double longitude;
    private double latitude;
}
