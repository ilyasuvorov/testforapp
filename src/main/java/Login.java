import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Login {
    AndroidDriver androidDriver = null;
    private static final long ADDITIONAL_TIME = 2000;
    private static final String EXPECTED_APPNAME = "Test Application";

    @Test(priority = 1)
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

    @Test(priority = 2, description = "Registration")
    public void testCaseZero(){
        WebElement logUp = findElement(By.id("btn2"));
        logUp.click();
        sleep(1000);
        enterLogAndPassInRegister();
        tryRegister();
        sleep(1000);
        System.out.println("Registration");
        System.out.println("Test[testCaseZero]: passed");
    }

    @Test(priority = 3,
            description = "Login with valid user data,open settings,go back to devices,logout")
    public void testCaseOne () {
        logOut();
        enterLogAndPassInLogIn("testlogin@gmail.com","112233");
        tryClickLogIn();
        navigateToSettingsScreen();
        navigateToDevicesFromSettings();
        navigateToSettingsScreen();
        tryClickLogOut();
        System.out.println("Login with valid user data,open settings,go back to devices,logout");
        System.out.println("Test[testCaseOne]: passed");
    }

    @Test(priority = 4,
            description = "Try login with non-gmail user data,assert that is not possible")
    public void testCaseTwo() {
        logOut();
        enterLogAndPassInLogIn("testlogin","112233");
        tryClickLogIn();
        Assert.assertNull(findElement(By.id("imageView")));
        System.out.println("Try login without gmail");
        System.out.println("Test[testCaseTWo]: passed");
    }

    @Test(priority = 5,
            description = "Try login with empty user data,assert error that it is not possible")
    public void testCaseThree(){
        logOut();
        enterLogAndPassInLogIn("","");
        tryClickLogIn();
        Assert.assertNull(findElement(By.id("imageView")));
        System.out.println("Try login with empty user data");
        System.out.println("Test[testCaseThree]: passed");
    }

    @Test(priority = 6,
            description = "Login with valid user data,click on each picture and make sure,nothing happens")
    public void testCaseFour(){
        logOut();
        enterLogAndPassInLogIn("testlogin@gmail.com","112233");
        tryClickLogIn();

        String pageSourceBefore = androidDriver.getPageSource();

        WebElement imageOne = findElement(By.id("imageView"));
        imageOne.click();
        String pageSourceAfterClickOnImageOne = androidDriver.getPageSource();

        WebElement imageTwo = findElement(By.id("imageView2"));
        imageTwo.click();
        String pageSourceAfterClickOnImageTwo = androidDriver.getPageSource();

        WebElement imageThree = findElement(By.id("imageView3"));
        imageThree.click();
        String pageSourceAfterClickOnImageThree = androidDriver.getPageSource();
        Assert.assertTrue((pageSourceBefore.equals(pageSourceAfterClickOnImageOne))
                && (pageSourceBefore.equals(pageSourceAfterClickOnImageTwo))
                && (pageSourceBefore.equals(pageSourceAfterClickOnImageThree)));
        System.out.println("Login and try to click on images");
        System.out.println("Test[testCaseFour]: passed");
    }

    @Test(priority = 7, description = "Open login screen and check that application name is valid")
    public void testCaseFive(){
        logOut();
        Assert.assertTrue(findElement(By.id("textview")).getAttribute("text").equals(EXPECTED_APPNAME));
        System.out.println("Check that application name is valid");
        System.out.println("Test[testCaseFive]: passed");
    }

    private void enterLogAndPassInRegister(){
        WebElement enterLogin = findElement(By.id("activity_register_editText"));
        enterLogin.sendKeys("testlogin@gmail.com");
        WebElement enterPass = findElement(By.id("activity_register_editText2"));
        enterPass.sendKeys("112233");
    }

    private void enterLogAndPassInLogIn(String login,String password){
        WebElement enterLogin = findElement(By.id("editText"));
        enterLogin.sendKeys(login);
        WebElement enterPass = findElement(By.id("editText2"));
        enterPass.sendKeys(password);
    }

    private void navigateToSettingsScreen(){
        WebElement settingsScreen = findElement(By.id("btn2"));
        settingsScreen.click();
        sleep(1000);
    }

    private void navigateToDevicesFromSettings (){
        WebElement backToDevicesButton = findElement(By.id("btn3"));
        backToDevicesButton.click();
        sleep(1000);
    }

    private void tryClickLogOut(){
        WebElement logOutButton = findElement(By.id("btn4"));
        if(logOutButton != null){
            logOutButton.click();
        }
        sleep(1000);
    }

    private void tryClickLogIn(){
        WebElement logIn = findElement(By.id("btn1"));
        if (logIn != null) {
            logIn.click();
        }
        sleep(1000);
    }

    private void tryRegister(){
        WebElement logInAfterRegistrarion = findElement(By.id("activity_register_btn1"));
        logInAfterRegistrarion.click();
        sleep(1000);
        WebElement check = findElement(By.id("btn2"));
        Assert.assertNotNull(check);
    }

    private void logOut(){
        WebElement enterLogin = findElement(By.id("editText"));
        if (enterLogin == null) {
            navigateToSettingsScreen();
            tryClickLogOut();
        }
    }

    private void sleep(long sleepValue){
        try {
            Thread.sleep(sleepValue + ADDITIONAL_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private WebElement findElement(By byValue){
        try {
            return androidDriver.findElement(byValue);
        } catch (Exception e){
            return null;
        }
    }

    @AfterTest
    public void afterTest(){
        androidDriver.quit();
    }
}