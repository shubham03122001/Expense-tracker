package springboot_lovepreetsingh.spring.boot.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import springboot_lovepreetsingh.spring.boot.entities.UserInfo;
import springboot_lovepreetsingh.spring.boot.eventProducer.UseInfoEvent;
import springboot_lovepreetsingh.spring.boot.eventProducer.UserInfoProducer;
import springboot_lovepreetsingh.spring.boot.model.UserInfoDto;
import springboot_lovepreetsingh.spring.boot.repository.UserInfoRepository;
import springboot_lovepreetsingh.spring.boot.utils.InputCheck;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@Component
@AllArgsConstructor
@Data


public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserInfoProducer userInfoProducer;

//    @Autowired
//    private InputCheck inputCheck;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userInfoRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Could not found user!!!");
        }

        return new CustomUserDetails(user);


    }

    public UserInfo checkIfUserAlreadyExist(UserInfoDto userInfoDto){
        return userInfoRepository.findByUsername(userInfoDto.getUsername());
    }

    public Boolean signupUser(UserInfoDto userInfoDto){

        if(InputCheck.checkPassword(userInfoDto.getPassword())){
            //Define a function to check if userEmail, password is correct
            userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
            if(Objects.nonNull(checkIfUserAlreadyExist(userInfoDto))){
                return false;
            }
            String userId = UUID.randomUUID().toString();
            userInfoRepository.save(new UserInfo(userId,userInfoDto.getUsername(),userInfoDto.getPassword(),new HashSet<>()));
            userInfoProducer.sendEventToKafka(userInfoEventToPublish(userInfoDto,userId));
            return true;

        }else {

            System.out.println("Password does not meet the criteria");
            return false;


        }









    }
    private UseInfoEvent userInfoEventToPublish(UserInfoDto userInfoDto, String userId){
        return UseInfoEvent.builder()
                .userId(userId)
                .firstName(userInfoDto.getUsername())
                .lastName(userInfoDto.getLastName())
                .email(userInfoDto.getEmail())
                .phoneNumber(userInfoDto.getPhoneNumber()).build();

    }


}
