package com.farmstory.service;

import com.farmstory.dto.UserDTO;
import com.farmstory.entity.User;
import com.farmstory.repository.UserRepository;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


@Log4j2
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final ModelMapper modelMapper;


    //유저 정보 저장
    public void insertUser(UserDTO userDTO) {

        String encoded = passwordEncoder.encode(userDTO.getUserPass());
        userDTO.setUserPass(encoded);
        User entity = userDTO.toEntity();
        userRepository.save(entity);
    }

    //유저 정보 전체 가져오기
    public List<UserDTO> selectUsers(){

        List<User> userall = userRepository.findAll();

        List<UserDTO> users = userall
                .stream()
                .map(entity -> entity.toDTO())
                .collect(Collectors.toList());
        return users;
    }
    //원하는 유저
    public UserDTO selectUserById(String uid) {
        Optional<User> opt = userRepository.findById(uid);
        if(opt.isPresent()) {
            User user = opt.get();
            return user.toDTO();
        }
        return null;
    }

    public UserDTO selectUserByEmail(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail);

        if(user!=null) {
            return modelMapper.map(user, UserDTO.class);
        }
        return null;
    }

    //선택한 유저 정보 삭제
    public void deleteUserById(String uid) {
        //Entity 삭제 (데이터베이스 Delete)
        userRepository.deleteById(uid);
    }

    public int selectCountUser(String type, String value){

        int count = 0;

        if(type.equals("uid")){
            count = userRepository.countByUserUid(value);
        }else if(type.equals("nick")){
            count = userRepository.countByUserNick(value);
        }else if(type.equals("hp")){
            count = userRepository.countByUserHp(value);
        }else if(type.equals("email")){
            count = userRepository.countByUserEmail(value);
        }
        return count;
    }

    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmailCode(HttpSession session, String receiver){


        // MimeMessage 생성
        MimeMessage message = mailSender.createMimeMessage();

        // 인증코드 생성 후 세션 저장
        int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
        session.setAttribute("code", String.valueOf(code));


        String title = "sboard 인증코드 입니다.";
        String content = "<h1>인증코드는 " + code + "입니다.</h1>";

        try {
            message.setFrom(new InternetAddress(sender, "보내는 사람", "UTF-8"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject(title);
            message.setContent(content, "text/html;charset=UTF-8");

            // 메일 발송
            mailSender.send(message);
        }catch(Exception e){
            log.error("sendEmailConde : " + e.getMessage());
        }
    }
}
