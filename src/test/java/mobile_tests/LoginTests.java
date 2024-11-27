package mobile_tests;

import config.AppiumConfig;
import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.FindCarScreen;
import screens.LoginScreen;
import screens.RegistrationScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {

    LoginScreen loginScreen;

    @BeforeMethod
    public void goToLoginScreen(){
        new SplashScreen(driver).goToFindCarScreen();
        new FindCarScreen(driver).goToLoginScreen();
        loginScreen = new LoginScreen(driver);
    }

    @Test
    public void loginPositiveTests(){
        UserDto user = UserDto.builder()
                .username("0bagginsbob@mail.com")
                .password("Qwerty123!")
                .build();
        loginScreen.typeLoginForm(user);
        Assert.assertTrue(new FindCarScreen(driver).validateMessageSuccess("Login success!"));
    }
}
