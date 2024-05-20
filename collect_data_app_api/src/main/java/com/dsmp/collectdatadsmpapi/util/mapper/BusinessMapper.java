package com.dsmp.collectdatadsmpapi.util.mapper;

import com.dsmp.collectdatadsmpapi.dto.BusinessDTO;
import com.dsmp.collectdatadsmpapi.entity.Business;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusinessMapper {
    Business toBusiness(BusinessDTO businessDTO);
}
