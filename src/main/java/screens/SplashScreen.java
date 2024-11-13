package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SplashScreen extends BaseScreen {
    public SplashScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/versionText")
    AndroidElement versionApp;

    public boolean validateVersionApp() {
        return textInElementPresent(versionApp, "", 5);
    }

    public boolean validateSplashScreenToDisappear(long expectedTime) {
        long starTime = System.currentTimeMillis();
        long endTime = 0;
        if (new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOf(versionApp)))
            endTime = System.currentTimeMillis();
        long splashScreenDuration = endTime - starTime;
        System.out.println(splashScreenDuration);
        if (splashScreenDuration <= expectedTime)
            return true;
        else return false;

    }

    public void goToFindCarScreen() {
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("com.telran.ilcarro:id/findTitle")));
        }catch (TimeoutException e){
            e.printStackTrace();
            System.out.println("smth wrong, created exception");
        }

    }
}
