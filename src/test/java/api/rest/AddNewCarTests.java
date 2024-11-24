package api.rest;

import config.CarController;
import dto.CarDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static helper.RandomUtils.*;

public class AddNewCarTests extends CarController {

    @Test
    public void addNewCarPositiveTest(){
        CarDto car = CarDto.builder()
                .serialNumber("123-"+generatePhone(5))
                .manufacture("Aurus")
                .model("model")
                .year("2020")
                .fuel("Electric")
                .seats(5)
                .carClass("C")
                .pricePerDay(300)
                .city("Haifa")
                .build();
        Response response = addNewCar(car);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void addNewCarNegativeTest_wrongToken(){
        CarDto car = CarDto.builder()
                .serialNumber("123-"+generatePhone(5))
                .manufacture("Aurus")
                .model("model")
                .year("2020")
                .fuel("Electric")
                .seats(5)
                .carClass("C")
                .pricePerDay(300)
                .city("Haifa")
                .build();
        Response response = addNewCar(car, "wrong_token");
        Assert.assertEquals(response.getStatusCode(), 401);
    }
}
