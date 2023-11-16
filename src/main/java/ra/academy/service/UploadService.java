package ra.academy.service;

import com.google.cloud.storage.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service // chú thích đây là lớp xử lí logic nghiệp vụ và tự dộng tạo bean
public class UploadService {
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private Storage storage;
    public String uploadFile(MultipartFile file){

        String bucketName = "upload-firebase-ac9f5.appspot.com";
        // upload file lên server
        String uploadPath = servletContext.getRealPath("/uploads");
        File fileUpload = new File(uploadPath);
        if (!fileUpload.exists()){
            fileUpload.mkdirs();
        }
        String localFilePath = uploadPath+File.separator+ RandomStringUtils.randomAlphanumeric(10) +file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(),new File(localFilePath)); // upload lên server
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return uploadFileFromServerToFirebaseStorage(storage,localFilePath,bucketName);
    }

    private String uploadFileFromServerToFirebaseStorage(Storage storage, String localFilePath, String bucketName)  {
        Path localPath = Paths.get(localFilePath);
        String fileName = localPath.getFileName().toString();

        BlobId blobId = BlobId.of(bucketName, fileName); // tạo định danh của file upload thông qua kho lưu trữ và tên ảnh
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        // Thiết lập quyền truy cập công cộng  - lấy ra đường dẫn công khai
        List<Acl> acls = new ArrayList<>();
        acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        blobInfo = blobInfo.toBuilder().setAcl(acls).build(); // thêm quyền vào cấu hình file upload
        try {
            Blob blob = storage.create(blobInfo, Files.readAllBytes(localPath));
            return blob.getMediaLink(); // lấy về đường dẫn vừa upload online + có thể download được
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
