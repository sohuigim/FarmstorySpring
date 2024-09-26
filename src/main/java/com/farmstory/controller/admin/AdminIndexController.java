package com.farmstory.controller.admin;

import com.farmstory.dto.ProductDTO;
import com.farmstory.dto.UserDTO;
import com.farmstory.service.ProductService;
import com.farmstory.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
public class AdminIndexController {
    private final ProductService productService;
    private final UserService userService;
    @GetMapping("/admin/AdminIndex")
    public String AdminIndex(Model model) {
        List<ProductDTO> productDTO = productService.selectProducts();
        log.info(productDTO);
        model.addAttribute("productDTOs",productDTO);
        List<UserDTO> userDto = userService.selectUsers();
        log.info(userDto);
        model.addAttribute("userDto", userDto);
        return "/admin/AdminIndex";
    }
}
