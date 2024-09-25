package com.farmstory.repository.product;

import com.farmstory.dto.MarketPageRequestDTO;
import com.farmstory.entity.Product;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryCustom {

    public List<Product> selectProducts();
    public Product selectProduct(int prodNo);

    public Page<Tuple> selectProductAllForList(MarketPageRequestDTO marketPageRequestDTO , Pageable pageable, int catetype);
}
