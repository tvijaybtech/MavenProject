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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;


@Test
public class Psyarxiv {

	public static WebDriver driver;
	public static String firstProductTitle;
	public static String clickedProductTitle;
	
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
		driver.get("https://psyarxiv.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
    
	@Test
	public static void search() {

		WebElement searchbox = driver.findElement(By.id("searchBox"));
		searchbox.sendKeys("psychology");

		WebElement search = driver.findElement(By.xpath("//span[@class='input-group-btn']//button[@type='button']"));
		search.click();
	}
    
	@Test(priority=1)
	public static void sorting() {

		WebElement sortby = driver.findElement(By.id("sortBy"));
		sortby.click();

		WebElement newest_to_oldests = driver
				.findElement(By.xpath("//button[text()='Modified Date (newest to oldest)']"));
		newest_to_oldests.click();

		WebElement accept = driver
				.findElement(By.xpath("//div[@id='ember748']//descendant::button[@class='btn btn-default']"));
		accept.click();

	}

	@Test(priority=2)
	public static void clickFirstResults() {

		WebElement firstproduct = driver.findElement(By.xpath(
				"//div[@class='search-result p-sm']//descendant::div[@class='col-xs-12']//child::div[@id='ember1032']//following-sibling::h4"));
		firstProductTitle = firstproduct.getText();
		System.out.println(firstProductTitle);
		firstproduct.click();
	}

	@Test(priority=3)
	public static void getTitle_and_Screenshot() throws IOException {

		WebElement products = driver.findElement(By.id("preprintTitle"));
		clickedProductTitle = products.getText();
		System.out.println(clickedProductTitle);

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\tvija\\eclipse-workspace\\Maven_Project\\Screenshot\\psyarxiv.png");
		FileUtils.copyFile(source, destination);
	}
	
	@Test(priority=4)
	public static void searchTitleComparision() {

		if (firstProductTitle.equals(clickedProductTitle)) {
			System.out.println("Title is equal");
		}
	}
    @AfterClass
	public static void quit() {

		driver.quit();

	}
}
