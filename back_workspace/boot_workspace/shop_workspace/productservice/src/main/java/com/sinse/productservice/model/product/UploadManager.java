package com.sinse.productservice.model.product;

import com.sinse.productservice.domain.Product;
import com.sinse.productservice.domain.ProductFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Slf4j
@Component
public class UploadManager {
    @Value("${file.upload-dir}")
    private String uploadDir;

    //서비스가 이 메서드를 파일수만큼 호출할 예정
    public ProductFile store(Product product, MultipartFile file) throws IOException{
        createDirectory(uploadDir);     //루트 디렉토리 생성

        //각 상품에 1:1 대응하는 디렉토리
         Path savePath = createDirectory(uploadDir + "/p" + product.getProductId());

         //파일명 생성
        String originalName = file.getOriginalFilename();     //사용자가 원래 부여한 파일명
        String extension = null;        //확장자

        //파일이 존재하면서, 파일명에 .이 포함된 유효한 경우
        if(originalName != null && originalName.contains(".")){
            extension = originalName.substring(originalName.lastIndexOf("."));
        }
        String newFilename = UUID.randomUUID().toString()  + extension;
        log.debug("개발자가 정의한 새로운 파일명은 "+newFilename);

        //파일 저장
        Path targetLocation = savePath.resolve(newFilename);    //OS에 적절한 경로로 변경
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        //저장 정보를 담을 ProductFile 을 반환
        ProductFile productFile = new ProductFile();
        productFile.setProduct(product);
        productFile.setFileName(newFilename);
        productFile.setOriginalName(originalName);
        productFile.setContentType(file.getContentType());
        productFile.setFileSize(file.getSize());
        productFile.setFilePath(targetLocation.toString());

        return productFile;
    }

    //디렉토리 생성 메서드 정의(root, 상품마다 필요한 디렉토리..)
    //createDirectory("c:/dev/upload"), createDirectory("p23")
    public Path createDirectory(String path) throws IOException {
        //기존 java.io.File과 동일한 업무를 수행하지만, os 특화된 처리에서 유용함
        Path dir = Paths.get(path);
        Path savePath = Paths.get(path).toAbsolutePath().normalize();

        //디렉토리가 존재하지 않을 경우, 디렉토리 생성
        if(!(Files.exists(dir) && Files.isDirectory(dir))) {
            Files.createDirectory(savePath);
        }else{
            log.debug("{} Directory already exists", path);
        }

        return savePath;
    }

}
