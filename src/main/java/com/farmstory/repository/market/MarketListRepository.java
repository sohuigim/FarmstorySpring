package com.farmstory.repository.market;

import com.farmstory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketListRepository extends JpaRepository<Product, Integer> {
}
