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
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class AddNewCarTestsOkHttp implements BaseApi {

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
    public void addNewCarPositiveTest(){
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("12345-" + i)
                .manufacture("Opel")
                .model("Astra")
                .year("2023")
                .fuel("Diesel")
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(car), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+ADD_NEW_CAR_PATH)
                .addHeader("Authorization", tokenDto.getAccessToken())
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(response.isSuccessful());
    }

    @Test
    public void addNewCarPositiveTest_validateGetApi(){
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("12345-" + i)
                .manufacture("Opel")
                .model("Astra")
                .year("2023")
                .fuel("Diesel")
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(car), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+ADD_NEW_CAR_PATH)
                .addHeader("Authorization", tokenDto.getAccessToken())
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                Assert.assertTrue(Arrays.asList(carsDto.getCars()).contains(car));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
