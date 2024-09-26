package com.farmstory.controller.market;

import com.farmstory.dto.CartDTO;
import com.farmstory.dto.ProductDTO;
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

import java.util.List;

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

            log.info("ordercontroller. proddto : "+product);


        }

        model.addAttribute("products", product);

        return "/market/MarketCart";
    }

    @PostMapping("/market/MarketCart")
    public void MarketCart(CartDTO cartDTO){

        log.info(cartDTO);

        cartService.insertCart(cartDTO);
        cartDTO.setProdDTO(productService.selectProduct(cartDTO.getProdNo()));

        ProductDTO product = cartDTO.getProdDTO();
        product.setCartProdCount(cartDTO.getCartProdCount());

        log.info("ordercontroller. proddto : "+product);

    }


    @GetMapping("/market/MarketOrder12")
    public String MarketOrder12(){


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
