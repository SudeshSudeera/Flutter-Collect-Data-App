package com.dsmp.collectdatadsmpapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseContactDetailsDTO {
    private String contactId;
    private String home;
    private String mobile;
}
