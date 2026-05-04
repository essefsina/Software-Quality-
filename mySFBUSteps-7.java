package StepDefinition;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.junit.Ignore;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import week12.MyFirstJavaSeleniumLog4j;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class mySFBUSteps {

    WebDriver driver;
    Actions actions;
    String driverPath = "C:\\BrowserDrivers\\chrome\\chromedriver.exe";
//    String driverPath = "C:\\BrowserDrivers\\firefox\\geckodriver.exe";
    static String sfbuUrl = "https://sfbu.edu";
    String expectedTitle = "San Francisco Bay University | San Francisco Bay University";
    static String mySFBUApplicantUrl = "https://sfbu.my.site.com/admissions/s/login/SelfRegister?startURL=%2Fadmissions%2Fs%2F";
    static String xPathOfFirstName = "//*[@id=\"first_name_container-1\"]/input";
    static String xPathOfLastName = "//*[@id=\"last_name_container-1\"]/input";
    static String xPathOfBtnContinue = "//*[@id=\"continueButton-1\"]";
    static String xPathOfErrMsg = "//*[@id=\"centerPanel\"]/div/div[2]/div/div[2]/c-lightning-self-register-custom/span[1]";
    static String msgMissingAllFields = "Please complete all fields before continuing.";
    static String screenshotPath = "c://CS522Screenshots";

    private static Logger logger = LogManager.getLogger(mySFBUSteps.class);

    @Given("Open browser and maximize windows")
    public void open_browser_and_maximize_windows() {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.INFO);
        try {
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver();
//        driver = new FirefoxDriver();
            actions = new Actions(driver);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            logger.info("Step 1: Initiate web driver and maximized window");
            takeSnapShot(driver, screenshotPath + "//" + "test1.png") ;
        }catch(Throwable t){
            throw new io.cucumber.java.PendingException();
        }
    }


    @Then("^Logout$")
    public void logout(){
        try{
            driver.quit();
            logger.info("logout.");
        }catch (Throwable t){
            throw new io.cucumber.java.PendingException();
        }
    }

    @When("Go to SFBU website")
    public void go_to_sfbu_website() {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.INFO);
        Configurator.setRootLevel(Level.ERROR);
        driver.get(sfbuUrl);
        try {
            String actualTitle = driver.getTitle();
            try {
                Assert.assertEquals(expectedTitle, actualTitle);
                logger.info("found expected page title: " + actualTitle + " ----- Test Passed");
            }catch (Throwable t){
                logger.error("Not found expected page title: " + actualTitle + " ----- Test Failed");
            }
            logger.info("Step 2: Go to SFBU website.");
            takeSnapShot(driver, screenshotPath + "//" + "test2.png") ;
        }catch (Throwable t){
            throw new io.cucumber.java.PendingException();
        }
    }

    @When("^Navigate to Self Register$")
    public void navigate_to_self_register() throws Throwable {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.INFO);
        try {
            driver.navigate().to(mySFBUApplicantUrl);
            WebElement res = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions
                    .elementToBeClickable(By.xpath(xPathOfBtnContinue)));
            if (res.isDisplayed()) {
                logger.info("Step 3: Navigated from My SFBU - Applicant to open " + driver.getTitle());
            }
            logger.info("Step 3: Navigate to Self Register.");
            takeSnapShot(driver, screenshotPath + "//" + "test3.png") ;
        }catch (Throwable t){
            throw new io.cucumber.java.PendingException();
        }
    }

    @When("Enter Self Register First Name as {string} and Last Name as {string}")
    public void enter_self_register_first_name_as_and_last_name_as(String firstName, String lastName) {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.INFO);
        try {
			// Enter first name and last name

        }catch (Throwable t){
            throw new io.cucumber.java.PendingException();
        }
    }

    @When("^Try to Continue$")
    public void try_to_continue() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.INFO);
        try{
			// click Continue button
			
        }catch (Throwable t){
            throw new io.cucumber.java.PendingException();
        }
        logger.info("Step 5: clicked Try to Continue button.");
        takeSnapShot(driver, screenshotPath + "//" + "test5.png") ;
    }

    @Then("^Self Register is failed with message \"([^\"]*)\"$")
    public void self_register_is_failed_with_message(String string) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.INFO);
        Configurator.setRootLevel(Level.ERROR);
        try {
            // sample a
            boolean b1 = driver.getPageSource().contains(msgMissingAllFields);
            if (b1){
                logger.info("Step 6a: using boolean if else and Found expected message: " + msgMissingAllFields + " ----- Test Passed!");
            } else {
                logger.error("Step 6a: using boolean if else and Not Found expected message: " + msgMissingAllFields + " ----- Test Failed");
            }

            // sample b
            boolean b2 = driver.getPageSource().contains(msgMissingAllFields);
            try {
                Assert.assertTrue(b2);
                logger.info("Step 6b: using getPageSource() found expected message on the page.");
            }catch (Throwable t){
                logger.error("Step 6b: using getPageSource() doesn't find expected message on the page.");
                throw t;     // rethrowing the exception
            }

            // sample c
            String actualMsg = driver.findElement(By.xpath(xPathOfErrMsg)).getText();
            try{
                Assert.assertEquals(msgMissingAllFields, actualMsg);
                logger.info("Step 6c: using getText() found expected message on the page.");
            }catch (Exception e){
                logger.error("Step 6c: using getText() doesn't find expected message on the page.");
                throw e;     // rethrowing the exception
            }
        }catch (Throwable t){
            throw new io.cucumber.java.PendingException();
        }
    }

    public void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception {
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination
        File DestFile=new File(fileWithPath);

        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }
}