package com.auto.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.auto.configProperties.ConfigProperties;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCore extends Page {

	static DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
	static Date date = new Date();
	public static Properties object = new Properties();
	public static Properties config = new Properties();
	public static WebDriver driver;
	public static String SCREENSHOT_FOLDER = "target/screenshots/";
	public static final String SCREENSHOT_FORMAT = ".png";
	private String testUrl;
	private String targetBrowser;
	private String os;
	public static ExtentTest test;
	public static ExtentReports report;

	/**
	 * WebDriver initialization
	 *
	 * @return WebDriver object
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@BeforeSuite
	public void setup() throws IOException, InterruptedException, Exception {

		report = new ExtentReports(System.getProperty("user.dir") + "//extentReport//ExtentReportResults.html", false);
		try {

		} catch (Exception e) {
		}

		testUrl = ConfigProperties.baseUrl + "secur/frontdoor.jsp?sid=[SESSION_ID_FROM_RESPONSE]";
		System.out.println("----------" + testUrl + "----------");
		targetBrowser = ConfigProperties.browserName;
		os = ConfigProperties.operatingSys;

		if (os.toLowerCase().equals("windows")) {
			if (targetBrowser.toLowerCase().contains("firefox") || targetBrowser.toLowerCase().contains("ff")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "//src//main//resources//drivers_window//geckodriver.exe");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "false");
				WebDriverManager.firefoxdriver().setup();

				FirefoxOptions options = new FirefoxOptions();
				options.setHeadless(true);
				options.addArguments("window-size=1366,768");
				driver = new FirefoxDriver(options);

			}

			else if (targetBrowser.toLowerCase().contains("chrome")) {

				WebDriverManager.chromedriver().setup();
				Map<String, Object> prefs = new HashMap<>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.setHeadless(false);   //set true for chrome headless
				options.addArguments("disable-popup-blocking");
				driver = new ChromeDriver(options);
			}

			else if (targetBrowser.toLowerCase().contains("IE") || targetBrowser.toLowerCase().contains("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "//src//main//resources//drivers_window//IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
		}

		if (os.toLowerCase().equals("linux")) {
			if (targetBrowser.toLowerCase().contains("firefox") || targetBrowser.toLowerCase().contains("ff")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "//src//main//resources//drivers_linux//geckodriver");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "false");
				driver = new FirefoxDriver();
			} else if (targetBrowser.toLowerCase().contains("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "//src//main//resources//drivers_linux//chromedriver");
				driver = new ChromeDriver();
			}
		}

		if (os.toLowerCase().equals("mac")) {
			if (targetBrowser.toLowerCase().contains("firefox") || targetBrowser.toLowerCase().contains("ff")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "//src//main//resources//drivers_mac//geckodriver");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "false");
				driver = new FirefoxDriver();
			} else if (targetBrowser.toLowerCase().contains("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "//src//main//resources//drivers_mac//chromedriver");
				driver = new ChromeDriver();
			}

			else if (targetBrowser.toLowerCase().contains("safari")) {
				driver = new SafariDriver();
			}
		}

		driver.manage().window().maximize();
		/*
		 * driver.get(testUrl.replace("[SESSION_ID_FROM_RESPONSE]",
		 * BasePage.loginUsingSoapAPI()));
		 */
		driver.get(ConfigProperties.baseUrl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void log(Method method) {
		log("--------------------------------------------------------", ILogLevel.TESTCASE);
		log("Test [" + method.getName() + "] Started", ILogLevel.TESTCASE);
		log("--------------------------------------------------------", ILogLevel.TESTCASE);
	}

	/**
	 * capture screenshot on test(pass/fail)
	 */
	@AfterMethod
	public void setScreenshot(ITestResult result, Method method) {
		if (result.isSuccess()) {
			test.log(LogStatus.PASS, "Test Passed");
		}
		if (!result.isSuccess()) {
			test.log(LogStatus.FAIL, "Test Failed");
			try {
				test.log(LogStatus.FAIL, test.addScreenCapture(capture(driver)) + "Test Failed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log("--------------------------------------------------------", ILogLevel.TESTCASE);
		log("Test [" + method.getName() + "] Finished", ILogLevel.TESTCASE);
		log("--------------------------------------------------------", ILogLevel.TESTCASE);

	}

	@AfterSuite
	public void terminateDriver() {
		if (driver != null) {

			driver.quit();
			report.endTest(test);
			report.flush();

		}
	}

	public static String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File(System.getProperty("user.dir") + "/target/screenshots/failedImages/"
				+ System.currentTimeMillis() + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
	}

}
