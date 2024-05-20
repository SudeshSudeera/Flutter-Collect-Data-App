package com.dsmp.collectdatadsmpapi.dto;

import com.dsmp.collectdatadsmpapi.dto.response.core.CommonFileSavedBinaryDataDTO;
import com.dsmp.collectdatadsmpapi.enums.BranchType;
import com.dsmp.collectdatadsmpapi.enums.BusinessOwnerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BusinessDTO {
    private String businessId;

    private String businessName;

    private String businessType;

    private BusinessOwnerType businessOwnerType;

    private CommonFileSavedBinaryDataDTO fileResource;

    private Date registerDate;

}
