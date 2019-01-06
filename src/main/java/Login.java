import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Login {
    AndroidDriver androidDriver = null;
    @Test
    public void initializeTest(){
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"7.1.1");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Mi Note 3");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
            desiredCapabilities.setCapability(MobileCapabilityType.APP,"C:\\Users\\bober\\AndroidStudioProjects\\MyApplication2\\app\\build\\outputs\\apk\\debug\\app-debug.apk");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.example.bober.myapplication");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY,"com.example.bober.myapplication.LoginActivity");
            androidDriver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void register(){
        WebElement logUp = androidDriver.findElement(By.id("btn2"));
        logUp.click();
        sleep(1000);}
    @Test
    public void enterLogAndPss(){
        WebElement enterLogin = androidDriver.findElement(By.id("activity_register_editText"));
        enterLogin.sendKeys("boberwithyou@gmail.com");
        WebElement enterPass = androidDriver.findElement(By.id("activity_register_editText2"));
        enterPass.sendKeys("228322");
    }
    @Test
    public void login(){
        WebElement logInAfterRegistrarion = androidDriver.findElement(By.id("activity_register_btn1"));
        logInAfterRegistrarion.click();
        sleep(1000);
        WebElement check = androidDriver.findElement(By.id("btnSettings"));
        Assert.assertFalse(check == null);
    }
    @Test
    public void settings () {
        WebElement goToSettings = androidDriver.findElement(By.id("btnSet"));
        goToSettings.click();
        sleep(1000);
    }
    @Test
    public void logOut (){
        WebElement logOut = androidDriver.findElement(By.id("btn4"));
        logOut.click();
    }
    @Test
    public void tryLoginWithoutGmail() {
        WebElement enterLoginWithOutGmail = androidDriver.findElement(By.id("editText"));
        enterLoginWithOutGmail.sendKeys("boberwithyou");
        WebElement enterPass = androidDriver.findElement(By.id("activity_register_editText2"));
        enterPass.sendKeys("228322");
    }
    @Test
    public void tryLoginWithEmptyData(){

    }
    @Test
    public void clickOnImage(){
        WebElement enterLogin = androidDriver.findElement(By.id("editText"));
        enterLogin.sendKeys("boberwithyou@gmail.com");
        WebElement enterPass = androidDriver.findElement(By.id("editText2"));
        enterPass.sendKeys("228322");
        WebElement logIn = androidDriver.findElement(By.id("btn1"));
        logIn.click();
        sleep(1000);
        WebElement clickOnImages = androidDriver.findElement(By.id("imageView"));
        clickOnImages.click();

    }
    public void sleep(long sleepValue){
        try {
            Thread.sleep(sleepValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
