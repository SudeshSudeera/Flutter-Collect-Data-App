package com.dsmp.collectdatadsmpapi.util.mapper;

import com.dsmp.collectdatadsmpapi.dto.AddressDTO;
import com.dsmp.collectdatadsmpapi.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toAddress(AddressDTO addressDTO);
}
