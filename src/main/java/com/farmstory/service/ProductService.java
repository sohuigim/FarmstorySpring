package com.farmstory.service;

import com.farmstory.dto.ProductDTO;
import com.farmstory.entity.Product;
import com.farmstory.repository.product.ProductRepository;
import com.farmstory.repository.product.ProductRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {


    private final ProductRepositoryImpl productRepositoryImpl;



    public List<ProductDTO> selectProducts(){

        List<Product> products = productRepositoryImpl.selectProducts();

        System.out.println("111111111" + products);

        List<ProductDTO> productDTOS = products
                            .stream()
                            .map(entity -> entity.toDTO())
                            .collect(Collectors.toList());

        return productDTOS;
    }

    public ProductService(ProductRepositoryImpl productRepository) {
        this.productRepositoryImpl = productRepository;
    }

    public Page<Product> getPagedProducts(Pageable pageable) {
        return productRepositoryImpl.findAllWithPaging(pageable);
    }

}