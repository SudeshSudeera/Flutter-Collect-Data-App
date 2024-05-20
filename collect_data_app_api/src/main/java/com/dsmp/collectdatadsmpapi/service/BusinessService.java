package com.dsmp.collectdatadsmpapi.service;

import com.oznhub.oznhubcollectdataserviceapi.dto.request.RequestBusinessDetailsDTO;
import com.dsmp.collectdatadsmpapi.dto.response.*;
import com.dsmp.collectdatadsmpapi.dto.response.core.*;
import com.dsmp.collectdatadsmpapi.dto.response.paginated.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

public interface BusinessService {
    CommonResponseDTO savedBusiness(MultipartFile file, RequestBusinessDetailsDTO requestBusinessDetailsDTO);

    PaginatedResponseBusinessDTO allBusiness(int page, int size,String searchText) throws SQLException;

    Long countAllBusiness(String regDate, String userId);

    ResponseBusinessDTO getById(String id) throws SQLException;

    boolean deleteById(String id);
}
