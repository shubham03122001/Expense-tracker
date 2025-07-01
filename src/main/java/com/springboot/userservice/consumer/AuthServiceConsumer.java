package com.springboot.userservice.consumer;


import com.springboot.userservice.entity.UserInfoDTO;
import com.springboot.userservice.repository.UserRepository;
import com.springboot.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//@RequiredArgsConstructor is a Lombok annotation used to automatically generate a constructor with required (final or @NonNull) fields.
public class AuthServiceConsumer {
//    private UserRepository userRepository;
    private  final UserService userService;


   // public AuthServiceConsumer(UserService userService){
//        this.userRepository = userRepository;
        //this.userService = userService;
  //  }

    //Hello this is shubham

    @KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void listen(UserInfoDTO eventData){
    try{
        //Todo: Make it transactional , to handle idempotency and validate emial, phoneNumber etc
        System.out.println("Consumed event :-"+eventData);
        userService.createOrUpdateUser(eventData);


    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("AuthserviceConsumer: Exception is throws while consuming kafka event ");
    }
    }

}
