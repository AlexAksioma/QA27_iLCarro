package api.okhttp;

import dto.ErrorMessageDtoString;
import dto.UserDto;
import interfaces.BaseApi;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static helper.RandomUtils.*;

public class RegistrationTestsOkHttp implements BaseApi {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void registrationPositiveTest(){
        UserDto user = UserDto.builder()
                .username(generateEmail(12))
                .password("Qwerty123!")
                .firstName(generateString(5))
                .lastName(generateString(10))
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_PATH)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(response.isSuccessful());
    }

    @Test
    public void registrationNegativeTest_wrongEmail(){
        UserDto user = UserDto.builder()
                .username(generateString(12))
                .password("Qwerty123!")
                .firstName(generateString(5))
                .lastName(generateString(10))
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_PATH)
                .post(requestBody)
                .build();
        Response response;
        String responseJson;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            System.out.println(response);
            responseJson = response.body().string();
            System.out.println(responseJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ErrorMessageDtoString errorMessageDtoString = GSON.fromJson(responseJson, ErrorMessageDtoString.class);
        softAssert.assertEquals(errorMessageDtoString.getStatus(), 400);
        softAssert.assertEquals(errorMessageDtoString.getError(), "Bad Request");
        softAssert.assertTrue(errorMessageDtoString.getMessage().toString().contains("must be a well-formed email address"));
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_DuplicateUser(){
        UserDto user = UserDto.builder()
                .username("0bagginsbob@mail.com")
                .password("Qwerty123!")
                .firstName(generateString(5))
                .lastName(generateString(10))
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_PATH)
                .post(requestBody)
                .build();
        Response response;
        String responseJson;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            System.out.println(response);
            responseJson = response.body().string();
            System.out.println(responseJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ErrorMessageDtoString errorMessageDtoString = GSON.fromJson(responseJson, ErrorMessageDtoString.class);
        softAssert.assertEquals(errorMessageDtoString.getStatus(), 400);
        softAssert.assertEquals(errorMessageDtoString.getError(), "Bad Request");
        softAssert.assertTrue(errorMessageDtoString.getMessage().toString().contains("User already exists"));
        softAssert.assertAll();
    }
}
