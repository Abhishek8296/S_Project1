
# Yatra-selenium-tests
Automation project on yatra for web. Based on Java-Maven, Selenium-webdriver. Used Testng to design test cases.

## Getting Started
Clone the repo or download the project to your system.

## Prerequisites
* Need maven installed in system
* Set options.setHeadless(true) to run the tests on chrome headless;

### Parameters to run Test
* OPERATING_SYS: Window, linux, mac
* BROWSER_NAME: Chrome, IE, Firefox, Safari

### Run on Local
1. Go to file \src\main\java\com\auto\configProperties\ConfigProperties.java
2. Change value of operatingSys string to "Window" or "Mac" or "linux"
3. Change value of browserName string to "firefox" or "chrome" or "IE" or "safari" 
4. From root folder, run the command `mvn clean test`

### Run Single Test
1. Go to file \src\main\java\com\auto\configProperties\ConfigProperties.java
2. Change value of operatingSys string to "Window" or "Mac" or "linux"
3. Change value of browserName string to "firefox" or "chrome" or "IE" or "safari" 
4. From root folder, run the command `mvn -Dtest=Booking_Section_Test_Scenarios test`

### View Reports and Screenshot
1. Open Project Folder > extentReport > ExtentReportResults.html
2. Copy path and paste it to your browser
3. You can see the test report with failed screenshot(if test are failed)
