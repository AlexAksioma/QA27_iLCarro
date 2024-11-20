package api.okhttp;

import dto.TokenDto;
import dto.UserDto;
import interfaces.BaseApi;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserCars implements BaseApi {
    TokenDto tokenDto;

    @BeforeClass
    public void login(){
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
        if(response.isSuccessful()){
            try {
                tokenDto = GSON.fromJson(response.body().string(), TokenDto.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(tokenDto);
    }

    @Test
    public void getUserCarsPositiveTest(){
        Request request = new Request.Builder()
                .url(BASE_URL+GET_USER_CARS_PATH)
                .addHeader("Authorization", tokenDto.getAccessToken())
                .get()
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(response.isSuccessful());
    }
}
