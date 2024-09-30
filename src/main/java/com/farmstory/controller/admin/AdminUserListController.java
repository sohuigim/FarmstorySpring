package com.farmstory.controller.admin;

import com.farmstory.dto.UserDTO;
import com.farmstory.entity.User;
import com.farmstory.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Controller
public class AdminUserListController {

    private final UserService userService;

    @GetMapping("/admin/UserList")
    public String AdminUser(Model model) {
        List<UserDTO> userDto = userService.selectUsers();
        log.info(userDto);
        model.addAttribute("userDto", userDto);
        return "/admin/user/UserList";
    }


}