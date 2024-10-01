package com.farmstory.controller.myinfo;

import com.farmstory.dto.CartDTO;
import com.farmstory.dto.ProductDTO;
import com.farmstory.service.CartService;
import com.farmstory.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
public class MyinfoCartController {

    private final CartService cartService;
    private final ProductService productService;


    @GetMapping("/userinfo/UserMyinfoCart12")
    public String MarketCart12(@RequestParam(required = false) String userId, Model model) {


        List<CartDTO> carts = cartService.selectCartAll(userId);

        ProductDTO product = null;

        for(CartDTO cartDTO : carts) {

            cartDTO.setProdDTO(productService.selectProduct(cartDTO.getProdNo()));

            product = cartDTO.getProdDTO();
            product.setCartProdCount(cartDTO.getCartProdCount());


        }
        log.info("222222222222222"+carts);

        model.addAttribute("carts", carts);
        return "/user/UserMyinfoCart";
    }
}
