package api.rest;

import config.AuthenticationController;
import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends AuthenticationController {

    @Test
    public void loginPositiveTest(){
        UserDto user = UserDto.builder()
                .username("0bagginsbob@mail.com")
                .password("Qwerty123!")
                .build();
        Assert.assertEquals(registrationLogin(user, LOGIN_PATH).getStatusCode(), 200);
    }
}
