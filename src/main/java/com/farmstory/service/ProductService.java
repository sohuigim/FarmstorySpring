package com.farmstory.service;

import com.farmstory.dto.MarketPageRequestDTO;
import com.farmstory.dto.MarketPageResponseDTO;
import com.farmstory.dto.ProductDTO;
import com.farmstory.entity.Product;
import com.farmstory.repository.product.ProductRepository;
import com.farmstory.repository.product.ProductRepositoryImpl;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProductService {


    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductDTO selectProduct(int pNo) {
        Optional<Product> opt = productRepository.findById(pNo);

        Product product = null;

        if (opt.isPresent()) {
            product = opt.get();
        }

        return modelMapper.map(product, ProductDTO.class);

    }
    public List<ProductDTO> selectProducts(){

        List<Product> products = productRepository.selectProducts();

        List<ProductDTO> productDTOS = products
                .stream()
                .map(entity -> entity.toDTO())
                .collect(Collectors.toList());

        return productDTOS;
    }

    public MarketPageResponseDTO selectProductAll(MarketPageRequestDTO marketPageRequestDTO, int catetype){
        Pageable pageable = marketPageRequestDTO.getPageable("prodNo");

        marketPageRequestDTO.setCateType(catetype);

        Page<Tuple> pageProduct = productRepository.selectProductAllForList(marketPageRequestDTO, pageable, catetype);

        List<ProductDTO> productList = pageProduct.getContent().stream().map(tuple -> {

            Product product = tuple.get(0, Product.class);

            return modelMapper.map(product, ProductDTO.class);

        }).toList();

        int total = (int) pageProduct.getTotalElements();

        return MarketPageResponseDTO.builder()
                .marketPageRequestDTO(marketPageRequestDTO)
                .dtoList(productList)
                .total(total)
                .build();
    }

}