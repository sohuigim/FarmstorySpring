package com.farmstory.controller.market;

import com.farmstory.dto.*;
import com.farmstory.entity.Cart;
import com.farmstory.service.CartService;
import com.farmstory.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Controller
public class MarketOrderController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/market/MarketCart")
    public String MarketCart(@RequestParam(required = false) String userId, Model model) {

        List<CartDTO> carts = cartService.selectCartAll(userId);

        ProductDTO product = null;

        for(CartDTO cartDTO : carts) {

            cartDTO.setProdDTO(productService.selectProduct(cartDTO.getProdNo()));

            product = cartDTO.getProdDTO();
            product.setCartProdCount(cartDTO.getCartProdCount());

        }
        log.info("222222222222222"+carts);

        model.addAttribute("carts", carts);

        return "/market/MarketCart";
    }

    @PostMapping("/market/MarketCart")
    public ResponseEntity<Map<String, Object>> MarketCart(CartDTO cartDTO){

        log.info(cartDTO);

        cartService.insertCart(cartDTO);
        cartDTO.setProdDTO(productService.selectProduct(cartDTO.getProdNo()));

        ProductDTO product = cartDTO.getProdDTO();
        product.setCartProdCount(cartDTO.getCartProdCount());

        log.info("ordercontroller. proddto : "+product);


        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("cartItems", product);

        return ResponseEntity.ok(response);

    }


    @GetMapping("/market/MarketOrder12")
    public String MarketOrder12(@RequestParam(required = false) String userId, Model model) {

        List<CartDTO> carts = cartService.selectCartAll(userId);

        log.info("22221111112222"+carts);

        for(CartDTO cartDTO : carts) {

            cartDTO.setProdDTO(productService.selectProduct(cartDTO.getProdNo()));

            ProductDTO product = cartDTO.getProdDTO();
            product.setCartProdCount(cartDTO.getCartProdCount());

        }
        log.info("22221111112222"+carts);

        model.addAttribute("carts", carts);

        return "/market/MarketOrder";
    }

    @GetMapping("/market/MarketOrder")
    public String MarketOrder(@RequestParam(required = false) int prodNo, @RequestParam(required = false) int cartProdCount, Model model){

        ProductDTO product = productService.selectProduct(prodNo);

        product.setCartProdCount(cartProdCount);

        model.addAttribute("product", product);

        return "/market/MarketOrder";
    }


}
