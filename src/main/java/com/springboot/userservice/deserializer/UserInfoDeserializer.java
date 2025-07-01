package com.springboot.userservice.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.userservice.entity.UserInfoDTO;
import org.apache.catalina.User;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class UserInfoDeserializer implements Deserializer<UserInfoDTO> {


    @Override
    public void configure(Map<String,?> configs,boolean key){

    }

    @Override
    public  UserInfoDTO deserialize(String argument0 , byte[] argument1 ){
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfoDTO userInfoDTO = null;
        try{

            userInfoDTO = objectMapper.readValue(argument1,UserInfoDTO.class);


        } catch (Exception e) {
            System.out.println("Cannot deserialize"+e);
            e.printStackTrace();
        }

        return userInfoDTO;
    }

}
