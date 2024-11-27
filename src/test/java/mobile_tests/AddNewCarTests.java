package mobile_tests;

import config.AppiumConfig;
import dto.CarDto;
import dto.UserDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import java.util.Random;

public class AddNewCarTests extends AppiumConfig {

    @BeforeMethod
    public void goToLoginScreen() {
        UserDto user = UserDto.builder()
                .username("0bagginsbob@mail.com")
                .password("Qwerty123!")
                .build();
        new SplashScreen(driver).goToFindCarScreen();
        new FindCarScreen(driver).goToLoginScreen();
        new LoginScreen(driver).typeLoginForm(user);
        new FindCarScreen(driver).goToMyCarScreen();
        new MyCarsScreen(driver).clickBtnAddCar();
    }

    @Test
    public void addNewCarPositiveTests(){
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("123456-" + i)
                .manufacture("Opel")
                .model("Astra")
                .year("2023")
                .fuel("Diesel")
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .about("about my car")
                .build();
        new AddNewCarScreen(driver).typeAddNewCarForm(car);
        new MyCarsScreen(driver).validateMessage("Car was added");
    }
}