package mobile_tests;

import config.AppiumConfig;
import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.FindCarScreen;
import screens.RegistrationScreen;
import screens.SplashScreen;

import static helper.RandomUtils.*;

public class RegistrationTests extends AppiumConfig {

    RegistrationScreen registrationScreen;
    @BeforeMethod
    public void goToRegScreen(){
        new SplashScreen(driver).goToFindCarScreen();
        new FindCarScreen(driver).goToRegistrationScreen();
        registrationScreen = new RegistrationScreen(driver);
    }

    @Test
    public void registrationPositiveTest(){
        UserDto user = UserDto.builder()
                .firstName(generateString(5))
                .lastName(generateString(10))
                .username(generateEmail(10))
                .password("Qwerty123!")
                .build();
        registrationScreen.typeRegForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnYalla();
        Assert.assertTrue(new FindCarScreen(driver).validateMessageSuccess());
    }
    @Test
    public void registrationNegativeTest_wrongEmail(){
        UserDto user = UserDto.builder()
                .firstName(generateString(5))
                .lastName(generateString(10))
                .username(generateString(10))
                .password("Qwerty123!")
                .build();
        registrationScreen.typeRegForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnYalla();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("must be a well-formed email address", 5));
    }

    @Test
    public void registrationNegativeTest_wrongPassword(){
        UserDto user = UserDto.builder()
                .firstName(generateString(5))
                .lastName(generateString(10))
                .username(generateEmail(10))
                .password("qwerty123!")
                .build();
        registrationScreen.typeRegForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnYalla();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("At least 8 characters; Must contain at least 1 uppercase letter,", 5));
    }
}
