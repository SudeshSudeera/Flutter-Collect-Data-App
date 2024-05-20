package com.dsmp.collectdatadsmpapi.service.impl;

import com.dsmp.collectdatadsmpapi.dto.*;

import com.dsmp.collectdatadsmpapi.service.process.FileService;
import com.dsmp.collectdatadsmpapi.util.FileDataExtractor;
import com.dsmp.collectdatadsmpapi.dto.ContactDetailsDTO;
import com.oznhub.oznhubcollectdataserviceapi.dto.request.RequestBusinessDetailsDTO;
import com.dsmp.collectdatadsmpapi.dto.response.*;

import com.dsmp.collectdatadsmpapi.dto.response.core.*;
import com.dsmp.collectdatadsmpapi.dto.response.paginated.*;
import com.dsmp.collectdatadsmpapi.entity.*;

import com.dsmp.collectdatadsmpapi.enums.*;

import com.dsmp.collectdatadsmpapi.repo.*;
import com.dsmp.collectdatadsmpapi.service.*;
import com.dsmp.collectdatadsmpapi.util.mapper.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepo businessRepo;
    private final BusinessMapper businessMapper;
    private final AddressRepo addressRepo;
    private final AddressMapper addressMapper;
    private final ContactDetailsRepo contactDetailsRepo;
    private final ContactDetailsMapper contactDetailsMapper;


    private final FileService fileService;
    private final FileDataExtractor fileDataExtractor;

    @Value("${bucketName}")
    private String bucketName;

    public BusinessServiceImpl(BusinessRepo businessRepo, BusinessMapper businessMapper, AddressRepo addressRepo, AddressMapper addressMapper, ContactDetailsRepo contactDetailsRepo, ContactDetailsMapper contactDetailsMapper, FileService fileService, FileDataExtractor fileDataExtractor) {
        this.businessRepo = businessRepo;
        this.businessMapper = businessMapper;
        this.addressRepo = addressRepo;
        this.addressMapper = addressMapper;
        this.contactDetailsRepo = contactDetailsRepo;
        this.contactDetailsMapper = contactDetailsMapper;
        this.fileService = fileService;
        this.fileDataExtractor = fileDataExtractor;
    }


    @Override
    public CommonResponseDTO savedBusiness(MultipartFile file, RequestBusinessDetailsDTO dto) {
        CommonFileSavedBinaryDataDTO commonFileSavedBinaryDataDTO = null;
        try {
            commonFileSavedBinaryDataDTO = fileService.createResource(file, "ozh-collect-data/business/file/", bucketName, "AC", 20);

            String lastId = businessRepo.findLastId("OH-B-",
                    6);
            System.out.println(lastId);
            String propertyId = "OH-B-1";

            if (null != lastId) {
                int i = (Integer.parseInt(lastId.split("OH-B-")[1])) + 1;
                propertyId = "OH-B-" + i;
            }


            BusinessDTO businessDTO = new BusinessDTO(
                    propertyId,
                    dto.getBusinessName(),

                    dto.getBusinessType(),
                    BusinessOwnerType.valueOf(dto.getBusinessOwnerType()),
                    new CommonFileSavedBinaryDataDTO(
                            commonFileSavedBinaryDataDTO.getHash(),
                            commonFileSavedBinaryDataDTO.getDirectory(),
                            commonFileSavedBinaryDataDTO.getFileName(),
                            commonFileSavedBinaryDataDTO.getResourceUrl()
                    ),
                    dto.getRegisterDate()


            );
            businessRepo.save(businessMapper.toBusiness(businessDTO));
//------address saved------


            String addressLastId = addressRepo.findLastId("OH-A-",
                    6);

            String addressId = "OH-A-1";

            if (null != addressLastId) {
                int i = (Integer.parseInt(addressLastId.split("OH-A-")[1])) + 1;
                addressId = "OH-A-" + i;
            }

            AddressDTO addressDTO = new AddressDTO(
                    addressId,
                    dto.getAddress(),
                    dto.getLongitude(),
                    dto.getLatitude(),
                    businessDTO
            );


            addressRepo.save(addressMapper.toAddress(addressDTO));

//------contact details saved------
            String contactDetailsLastId = contactDetailsRepo.findLastId("OH-CD-",
                    7);

            String contactDetailsId = "OH-CD-1";

            if (null != contactDetailsLastId) {
                int i = (Integer.parseInt(contactDetailsLastId.split("OH-CD-")[1])) + 1;
                contactDetailsId = "OH-CD-" + i;
            }

            ContactDetailsDTO contactDetailsDTO = new ContactDetailsDTO(
                    contactDetailsId,
                    dto.getHome(),
                    dto.getMobile(),
                    businessDTO
            );
            contactDetailsRepo.save(contactDetailsMapper.toContactDetails(contactDetailsDTO));


            return new CommonResponseDTO(201, "Business  saved!", businessDTO.getBusinessId(), new ArrayList<>());


        } catch (Exception e) {
            System.out.println(e);
            try {
                assert commonFileSavedBinaryDataDTO != null;
                fileService.deleteResource(bucketName, commonFileSavedBinaryDataDTO.getDirectory(), fileDataExtractor.extractActualFileName(
                        new InputStreamReader(
                                commonFileSavedBinaryDataDTO.getFileName().getBinaryStream())));

            } catch (SQLException ex) {
                throw new IllegalStateException("Something went wrong please try again later !");
            }
            throw new IllegalStateException("Something went wrong please try again later!");
        }
    }

    @Override
    public PaginatedResponseBusinessDTO allBusiness(int page, int size, String searchText) throws SQLException {
        List<Business> allBusinessForProvidedDate = businessRepo.getAllBusinessForProvidedDate(PageRequest.of(page, size), searchText);
        List<ResponseBusinessDTO> responseBusinessDTOS = new ArrayList<>();
        for (Business b : allBusinessForProvidedDate
        ) {

            responseBusinessDTOS.add(
                    new ResponseBusinessDTO(
                            b.getBusinessId(),
                            b.getBusinessName(),

                            b.getBusinessType(),
                            b.getBusinessOwnerType(),
                            b.getRegisterDate(),

                            new String(b.getFileResource().getResourceUrl().getBytes(1, (int) b.getFileResource().getResourceUrl().length())),
                            new ResponseAddressDetailsDTO(
                                    b.getAddress().getAddressId(),
                                    b.getAddress().getAddress(),
                                    b.getAddress().getLongitude(),
                                    b.getAddress().getLatitude()
                            ),
                            new ResponseContactDetailsDTO(
                                    b.getContactDetails().getContactId(),
                                    b.getContactDetails().getHome(),
                                    b.getContactDetails().getMobile()
                            )
                    )
            );

        }
        return new PaginatedResponseBusinessDTO(
                businessRepo.countAllBusinessForProvidedDate(searchText),
                responseBusinessDTOS
        );

    }

    @Override
    public Long countAllBusiness(String regDate, String userId) {


        return businessRepo.countAllBusinessForProvidedDate(userId);

    }

    @Override
    public ResponseBusinessDTO getById(String id) throws SQLException {

        Optional<Business> b = businessRepo.findById(id);

        return new ResponseBusinessDTO(
                b.get().getBusinessId(),
                b.get().getBusinessName(),

                b.get().getBusinessType(),
                b.get().getBusinessOwnerType(),
                b.get().getRegisterDate(),

                new String(b.get().getFileResource().getResourceUrl().getBytes(1, (int) b.get().getFileResource().getResourceUrl().length())),
                new ResponseAddressDetailsDTO(
                        b.get().getAddress().getAddressId(),
                        b.get().getAddress().getAddress(),
                        b.get().getAddress().getLongitude(),
                        b.get().getAddress().getLatitude()
                ),
                new ResponseContactDetailsDTO(
                        b.get().getContactDetails().getContactId(),
                        b.get().getContactDetails().getHome(),
                        b.get().getContactDetails().getMobile()
                )
        );


    }

    @Override
    public boolean deleteById(String id) {

        businessRepo.deleteById(id);
        return true;
    }
}
