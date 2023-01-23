package com.PsyarxivProject;

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
import io.github.bonigarcia.wdm.WebDriverManager;

public class Psyarxiv {

	private static ChromeOptions desiredOptions() {

		List<String> listOfOptions = new ArrayList<String>();
		listOfOptions.add("start-maximized");

		ChromeOptions options = new ChromeOptions();
		options.addArguments(listOfOptions);
		return options;

	}

	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(desiredOptions());
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get("https://psyarxiv.com/");

		WebElement searchbox = driver.findElement(By.id("searchBox"));
		searchbox.sendKeys("psychology");

		WebElement search = driver.findElement(By.xpath("//span[@class='input-group-btn']//button[@type='button']"));
		search.click();
		
		WebElement sortby = driver.findElement(By.id("sortBy"));
		sortby.click();
		
		WebElement newest_to_oldests = driver.findElement(By.xpath("//button[text()='Modified Date (newest to oldest)']"));
		newest_to_oldests.click();
		
		WebElement accept = driver.findElement(By.xpath("//div[@id='ember748']//descendant::button[@class='btn btn-default']"));
		accept.click();
		
		WebElement firstproduct =driver.findElement(By.xpath("//div[@class='search-result p-sm']//descendant::div[@class='col-xs-12']//child::div[@id='ember1032']//following-sibling::h4"));
	    String title = firstproduct.getText();
	    System.out.println(title);
	    firstproduct.click();
	    
	    WebElement products = driver.findElement(By.id("preprintTitle"));
		String productTitle = products.getText();
	    System.out.println(productTitle);
	   
	    TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\tvija\\eclipse-workspace\\Maven_Project\\Screenshot\\psyarxiv.png");
		FileUtils.copyFile(source, destination);
		 
	    if(title.equals(productTitle))
	    {
	     System.out.println("Title is equal");
	     driver.quit();
	    }
	    
	}
}
