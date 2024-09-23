package com.farmstory.controller.admin;

import com.farmstory.dto.UserDTO;
import com.farmstory.entity.User;
import com.farmstory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminUserListController {

    private final UserService userService;

    @GetMapping("/admin/UserList")
    public String AdminUser(Model model) {
        List<UserDTO> userDto = userService.selectUsers();
        model.addAttribute("userDto", userDto);
        return "/admin/user/UserList";
    }

}
