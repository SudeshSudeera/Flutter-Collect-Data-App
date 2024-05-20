package com.dsmp.collectdatadsmpapi.util.mapper;

import com.dsmp.collectdatadsmpapi.dto.ContactDetailsDTO;
import com.dsmp.collectdatadsmpapi.entity.ContactDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactDetailsMapper {
    ContactDetails toContactDetails(ContactDetailsDTO contactDetailsDTO);
}
