package com.springboot.userservice.controller;


import com.springboot.userservice.entity.UserInfoDTO;
import com.springboot.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/v1/getuser/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id){
        try{

            UserInfoDTO userById = userService.getUserById(id);

            return new ResponseEntity<>(userById , HttpStatus.OK);


        } catch (Exception e) {
            System.out.println("Exception occured while getting user"+e);
            return new ResponseEntity<>("Exception Occured ", HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PostMapping("/user/v1/createUpdate")
    public ResponseEntity<UserInfoDTO> createUpdateUser(@RequestBody UserInfoDTO userInfoDTO){
        try {
            UserInfoDTO User = userService.createOrUpdateUser(userInfoDTO);
            return new ResponseEntity<>(User,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Boolean> checkHealth(){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<?> checkingEndpoint(){
        return new ResponseEntity<>("Hello there shubham!!!Long way to goo!!!",HttpStatus.OK);

    }

}
