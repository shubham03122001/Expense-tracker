package springboot_lovepreetsingh.spring.boot.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springboot_lovepreetsingh.spring.boot.entities.RefreshToken;
import springboot_lovepreetsingh.spring.boot.model.UserInfoDto;
import springboot_lovepreetsingh.spring.boot.response.JwtResponseDTO;
import springboot_lovepreetsingh.spring.boot.service.JwtService;
import springboot_lovepreetsingh.spring.boot.service.RefreshTokenService;
import springboot_lovepreetsingh.spring.boot.service.UserDetailsServiceImpl;

@RestController
@AllArgsConstructor
public class AuthController {


    @Autowired
    private JwtService jwtService;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("auth/v1/signup")
    public ResponseEntity<?> signUp(@RequestBody UserInfoDto userInfoDto){
        try{
            Boolean isSignup = userDetailsService.signupUser(userInfoDto);
            if(Boolean.FALSE.equals(isSignup)){
                return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
            }
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUsername());

            String jwtToken = jwtService.generateToken(userInfoDto.getUsername());

            return new ResponseEntity<>(JwtResponseDTO.builder().accessToken(jwtToken).token(refreshToken.getToken()).build(),HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Exception in User Service",HttpStatus.INTERNAL_SERVER_ERROR);

        }




    }

}
