package com.dsmp.collectdatadsmpapi.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oznhub.oznhubcollectdataserviceapi.dto.request.RequestBusinessDetailsDTO;
import com.dsmp.collectdatadsmpapi.dto.response.core.CommonResponseDTO;
import com.dsmp.collectdatadsmpapi.service.BusinessService;
import com.dsmp.collectdatadsmpapi.util.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/business")
public class BusinessController {
    private final Logger LOGGER = LoggerFactory.getLogger(BusinessController.class);
    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }


    @PostMapping(path = {"/create"})
    public ResponseEntity<StandardResponse> savedBusiness(
            @RequestParam("file") MultipartFile file,
            @RequestParam("data") String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        RequestBusinessDetailsDTO requestBusinessDetailsDTO = objectMapper.readValue(data, RequestBusinessDetailsDTO.class);
        CommonResponseDTO responseData = businessService.savedBusiness(file, requestBusinessDetailsDTO);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(), responseData.getMessage(), responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }


    @GetMapping(path = "/find-all-business", params = {"page", "size","searchText"})
    public ResponseEntity<StandardResponse> allBusiness(
            @RequestParam int page,
            @RequestParam int size,@RequestParam String searchText) throws SQLException {

        return new ResponseEntity<>(
                new StandardResponse(200, "Business List",
                        businessService.allBusiness(page, size,searchText)),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/business/count-all-business/{userId}", params = {"regDate"})
    public ResponseEntity<StandardResponse> countAllBusiness(

            @RequestParam String regDate,
            @PathVariable String userId) throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(200, "Business Count",
                        businessService.countAllBusiness(regDate, userId)),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-by-id/{id}")
    public ResponseEntity<StandardResponse> getById(

            @PathVariable String id) throws SQLException {

        return new ResponseEntity<>(
                new StandardResponse(200, "Business List",
                        businessService.getById(id)),
                HttpStatus.OK
        );
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<StandardResponse> deleteById(

            @PathVariable String id) throws SQLException {

        return new ResponseEntity<>(
                new StandardResponse(200, "Deleted!",
                        businessService.deleteById(id)),
                HttpStatus.OK
        );
    }
}
    