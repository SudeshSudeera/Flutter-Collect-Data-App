package com.dsmp.collectdatadsmpapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBusinessDetailsDTO {
    private String businessId;
    private String businessName;
    private String mobile;
}
