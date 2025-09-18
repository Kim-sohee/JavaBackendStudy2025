package com.sinse.productservice.model.product;

import com.sinse.productservice.domain.Product;
import com.sinse.productservice.domain.ProductFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    //상품 등록에 필요한 Jpa
    private final JpaProductRepository jpaProductRepository;

    //상품 이미지 등록에 필요한 UploadManager
    private final UploadManager uploadManager;

    //상품 이미지 등록에 필요한 Jpa
    private final JpaProductFileRespository jpaProductFileRespository;

    @Override
    public void save(Product product, List<MultipartFile> files) {
        /*--------------------------------------------------------
        *   상품 등록
        *   (상품 등록해야 pk가 반환 -> pk가 있어야 디렉토리 생성, product_file에 사용)
        * -------------------------------------------------------*/
        Product savedProduct = jpaProductRepository.save(product);
        log.debug("save product {}", savedProduct);

        //이미지 수만큼 UploadManager의 store()를 호출하자!
        //단, 트랜잭션의 대상은 파일이 아니라 DB만 가능하므로, 파일 저장 시 하나라도 실패하면
        //비록 트랜잭션 대상은 아니지만, 처음부터 파일이 없었던 것으로 돌려놓아야 하므로, 디렉토리 자체를 날리자
        List<ProductFile> productFileList = files.stream().map(file -> {    //선언적 프로그래밍 방식
            try {
                return uploadManager.store(product, file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toList();

        productFileList.forEach(productFile -> {
            jpaProductFileRespository.save(productFile);
        });
    }
}
