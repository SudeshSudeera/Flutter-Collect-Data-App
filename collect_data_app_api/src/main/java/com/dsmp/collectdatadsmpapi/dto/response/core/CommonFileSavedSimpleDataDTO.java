package com.dsmp.collectdatadsmpapi.dto.response.core;


import com.oznhub.oznhubcollectdataserviceapi.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonFileSavedSimpleDataDTO implements SuperDTO {
    private String hash;
    private String directory;
    private String fileName;
    private String resourceUrl;
}
