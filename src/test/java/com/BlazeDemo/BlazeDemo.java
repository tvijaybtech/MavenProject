package com.BlazeDemo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BlazeDemo {

	public static Float minPrice;
	
	private static ChromeOptions desiredOptions() {

		List<String> listOfOptions = new ArrayList<String>();
		listOfOptions.add("start-maximized");

		ChromeOptions options = new ChromeOptions();
		options.addArguments(listOfOptions);
		return options;
	}

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(desiredOptions());
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get("https://blazedemo.com/");

		Select departurecity = new Select(driver.findElement(By.name("fromPort")));
		departurecity.selectByVisibleText("Boston");

		Select destinationcity = new Select(driver.findElement(By.name("toPort")));
		destinationcity.selectByVisibleText("London");
		
		WebElement Find_Flights = driver.findElement(By.xpath("//input[@type='submit']"));
		Find_Flights.click();
		
		List<WebElement> allPrice = driver.findElements(By.xpath("//table/tbody/tr/td[6]"));
		
		List<Float> allPricesList = new ArrayList<>();
		
		for (WebElement allprices : allPrice) {
			String priceText = allprices.getText().replace("$","");
			Float prices=Float.parseFloat(priceText);
			System.out.println(prices);
			allPricesList.add(prices);
			}
		
		System.out.println("Total Number Of Flights:" + allPricesList.size());
		Float minPrice = Collections.min(allPricesList);
		System.out.println("Minimum Price:" + minPrice);
		
		WebElement flightnumber = driver.findElement(By.xpath("//table/tbody/tr/td[contains(text(),"+minPrice+")]//ancestor::tr/td[2]"));
		System.out.println("Flight Number : "+flightnumber.getText());
		
		WebElement flightname = driver.findElement(By.xpath("//table/tbody/tr/td[contains(text(),"+minPrice+")]//ancestor::tr/td[3]"));
		System.out.println("Flight Name :"+flightname.getText());
		
		WebElement chooseflight = driver.findElement(By.xpath("//table/tbody/tr/td[contains(text(),"+minPrice+")]//ancestor::tr/form[@method='post']//parent::tr/td/input[@type='submit']"));
		chooseflight.click();
		
		WebElement firstname = driver.findElement(By.id("inputName"));
		firstname.sendKeys("VIJAYAKUMAR");
		
		WebElement address = driver.findElement(By.id("address"));
		address.sendKeys("6/149 Ramudaiyanoor,N.Pudupatti Post");
		
		WebElement city = driver.findElement(By.id("city"));
		city.sendKeys("Namakkal");
		
		WebElement state = driver.findElement(By.id("state"));
		state.sendKeys("TamilNadu");
		
		WebElement zipcode = driver.findElement(By.id("zipCode"));
		zipcode.sendKeys("637020");
		
		Select cardtype = new Select(driver.findElement(By.name("cardType")));
		cardtype.selectByVisibleText("Visa");
		
		WebElement cardno = driver.findElement(By.id("creditCardNumber"));
		cardno.sendKeys("1234 5678 1234");
		
		WebElement cardmonth = driver.findElement(By.id("creditCardMonth"));
		cardmonth.clear();
		cardmonth.sendKeys("12");
		
		WebElement cardyear = driver.findElement(By.id("creditCardYear"));
		cardyear.clear();
		cardyear.sendKeys("2025");
		
		WebElement nameoncard = driver.findElement(By.id("nameOnCard"));
		nameoncard.sendKeys("VIJAY");
		
		WebElement rememberme = driver.findElement(By.id("rememberMe"));
		rememberme.click();
		
		WebElement purchaseflight = driver.findElement(By.xpath("//input[@type='submit']"));
		purchaseflight.click();
		
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\tvija\\eclipse-workspace\\Maven_Project\\Screenshot\\cowin.png");
		FileUtils.copyFile(source, destination);
		System.out.println("screenshot captured");
		
		driver.quit();
	
	}
}