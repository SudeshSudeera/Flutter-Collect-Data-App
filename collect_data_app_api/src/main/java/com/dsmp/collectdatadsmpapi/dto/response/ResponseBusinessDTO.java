package com.dsmp.collectdatadsmpapi.dto.response;


import com.dsmp.collectdatadsmpapi.enums.BusinessOwnerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBusinessDTO {
    private String businessId;
    private String businessName;

    private String businessType;
    private BusinessOwnerType businessOwnerType;
    private Date registerDate;
    private String resourceUrl;
    private ResponseAddressDetailsDTO addressDetails;
    private ResponseContactDetailsDTO contactDetailsDTO;

}
