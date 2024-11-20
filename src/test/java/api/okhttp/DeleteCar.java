package api.okhttp;

import dto.CarDto;
import dto.CarsDto;
import dto.TokenDto;
import dto.UserDto;
import interfaces.BaseApi;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class DeleteCar implements BaseApi {
    TokenDto tokenDto;
    CarDto carDto;

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

    @BeforeMethod
    public void getCarId(){
        Request requestGet = new Request.Builder()
                .url(BASE_URL+GET_USER_CARS_PATH)
                .addHeader("Authorization", tokenDto.getAccessToken())
                .get()
                .build();
        Response responseGet;
        try {
            responseGet = OK_HTTP_CLIENT.newCall(requestGet).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(responseGet.isSuccessful()){
            try {
                CarsDto carsDto = GSON.fromJson(responseGet.body().string(), CarsDto.class);
                int randomCar = new Random().nextInt(carsDto.getCars().length);
                System.out.println("car number --> "+randomCar);
                carDto = carsDto.getCars()[randomCar];
                System.out.println("serial number --> "+carDto.getSerialNumber());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void deleteCarByIdPositiveTest(){
        Request request = new Request.Builder()
                .url(BASE_URL + DELETE_CAR_PATH+carDto.getSerialNumber())
                .addHeader("Authorization", tokenDto.getAccessToken())
                .delete()
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
