package config;

import dto.CarDto;
import dto.TokenDto;
import dto.UserDto;
import interfaces.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;

public class CarController implements BaseApi {

    TokenDto token;

    @BeforeSuite
    public void login() {
        UserDto user = UserDto.builder()
                .username("0bagginsbob@mail.com")
                .password("Qwerty123!")
                .build();
        Response response = given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + LOGIN_PATH)
                .thenReturn();
        if (response.getStatusCode() == 200) {
            token = response.getBody().as(TokenDto.class);
            System.out.println(token);
        }
    }

    protected Response addNewCar(CarDto car) {
        return given()
                .body(car)
                .contentType(ContentType.JSON)
                .header("Authorization", token.getAccessToken())
                .when()
                .post(BASE_URL + ADD_NEW_CAR_PATH)
                .thenReturn();
    }

    protected Response addNewCar(CarDto car, String token) {
        return given()
                .body(car)
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .post(BASE_URL + ADD_NEW_CAR_PATH)
                .thenReturn();
    }

    protected Response deleteCar(String carId) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", token.getAccessToken())
                .when()
                .delete(BASE_URL + DELETE_CAR_PATH + carId)
                .thenReturn();
    }

}
