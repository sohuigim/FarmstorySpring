package com.farmstory.controller.user;

import com.farmstory.dto.UserDTO;
import com.farmstory.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@Controller
public class UserMyinfoController {

    private final UserService userService;

    @GetMapping("userInfo/UserMyinfo")
    public String UserMyinfo(Authentication authentication, Model model){

        if(authentication != null){
            String uid = authentication.getName();
            UserDTO userDTO = userService.selectUserById(uid);

            model.addAttribute("user", userDTO);

            return "user/UserMyinfo";
        }else{
            return "redirect:/user/UserLogin";
        }

    }

    @ResponseBody
    @PostMapping("userInfo/UserMyinfo")
    public ResponseEntity UserMyinfo(@RequestBody UserDTO userDTO) {
        String uid = userDTO.getUserUid();
        UserDTO resultUser = userService.selectUserById(uid);

        resultUser.setUserPass(userDTO.getUserPass());
        ResponseEntity result = userService.updateUserPass(resultUser);
        return result;
    }

        @GetMapping("userInfo/UserMyinfoCart")
    public String UserMyinfoCart(){
        return "user/UserMyinfoCart";
    }

    @GetMapping("userInfo/UserMyinfoOrder")
    public String UserMyinfoOrder(){
        return "user/UserMyinfoOrder";
    }
}
