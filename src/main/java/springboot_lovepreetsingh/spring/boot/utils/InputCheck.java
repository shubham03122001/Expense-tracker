package springboot_lovepreetsingh.spring.boot.utils;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

public  class InputCheck {



    //REGEX for email validation
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    //REGEX for password
    // - At least 8 characters
    // - At least 1 digit
    // - At least 1 special character
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/]).{8,}$";




    public boolean checkEmail(String email){
       return Pattern.matches(EMAIL_REGEX,email);


    }

    public static boolean checkPassword(String password){
        return Pattern.matches(PASSWORD_REGEX,password);

    }


    public boolean isEmailAndPasswordCorrect(String email , String password){
        return checkEmail(email) && checkPassword(password);
    }

}
