package com.farmstory.service;

import com.farmstory.dto.UserDTO;
import com.farmstory.entity.User;
import com.farmstory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    //유저 정보 저장
    public void insertUser(UserDTO userDTO) {
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
    //선택한 유저 정보 삭제
    public void deleteUserById(String uid) {
        //Entity 삭제 (데이터베이스 Delete)
        userRepository.deleteById(uid);
    }
}
