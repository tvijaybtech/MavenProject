package com.WebTable;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTable {

	public static WebDriver driver;

	@Test(priority = 1)
	public static void browserLaunch() {

		try {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			driver.get("https://www.worldometers.info/coronavirus/");
			driver.manage().window().maximize();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test(priority = 5)
	public static void allData() {

		List<WebElement> allData = driver.findElements(By.xpath("(//table)[1]/tbody[1]/tr/td"));
		for (WebElement getAllData : allData) {
			System.out.println(getAllData.getText());
		}
	}

	@Test(priority = 2)
	public static void particularData() {

		WebElement population = driver.findElement(By.xpath("(//table)[1]/tbody[1]/tr[7]/td[15]"));
		System.out.println("India Population is : " + population.getText());
	}

	@Test(priority = 3)
	public static void rowWiseDetails() {

		List<WebElement> rowFullDetail = driver.findElements(By.xpath("(//table)[1]/tbody[1]/tr[5]/td"));
		for (WebElement rowFullDetails : rowFullDetail) {
			System.out.println(rowFullDetails.getText());
		}
	}

	@Test(priority = 4)
	public static void columnWiseDetails() {

		List<WebElement> columnFullDetail = driver.findElements(By.xpath("(//table)[1]/tbody[1]/tr/td[2]"));
		for (WebElement columnFullDetails : columnFullDetail) {
			System.out.println(columnFullDetails.getText());
		}

	}
}
