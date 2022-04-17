package com.auto.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.TestCore;
import com.auto.pageObjects.SRASignUpPageObjects;
import com.auto.pageObjects.mailinatorPageObjects;
import com.github.javafaker.Faker;
import com.relevantcodes.extentreports.LogStatus;

public class SignUpPage extends BasePage{

	int randomNum =  (int)(Math.random() * 2000);
	Faker faker = new Faker();
	String fname = faker.name().firstName();
	String lname = faker.name().lastName();
	String email = fname.toLowerCase() + lname.toLowerCase() +randomNum+ "@mailinator.com";
	String userName = "PP_"+fname+randomNum;
	String state = faker.address().state();
//	String dob = faker.date.betweens("2020-01-01T00:00:00.000Z", "2030-01-01T00:00:00.000Z");

	public SignUpPage(WebDriver driver) {
		super(driver);
	}

   /*
    * verify site url
    */
	public void VerifySiteUrl() {
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "https://www.yatra.com/" );
		log("Verify site url", ILogLevel.METHOD);
		TestCore.test.log(LogStatus.PASS, "Verify site url", "Site url verified successfully");
	}

	public void CreateAnAccount() throws InterruptedException {
//		System.out.println(dob);
		driver.findElement(By.cssSelector(SRASignUpPageObjects.signupLink)).click();
		driver.findElement(By.cssSelector(SRASignUpPageObjects.userName)).sendKeys(userName);
		System.out.println(userName);
//		waitForElementToBeExist(null);
		driver.findElement(By.cssSelector(SRASignUpPageObjects.password)).sendKeys("Damco123");
		driver.findElement(By.cssSelector(SRASignUpPageObjects.confirmPassword)).sendKeys("Damco123");
		driver.findElement(By.cssSelector(SRASignUpPageObjects.email)).sendKeys(email);
		driver.findElement(By.cssSelector(SRASignUpPageObjects.sendVerificationCodeButton)).click();
	}

	public void verificationCode() throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.open()");
		Thread.sleep(1500);
	    ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1)); //switches to new tab
	    driver.get("https://www.mailinator.com/");
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(mailinatorPageObjects.mailSearch)).sendKeys((email)+Keys.ENTER);
		driver.findElement(By.cssSelector(mailinatorPageObjects.openMail)).click();
		Thread.sleep(2000);

		driver.switchTo().frame(driver.findElement(By.cssSelector(mailinatorPageObjects.codeIframe)));
		WebElement ele = driver.findElement(By.cssSelector(mailinatorPageObjects.code));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView();", ele);
		String verificationCode = driver.findElement(By.cssSelector(mailinatorPageObjects.code)).getText();
		System.out.println("text is " + verificationCode);
		String[] updated = verificationCode.split(":");
		String code = updated[1].trim();
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0)); // switch back to main screen
		driver.findElement(By.cssSelector(SRASignUpPageObjects.verificationCode)).sendKeys(code);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(SRASignUpPageObjects.submitCodeButton)).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(SRASignUpPageObjects.continueButton)).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(SRASignUpPageObjects.firstName)).sendKeys(fname);
		driver.findElement(By.cssSelector(SRASignUpPageObjects.lastName)).sendKeys(lname);
		driver.findElement(By.cssSelector(SRASignUpPageObjects.dob)).sendKeys("02/08/1995");
		driver.findElements(By.cssSelector(SRASignUpPageObjects.contactEmail)).get(1).sendKeys(email);
		driver.findElement(By.cssSelector(SRASignUpPageObjects.personalAddress)).sendKeys((state)+Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(SRASignUpPageObjects.saveButton)).click();
	}

//	public void resumeSignUp() {
//		driver.findElement(By.cssSelector(SRASignUpPageObjects.verificationCode)).sendKeys(formattedName);
//		}

	/*
	 * select departure city from depart field
	*/
