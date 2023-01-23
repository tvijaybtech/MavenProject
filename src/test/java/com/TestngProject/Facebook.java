package com.TestngProject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Facebook {

	public static WebDriver driver;
	public static String title;

	@BeforeSuite

	public static ChromeOptions desiredOptions() {

		List<String> listOfOptions = new ArrayList<String>();
		listOfOptions.add("start-maximized");

		ChromeOptions options = new ChromeOptions();
		options.addArguments(listOfOptions);
		return options;
	}

	@BeforeTest

	public static void browserLaunch() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(desiredOptions());
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@BeforeClass

	public static void correctpageCheck() {

		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);
		String url = currentUrl.toUpperCase();

		title = driver.getTitle();
		System.out.println(title);
		String titlepage = title.toUpperCase();

		String s = "FACEBOOK";

		if (url.contains(s) == titlepage.contains(s)) {
			System.out.println("correct page");
		} else {
			System.out.println("not matched");
		}
	}
  
	@Test(priority=-1)
	public static void loginPage() throws InterruptedException {

		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("tvijaybtech97@gmail.com");

		WebElement password = driver.findElement(By.name("pass"));
		password.sendKeys("********");

		WebElement login = driver.findElement(By.name("login"));
		login.click();

		Thread.sleep(5000);
	}
    @Test(dependsOnMethods="loginPage")
	public static void errormsgCapture() throws IOException {

		TakesScreenshot Errormsg = (TakesScreenshot) driver;
		File source = Errormsg.getScreenshotAs(OutputType.FILE);
		File location = new File("C:\\Users\\tvija\\eclipse-workspace\\Maven_Project\\Screenshot\\facebook.png");
		FileUtils.copyFile(source, location);
	}
    @Test
    public static void createAccount() throws InterruptedException {
		
    	driver.navigate().to("https://www.facebook.com/");

		WebElement newaccount = driver.findElement(By.xpath("(//a[@role=\"button\"])[2]"));
		newaccount.click();

		Thread.sleep(3000);

		WebElement firstname = driver.findElement(By.xpath("//input[@name='firstname']"));
		firstname.sendKeys("VIJAYAKUMAR");

		WebElement surname = driver.findElement(By.xpath("//input[@name='lastname']"));
		surname.sendKeys("T");

		WebElement mobilenumber = driver.findElement(By.xpath("(//input[@type='text'])[4]"));
		mobilenumber.sendKeys("7373114990");

		WebElement newpassword = driver.findElement(By.xpath("(//input[@type=\"password\"])[2]"));
		newpassword.sendKeys("");

		Select day = new Select(driver.findElement(By.xpath("//select[@name='birthday_day']")));
		day.selectByVisibleText("9");

		Select month = new Select(driver.findElement(By.xpath("//select[@title='Month']")));
		month.selectByVisibleText("Apr");

		Select year = new Select(driver.findElement(By.xpath("//select[@title='Year']")));
		year.selectByVisibleText("1993");

		WebElement gender = driver.findElement(By.xpath("(//input[@type=\"radio\"])[2]"));
		gender.click();

		WebElement signup = driver.findElement(By.xpath("(//button[@type=\"submit\"])[2]"));
		signup.click();

		Thread.sleep(10000);
	}
    @Test(dependsOnMethods="createAccount")
    public static void createaccountErrormsg() throws IOException {
		
    	TakesScreenshot createaccount_error = (TakesScreenshot) driver;
		File source1 = createaccount_error.getScreenshotAs(OutputType.FILE);
		String location1 = "C:\\Users\\tvija\\eclipse-workspace\\Maven_Project\\Screenshot\\signup.png";
		FileHandler.copy(source1, new File(location1));
		
		WebElement close = driver.findElement(By.xpath("//img[@class='_8idr img']"));
		close.click();
	}
    @Test(dependsOnMethods="createaccountErrormsg")
    public static void titleCheck() throws InterruptedException {
    	
    	Thread.sleep(5000);
    	String title1 = driver.getTitle();
		System.out.println(title1);

		if (title.contains(title1)) {
			System.out.println("same title");
		} else {
			System.out.println("title not match");
		}
	}
    
    @Test@Ignore
	public static void close() {
		driver.close();
	}
    
    @AfterSuite
    public static void quit() {
    	driver.quit();

	}
	}

