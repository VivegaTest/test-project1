package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop = new Properties();
    public static Properties locator = new Properties();
    private final String propertyFilePath = "src//main//java//com//config//Config.properties";
    private final String locatorsFilePath = "src//main//java//com//config//locators.properties";

    BufferedReader reader;
    BufferedReader reader1;


    public TestBase() {
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            reader1 = new BufferedReader(new FileReader(locatorsFilePath));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        try {
            prop.load(reader);
            locator.load(reader1);
            reader.close();
            reader1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization() {
        String browserName = prop.getProperty("browser");

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/selenium/drivers/chromedriver.exe");
            driver = new ChromeDriver();
            ChromeOptions options = getChromeOptions();
            options.addArguments("--remote-allow-origins=*");
        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/selenium/drivers/geckodriver");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.get(prop.getProperty("URL"));
    }

    static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--enable-screenshot-testing-with-mode");
        //             Added below argument due to the 111 chrome driver issue for temporary
        options.addArguments("--disable-password-generation");
        return options;
    }

    public void closeBrowser() {
        driver.quit();
    }
}