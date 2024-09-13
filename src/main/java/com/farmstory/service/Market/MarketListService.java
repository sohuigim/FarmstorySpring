package com.farmstory.service.Market;

import com.farmstory.dto.ProductDTO;
import com.farmstory.entity.Product;
import com.farmstory.repository.market.MarketListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MarketListService {

    private final MarketListRepository marketListRepository;

    public List<ProductDTO> selectProducts(){

        List<Product> products = marketListRepository.findAll();

        List<ProductDTO> productDTOS = products
                            .stream()
                            .map(entity -> entity.toDTO())
                            .collect(Collectors.toList());

        return productDTOS;
    }

}
