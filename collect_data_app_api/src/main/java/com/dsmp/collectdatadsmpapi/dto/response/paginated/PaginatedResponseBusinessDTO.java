package com.dsmp.collectdatadsmpapi.dto.response.paginated;

import com.dsmp.collectdatadsmpapi.dto.response.ResponseBusinessDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponseBusinessDTO {
    private Long count;
    private List<ResponseBusinessDTO> dataList;

}