//    public void SelectDepartFromCity() throws IOException
//    {
//    	driver.findElement(By.cssSelector(SRASignUpPageObjects.departFromCssLoc)).click();
//    	fieldClear(By.cssSelector(SRASignUpPageObjects.departFromCssLoc));
//    	driver.findElement(By.cssSelector(SRASignUpPageObjects.departFromCssLoc)).click();
//    	String data[][] = getData.getDataFromExcel("testdata.xlsx", "MySheet");
//		String departFrom = data[1][0];
//		driver.findElement(By.cssSelector(SRASignUpPageObjects.departFromCssLoc))
//		.sendKeys(departFrom);
//    	driver.findElement(By.xpath(SRASignUpPageObjects.bangloreDropdownLoc)).click();
//		log("Select Departure value:"+departFrom, ILogLevel.METHOD);
//		TestCore.test.log(LogStatus.PASS, "Verify Banglore selected from Departure field", "Banglore selected from Departure field verified successfully");
//     }
//
//    /*
//	 * select arrival city from going to field
//	 */
//    public void SelectGoingToCity() throws IOException
//    {
//    	driver.findElement(By.cssSelector(SRASignUpPageObjects.goingToCssLoc)).click();
//    	fieldClear(By.cssSelector(SRASignUpPageObjects.goingToCssLoc));
//    	driver.findElement(By.cssSelector(SRASignUpPageObjects.goingToCssLoc)).click();
//    	String data[][] = getData.getDataFromExcel("testdata.xlsx", "MySheet");
//		String arrivalValue = data[1][1];
//		driver.findElement(By.cssSelector(SRASignUpPageObjects.goingToCssLoc))
//		.sendKeys(arrivalValue);
//    	driver.findElement(By.xpath(SRASignUpPageObjects.delhiDropdownLoc)).click();
//		log("Select Departure value:"+arrivalValue, ILogLevel.METHOD);
//		TestCore.test.log(LogStatus.PASS, "Verify Banglore selected from Departure field", "Banglore selected from Departure field verified successfully");
//     }
//
//    /*
//     * click on search flighs button
//    */
//    public void ClickOnSearchFlightsButton()
//    {
//    	driver.findElement(By.cssSelector(SRASignUpPageObjects.searchFlightsCssLoc)).click();
//    	log("Click on search flights button", ILogLevel.METHOD);
//		TestCore.test.log(LogStatus.PASS, "Click on search flights button", "Search flights button clicked successfully");
//    }
//
//    /*
//     * click on departure date button
//    */
//    public void ClickOnDepartureDate()
//    {
//    	driver.findElement(By.cssSelector(SRASignUpPageObjects.departureDateInputFieldCssLoc)).click();
//    	log("Click on departure date field", ILogLevel.METHOD);
//		TestCore.test.log(LogStatus.PASS, "Click on departure date field", "Departure date field clicked successfully");
//    }
//
//    /*
//     * click on search return date field
//    */
//    public void ClickOnReturnDate()
//    {
//    	driver.findElement(By.cssSelector(SRASignUpPageObjects.returnDateInputFieldCssLoc)).click();
//    	log("Click on return date field", ILogLevel.METHOD);
//		TestCore.test.log(LogStatus.PASS, "Click on return date field", "Return date field clicked successfully");
//    }
//
//    /*
//     * select departure date
//    */
//    public void SelectDepartureDate()
//    {
//    	Calendar cal = Calendar.getInstance();
//		Date today = cal.getTime();
//		cal.add(Calendar.WEEK_OF_MONTH, 2);
//		Date month = cal.getTime();
//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		String departuredate = dateFormat.format(month.getTime());
//		driver.findElements(By.cssSelector(SRASignUpPageObjects.departureDateLoc.replace("27/06/2021", departuredate))).get(0).click();
//		log("Select departure date value"+departuredate, ILogLevel.METHOD);
//		TestCore.test.log(LogStatus.PASS, "Select departure date value", "Departure date value selected successfully");
//    }
//
//    /*
//     * verify on search flighs exists
//    */
//    public void NoFlightsIfExists()
//    {
//    		if(driver.findElements(By.xpath(SRASignUpPageObjects.NoFlightsLoc)).size() != 0)
//    	 {
//    		driver.findElement(By.cssSelector(SRASignUpPageObjects.departLoc)).click();
//        	Calendar cal = Calendar.getInstance();
//    		Date today = cal.getTime();
//    		cal.add(Calendar.DATE, 10);
//    		Date month = cal.getTime();
//    		DateFormat dateFormat = new SimpleDateFormat("WW,MMMM dd,yyyy");
//    		String updatedeparturedate = dateFormat.format(month.getDate());
//    		System.out.println("Time is :" + updatedeparturedate);
//    		driver.findElement(By.cssSelector(SRASignUpPageObjects.UpdateDepartDateLoc.replace("Thursday, July 15, 2021", updatedeparturedate))).click();
//    	    driver.findElement(By.cssSelector(SRASignUpPageObjects.SearchAgainLoc)).click();
//    	    log("Select departure date value"+updatedeparturedate, ILogLevel.METHOD);
//    		TestCore.test.log(LogStatus.PASS, "Select departure date value", "Departure date value selected successfully");
//    }
//    else {
//    	  pause(2);
//    }
//   }
}

