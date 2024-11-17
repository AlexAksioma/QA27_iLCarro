package api.okhttp;

import dto.ErrorMessageDtoString;
import dto.UserDto;
import interfaces.BaseApi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static helper.RandomUtils.generateString;

public class LoginTests implements BaseApi {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void loginPositiveTest(){
        UserDto user = UserDto.builder()
                .username("0bagginsbob@mail.com")
                .password("Qwerty123!")
                .build();
        RequestBody requestBody  = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+LOGIN_PATH)
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
    public void loginNegativeTest_wrongPassword(){
        UserDto user = UserDto.builder()
                .username("0bagginsbob@mail.com")
                .password("Qwerty123")
                .build();
        RequestBody requestBody  = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+LOGIN_PATH)
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
        softAssert.assertEquals(errorMessageDtoString.getStatus(), 401);
        softAssert.assertTrue(errorMessageDtoString.getError().equals("Unauthorized"));
        softAssert.assertTrue(errorMessageDtoString.getMessage().toString().equals("Login or Password incorrect"));
        softAssert.assertAll();
    }
    @Test
    public void loginNegativeTest_wrongEmail(){
        UserDto user = UserDto.builder()
                .username("0bagginsbobmail.com")
                .password("Qwerty123!")
                .build();
        RequestBody requestBody  = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+LOGIN_PATH)
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
        softAssert.assertEquals(errorMessageDtoString.getStatus(), 401);
        softAssert.assertTrue(errorMessageDtoString.getError().equals("Unauthorized"));
        softAssert.assertTrue(errorMessageDtoString.getMessage().toString().equals("Login or Password incorrect"));
        softAssert.assertAll();
    }
}
