package com.dsmp.collectdatadsmpapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ContactDetailsDTO {

    private String contactId;
    private String home;
    private String mobile;
    private BusinessDTO business;

}
