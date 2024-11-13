package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString

public class UserDto {
//    "username": "string",
//            "password": "#$ZNHHt>86J),=UU} lk8<j:R6KA<+6e$f",
//            "firstName": "string",
//            "lastName": "string"
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
