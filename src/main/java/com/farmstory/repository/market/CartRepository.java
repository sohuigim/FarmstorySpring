package com.farmstory.repository.market;

import com.farmstory.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer>, CartRepositoryCustom {

    public List<Cart> findCartByUserId(String userId);


}
