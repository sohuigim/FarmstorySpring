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

    UserDTO resultUser = new UserDTO();

    @GetMapping("userInfo/UserMyinfo")
    public String UserMyinfo(Authentication authentication, Model model){

        if(authentication != null){
            String uid = authentication.getName();
            UserDTO userDTO = userService.selectUserById(uid);
            model.addAttribute("user", userDTO);

            resultUser = userDTO;
            return "user/UserMyinfo";
        }else{
            return "redirect:/user/UserLogin";
        }

    }

    @ResponseBody
    @PostMapping("userInfo/UserMyinfo/{cate}")
    public ResponseEntity UserMyinfo(@RequestBody UserDTO userDTO) {

        log.info(userDTO.toString());
        UserDTO resultUser = userService.selectUserById(userDTO.getUserUid());

        if(userDTO.getUserPass()!=null && resultUser!=null){
            resultUser.setUserPass(userDTO.getUserPass());
            ResponseEntity result = userService.updateUserPass(resultUser);

            return result;
        }else if(resultUser!=null){
            userDTO.setUserPass(resultUser.getUserPass());
            userDTO.setUserRegip(resultUser.getUserRegip());

            ResponseEntity result = userService.updateUser(userDTO);
            return result;
        }
        return ResponseEntity.ok().body(false);
    }

    @ResponseBody
    @PostMapping("userInfo/UserMyinfoLeave")
    public ResponseEntity UserMyinfoLeave(@RequestBody String uid) {

        log.info(uid);
        ResponseEntity result = userService.leaveUser(uid);
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
