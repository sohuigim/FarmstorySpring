package com.farmstory.service;

import com.farmstory.dto.CartDTO;
import com.farmstory.entity.Cart;
import com.farmstory.repository.market.CartRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    public void insertCart(CartDTO cartDTO) {

        Cart cart = modelMapper.map(cartDTO, Cart.class);
        cartRepository.save(cart);
    }
    public CartDTO selectCart(int cartNo) {
        return null;
    }
    public List<CartDTO> selectCartAll(String userId) {
        List<Cart> carts = cartRepository.findCartByUserId(userId);

        List<CartDTO> cartDTOS = carts.stream().map(Cart::toDTO).toList();

        return cartDTOS;
    }
    public void updateCart(Cart cart) {}
    public void deleteCart(int cartNo) {}

}
