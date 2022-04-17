package com.auto.base;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends Page {

	protected WebDriver driver;
	public static String SCREENSHOT_FOLDER = "target/screenshots/";
	public static final String SCREENSHOT_FORMAT = currentDate("dd_MM_yyyy HH_mm_ss") + ".png";

	public BasePage(WebDriver driver) {

		this.driver = driver;

	}

	/*
	 * Return true if element is displayed else false
	 *
	 * @return
	 */
	public final boolean waitForElementDisplayed(By by) {
		for (int sec = 1; sec <= 10; sec++) {
			try {
				if (driver.findElement(by).isDisplayed()) {
					Thread.sleep(1000);
					return true;
				}
			} catch (Exception e) {
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
		// analyzeBrowserLogs();
		log_Method("Debug Log : waitForElementDisplayed method : Element not displayed.");
		return false;
	}

	/*
	 * Return true if element is clickable else return false
	 *
	 * @return
	 */
	public final boolean waitForElementClickableAndClick(By _by) throws InterruptedException {
		for (int sec = 1; sec <= 10; sec++) {
			try {
				driver.findElement(_by).click();
				log_Method("Debug Log : waitForElementClickableAndClick - Able to Click Element");
				return true;
			} catch (Exception e) {
				Thread.sleep(1000);
				log_Method("Debug Log : waitForElementClickableAndClick - Unable to Click Element - Retrying again");
			}
		}
		// analyzeBrowserLogs();
		log_Method("Debug Log : waitForElementClickableAndClick - Unable to Click");
		return false;
	}

	/*
	 * Return true if element is displayed
	 *
	 * @param: webelement else false
	 *
	 * @return
	 */
	public final boolean waitForElementDisplayed(WebElement _ele) {
		for (int sec = 1; sec <= 100; sec++) {
			try {
				if (_ele.isDisplayed()) {
					/* Thread.sleep(1000); */
					return true;
				}
			} catch (Exception e) {
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
		// analyzeBrowserLogs();
		return false;
	}

	/*
	 * Returns deriver variable instance
	 *
	 * @return: driver
	 */
	public final WebDriver getWebDriver() {
		return driver;
	}

	/*
	 * Holds the driver for a particular time period
	 *
	 * @param: seconds
	 */
	public final void pause(int seconds) {
		pauseMilis(seconds * 500);
	}

	/*
	 * Holds the driver for particular time period
	 *
	 * @param: miliseconds
	 */
	public final void pauseMilis(long miliSeconds) {
		try {
			Thread.sleep(miliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * generate a random number of given length and returns it
	 *
	 * @param length
	 * @return
	 */
	public String AutogenerateNumber(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	/*
	 * Return true if driver finds the element is present else return false
	 */
	public boolean isElementPresent(By by) {
		try {

			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/*
	 *
	 */
	public void waitForAlert() {
		int i = 0;
		while (i++ < 10) {
			try {
				driver.switchTo().alert();
				break;
			} catch (NoAlertPresentException e) {
				pause(1);
				continue;
			}
		}
	}

	/**
	 * Returns true if alert is present else returns false
	 *
	 * @return
	 */
	public boolean isAlertPresent() {
		pauseMilis(500);
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException nep) {
			return false;
		}
	}

	/**
	 * Get alert text
	 *
	 * @return
	 */
	public String getAlertText() {
		pauseMilis(1000);
		return driver.switchTo().alert().getText();
	}

	/**
	 * Accepts alert.
	 */
	public void acceptAlert() {
		pause(1);
		driver.switchTo().alert().accept();
	}

	/**
	 * Dismiss alert
	 */
	public void dismissAlert() {
		pause(1);
		driver.switchTo().alert().dismiss();
	}

	/*
	 * Click on element when driver finds the element
	 */
	public void click(By _by, String _log, int... index) {

		if (index.length > 0) {
			driver.findElements(_by).get(index[0]).click();
		}

		else {
			driver.findElement(_by).click();
		}
		log(_log, ILogLevel.TEST);

	}

	public void performActionIfExists(By _by) {
		if (!driver.findElements(_by).isEmpty()) {
			driver.findElement(_by).click();
		}
	}

	/*
	 * Verify the title when driver access it
	 *
	 * @return: title
	 */
	public String title() {
		return driver.getTitle();
	}

	/*
	 * Verify the url when driver access it
	 *
	 * @return: url
	 */
	public String url() {
		return driver.getCurrentUrl();
	}

	/*
	 * Get expected element when driver locates the element
	 */
	public void findExpectedElement(By _by) {
		driver.findElement(_by);

	}

	public void fieldClear(By _by, int... index) {
		if (index.length > 0) {
			driver.findElements(_by).get(index[0]).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			driver.findElement(_by).clear();
		}

		else {
			driver.findElement(_by).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			driver.findElement(_by).clear();
		}

		pause(2);

	}

	public void sendKeys(By _by, String _key, int _wait, String _log, int... index) {
		if (index.length > 0) {
			driver.findElements(_by).get(index[0]).sendKeys(_key);
		}

		else {
			driver.findElement(_by).sendKeys(_key);
		}
		log(_log, ILogLevel.METHOD);
		pause(_wait);
	}

	/*
	 * Get text when driver finds the element
	 *
	 * @return: text
	 */
	public String getText(By _by, int... index) {
		String text;
		if (index.length > 0)
			text = driver.findElements(_by).get(index[0]).getText();
		else
			text = driver.findElement(_by).getText();
		return text;

	}

	/*
	 * Get value when driver finds the element
	 *
	 * @return: value
	 */
	public String getValue(By _by) {
		String value = driver.findElement(_by).getAttribute("value");
		return value;
	}

	/*
	 * Get attribute value when driver finds the element
	 *
	 * @return: value
	 */
	public String getAttribute(By _by, String attr) {
		String value = driver.findElement(_by).getAttribute(attr);
		return value;
	}

	/*
	 * Gets text of selected option of drop down when driver finds the element
	 *
	 * @return: text of selected option
	 */
	public String dropDownGetSelectedOptionText(By _by) {
		Select select = new Select(driver.findElement(_by));
		WebElement option = select.getFirstSelectedOption();
		return option.getText();
	}

	public void log_Method(String _massageString) {
		log(_massageString, ILogLevel.METHOD);
	}

	public void dropdownSelect(By _by, String _option, String _log, int... index) {
		if (index.length > 0)
			new Select(driver.findElements(_by).get(index[0])).selectByVisibleText(_option);
		else
			new Select(driver.findElement(_by)).selectByVisibleText(_option);
		pause(2);

		log(_log, ILogLevel.TEST);
	}

	/*
	 * Select drop down element by index when driver finds the element
	 *
	 * @param: agrument
	 */
	public void dropdownSelectByIndex(By _by, int _arg) {
		new Select(driver.findElement(_by)).selectByIndex(_arg);

	}

	public void dropdownSelectAll(By _by) {
		List<WebElement> listOfOptions = new Select(driver.findElement(_by)).getOptions();
		Iterator<WebElement> it = listOfOptions.iterator();
		while (it.hasNext()) {
			WebElement ele = it.next();
			dropdownSelect(_by, ele.getText(), null);
		}
	}

	public final boolean isElementEnabled(By by) {
		return driver.findElement(by).isEnabled();
	}

	public final boolean waitForElementEnabled(By by) {
		for (int sec = 1; sec <= 100; sec++) {
			try {
				if (driver.findElement(by).isEnabled()) {
					Thread.sleep(1000);
					return true;
				}
			} catch (Exception e) {
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
		// analyzeBrowserLogs();
		return false;
	}

	@Override
	public String getValue(String _property) {
		return TestCore.config.getProperty(_property);
	}

	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	/*
	 * Generate a random number of given length and return it
	 *
	 * @param: length
	 *
	 * @return
	 */
	public static String autogenerateNumber(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	public static String currentDate(String dateFormats) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormats);
		Date date = new Date();
		String date1 = dateFormat.format(date);
		return date1;
	}

	/*
	 * Calls the driver to navigate back
	 */
	public void navigateBack() {
		driver.navigate().back();
	}

	public void screenShot(String result) {
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(f, new File(SCREENSHOT_FOLDER + result + SCREENSHOT_FORMAT).getAbsoluteFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Driver waits for the page title for given time until expected condition is
	 * achieved
	 */
	public void waitForPageTitle(String title) {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.titleIs(title));
	}

	/*
	 * Driver waits for the page url for given time until expected condition is
	 * achieved
	 */
	public void waitForPageURL(String url) {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.urlContains(url));
	}

	public void waitForElementToBeClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/*
	 * Driver waits for the element to be exit
	 */
	public void waitForElementToBeExist(By locator) {
		pause(2);
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForTitle(String element) {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.titleIs(element));
	}

	/*
	 * Driver waits for the element to be staled
	 */
	public void waitForElementToBeStaled(By by) {
		WebElement ele = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.stalenessOf(ele));

	}

	/*
	 * Driver navigates to refresh the page
	 */
	public void refreshPage() {
		driver.navigate().refresh();
		pause(5);

	}

	/*
	 * Driver switch to specific tab
	 */
	public void switchToTab(int index) {
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(index));
	}

}