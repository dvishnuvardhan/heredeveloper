package com.heredeveloper.stepdefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.heredeveloper.generic.ObjectPropertyConnection;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static ObjectPropertyConnection opc;

	@Before
	public void openBrowser() throws Exception {
		opc = new ObjectPropertyConnection();
		String browserName = opc.getObjectRepository("Config.properties").getProperty("browser");
		System.out.println("User selected browser as :: " + browserName);
		try {
			if (browserName.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "/Drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(browserName + " :: Browser has been launched");
		driver.manage().window().maximize();

	}

	@After
	public void exitBrowser() {
		driver.quit();
	}
}
