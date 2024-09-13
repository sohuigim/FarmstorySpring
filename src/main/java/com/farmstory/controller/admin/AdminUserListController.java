package com.farmstory.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminUserListController {

    @GetMapping("/admin/UserList")
    public String AdminUser() {
        return "/admin/user/UserList";
    }

}
