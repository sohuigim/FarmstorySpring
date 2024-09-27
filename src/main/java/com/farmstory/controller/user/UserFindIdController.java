package com.farmstory.controller.user;

import com.farmstory.dto.UserDTO;
import com.farmstory.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.sound.midi.Receiver;

@Log4j2
@RequiredArgsConstructor
@Controller
public class UserFindIdController {

    private final UserService userService;

    @GetMapping("user/UserFindId")
    public String UserFindId(){
        return "user/UserFindId";
    }

    @PostMapping("user/UserFindId")
    public ResponseEntity UserFindId( HttpSession session,
                                      @RequestBody UserDTO userDTO){
        String receivedEmail = userDTO.getUserEmail();
        String receivedName = userDTO.getUserName();

        UserDTO user = userService.selectUserByEmail(receivedEmail);

        try{
            String name = user.getUserName();
            String email = user.getUserEmail();

            if(receivedName.equals(name) && receivedEmail.equals(email)){
                userService.sendEmailCode(session, receivedEmail);
                return ResponseEntity.ok().body(true);
            }else{
                return ResponseEntity.ok().body(false);
            }

        }catch (Exception e){
            return ResponseEntity.ok().body(false);
        }
    }
}
