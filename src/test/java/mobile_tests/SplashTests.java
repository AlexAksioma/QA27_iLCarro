package mobile_tests;

import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class SplashTests extends AppiumConfig {

    @Test
    public void validateVersionApp(){
        Assert.assertTrue(new SplashScreen(driver).validateVersionApp());
    }
}
