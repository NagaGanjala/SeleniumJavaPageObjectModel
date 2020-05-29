/*
 * @author : Naga Ganjala
 */
package com.bank.base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.bank.testlisteners.WebEventListener;


public class BaseTest {
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static WebDriverWait wait;
	public static Logger log;
	public static String browserName;
	public  static EventFiringWebDriver edriver;
	public static WebEventListener eventListener;

	@BeforeSuite
	public static void initialization() {
		if (driver == null) {
			log = Logger.getLogger("devpinoyLogger");
			try {
				PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\java\\com\\bank\\config\\log4j.properties");
				BufferedReader br = new BufferedReader(new FileReader(
						System.getProperty("user.dir") + "\\src\\test\\java\\com\\bank\\config\\config.properties"));
				config.load(br);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				log.error(e.getMessage());
			}
			System.out.println("Browser Name which you provided:" + config.getProperty("browser"));
			if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", config.getProperty("chromepath"));
				driver = new ChromeDriver();
			} else if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", config.getProperty("iepath"));
				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver", config.getProperty("firefoxpath"));
				driver = new InternetExplorerDriver();
			}
			//Web Event Listener
			edriver = new EventFiringWebDriver(driver);
			// Now create object of EventListerHandler to register it with EventFiringWebDriver
			eventListener = new WebEventListener();
			edriver.register(eventListener);
			driver = edriver;
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(config.getProperty("pageLoadTimeout")),
					TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, Integer.parseInt(config.getProperty("webDriverWait")));
			
			driver.get(config.getProperty("baseURL"));
		}
	}
	public static boolean isAlertPresent() //user defined method created to check alert is present or not
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			log.debug(e);
			return false;
			
		}
		
	}
	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		log.debug("test execution completed !!!");
	}
}
