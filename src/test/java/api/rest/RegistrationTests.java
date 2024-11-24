package api.rest;

import config.AuthenticationController;
import dto.ErrorMessageDtoString;
import dto.UserDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static helper.RandomUtils.*;

public class RegistrationTests extends AuthenticationController {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void registrationPositiveTest(){
        UserDto user = UserDto.builder()
                .username(generateEmail(10))
                .password("Qwerty123!")
                .firstName(generateString(5))
                .lastName(generateString(8))
                .build();
        Assert.assertEquals(registrationLogin(user, REGISTRATION_PATH).getStatusCode(), 200);
    }

    @Test
    public void registrationNegativeTest_wrongEmail(){
        UserDto user = UserDto.builder()
                .username(generateString(10))
                .password("Qwerty123!")
                .firstName(generateString(5))
                .lastName(generateString(8))
                .build();
        Response response = registrationLogin(user, REGISTRATION_PATH);
        System.out.println(response.print());
        softAssert.assertEquals(response.getStatusCode(), 400);
        ErrorMessageDtoString errorMessage = response.getBody().as(ErrorMessageDtoString.class);
        softAssert.assertEquals(errorMessage.getError(), "Bad Request");
        softAssert.assertTrue(errorMessage.getMessage().toString().contains("must be a well-formed email address"));
        softAssert.assertAll();
    }
}
