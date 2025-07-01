package com.springboot.userservice.service;

import com.springboot.userservice.entity.UserInfo;
import com.springboot.userservice.entity.UserInfoDTO;
import com.springboot.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserInfoDTO createOrUpdateUser(UserInfoDTO userInfoDTO){
      UnaryOperator<UserInfo> updatingUser=user->{
            return  userRepository.save(userInfoDTO.transformToUserInfo());
    };

        Supplier<UserInfo> createUser =()->{
        return userRepository.save(userInfoDTO.transformToUserInfo());
        };

        UserInfo userInfo = userRepository.findByUserId(userInfoDTO.getUserId())
                .map(updatingUser)
                .orElseGet(createUser);
       // userInfo.setUserId("123");


        return new UserInfoDTO(
                userInfo.getUserId(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPhoneNumber(),
                userInfo.getEmail(),
                userInfo.getProfilePic()
        );
    }

    public UserInfoDTO getUser(UserInfoDTO userInfoDTO) throws Exception{
        Optional<UserInfo> userInfoDtoOption = userRepository.findByUserId(userInfoDTO.getUserId());
        if(userInfoDtoOption.isEmpty()){
            throw new Exception("User not found");
        }
        UserInfo userInfo = userInfoDtoOption.get();

        return new UserInfoDTO(
                userInfo.getUserId(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPhoneNumber(),
                userInfo.getEmail(),
                userInfo.getProfilePic()
        );




    }
    public UserInfoDTO getUserById(String id) throws Exception{

        Optional<UserInfo> byUserId = userRepository.findByUserId(id);
        if(byUserId.isEmpty()){
            throw new Exception("User not found");
        }
        UserInfo userInfo = byUserId.get();

        return new UserInfoDTO(
                userInfo.getUserId(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPhoneNumber(),
                userInfo.getEmail(),
                userInfo.getProfilePic()
        );

    }



}
