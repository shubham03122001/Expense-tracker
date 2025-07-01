package springboot_lovepreetsingh.spring.boot.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import springboot_lovepreetsingh.spring.boot.entities.UserInfo;


@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserInfoDto extends UserInfo {

    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String email;

}
