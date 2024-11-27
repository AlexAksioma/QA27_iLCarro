package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class FindCarScreen extends BaseScreen{
    public FindCarScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    AndroidElement btnMoreOptions;

    @FindBy(xpath = "//*[@text='Registration']")
    AndroidElement btnRegistration;
    @FindBy(xpath = "//*[@text='Login']")
    AndroidElement btnLogin;
    @FindBy(xpath = "//*[@text='My Cars']")
    AndroidElement btnMyCars;
    @FindBy(xpath = "/hierarchy/android.widget.Toast")  //	/hierarchy/android.widget.Toast
    AndroidElement popUpMessageSuccess;

    public void goToRegistrationScreen(){
        //pause(3);
        btnMoreOptions.click();
        btnRegistration.click();
    }
    public void goToLoginScreen(){
        //pause(3);
        btnMoreOptions.click();
        btnLogin.click();
    }
    public void goToMyCarScreen(){
        //pause(3);
        btnMoreOptions.click();
        btnMyCars.click();
    }

    public boolean validateMessageSuccess(){
        return textInElementPresent(popUpMessageSuccess, "Registration success!", 5);
    }
    public boolean validateMessageSuccess(String message){
        return textInElementPresent(popUpMessageSuccess, message, 5);
    }

}
