package springboot_lovepreetsingh.spring.boot.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import springboot_lovepreetsingh.spring.boot.eventProducer.UseInfoEvent;
import springboot_lovepreetsingh.spring.boot.model.UserInfoDto;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class UserInfoSerializer implements Serializer<UseInfoEvent> {
//    @Override
//    public  void configure(Map<String ,UserInfoDto> configs ,boolean isKey){
//
//
//    }
    @Override
    public byte[] serialize(String key , UseInfoEvent value){
        byte[] returnValue=null;
        ObjectMapper objectMapper = new ObjectMapper();
         try{
             returnValue  = objectMapper.writeValueAsString(value).getBytes(StandardCharsets.UTF_8);

         } catch (Exception e) {
             e.printStackTrace();
         }

         return returnValue;

    }



}
