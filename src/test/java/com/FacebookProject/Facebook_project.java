package com.FacebookProject;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

public class Facebook_project {

	public static void main(String[] args) throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\tvija\\eclipse-workspace\\Maven_Project\\Driver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.facebook.com/");
		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);
		String url = currentUrl.toUpperCase();

		String title = driver.getTitle();
		System.out.println(title);
		String titlepage = title.toUpperCase();

		String s = "FACEBOOK";

		if (url.contains(s) == titlepage.contains(s)) {
			System.out.println("correct page");
		} else {
			System.out.println("not matched");
		}

		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("tvijaybtech97@gmail.com");

		WebElement password = driver.findElement(By.name("pass"));
		password.sendKeys("********");

		WebElement login = driver.findElement(By.name("login"));
		login.click();

		Thread.sleep(10000);

		TakesScreenshot Errormsg = (TakesScreenshot) driver;

		File source = Errormsg.getScreenshotAs(OutputType.FILE);
		File location = new File("C:\\Users\\tvija\\eclipse-workspace\\Maven_Project\\Screenshot\\facebook.png");

		FileUtils.copyFile(source, location);

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

		Thread.sleep(15000);

		TakesScreenshot createaccount_error = (TakesScreenshot) driver;
		File source1 = createaccount_error.getScreenshotAs(OutputType.FILE);
		String location1 = "C:\\Users\\tvija\\eclipse-workspace\\Maven_Project\\Screenshot\\signup.png";
		FileHandler.copy(source1, new File(location1));

		WebElement close = driver.findElement(By.xpath("//img[@class='_8idr img']"));
		close.click();

		String title1 = driver.getTitle();
		System.out.println(title1);

		if (title.contains(title1)) {
			System.out.println("same title");
		} else {
			System.out.println("title not match");
		}

		driver.close();

	}
}
