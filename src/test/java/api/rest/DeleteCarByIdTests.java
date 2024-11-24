package api.rest;

import config.CarController;
import dto.CarDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static helper.RandomUtils.generatePhone;

public class DeleteCarByIdTests extends CarController {

    CarDto car;

    @BeforeMethod
    public void addNewCar(){
        car = CarDto.builder()
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
        if (response.getStatusCode() == 200){
            System.out.println("successful add new car ser number --> "+car.getSerialNumber());
        }
    }
    @Test
    public void deleteCarPositiveTest(){
        Response response = deleteCar(car.getSerialNumber());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
