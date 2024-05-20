package com.dsmp.collectdatadsmpapi.service.process;



import com.dsmp.collectdatadsmpapi.dto.response.core.CommonFileSavedBinaryDataDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public CommonFileSavedBinaryDataDTO createResource(MultipartFile file, String directory, String bucket, String prefix, int keyLength);
    public void deleteResource(String bucket,String directory, String fileName);
    public byte[] downloadFile(String bucket, String fileName);
}
