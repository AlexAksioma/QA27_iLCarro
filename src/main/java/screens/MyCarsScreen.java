package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class MyCarsScreen extends BaseScreen{
    public MyCarsScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/addCarBtn")
    AndroidElement btnAddCar;
    @FindBy(xpath = "/hierarchy/android.widget.Toast")
    AndroidElement message;

    public void clickBtnAddCar(){
        btnAddCar.click();
    }

    public boolean validateMessage(String text){
        return textInElementPresent(message, text, 5);
    }
}
