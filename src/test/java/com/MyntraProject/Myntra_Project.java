package com.MyntraProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra_Project {

	public static WebDriver driver;
	public static int minPrice;

	@Test(priority = 1)
	public static void browserLaunch() {

		try {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			driver.get(
					"https://www.myntra.com/kids?f=Categories%3ATshirts%3A%3AGender%3Aboys%2Cboys%20girls&plaEnabled=false");
			driver.manage().window().maximize();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public static void mumberOfProducts() {

		try {
			List<WebElement> allProducts = driver.findElements(By.xpath("//li[@class='product-base']"));
			System.out.println("Total number of product:" + allProducts.size());
//			for (WebElement product : allProducts) {
//				System.out.println(product.getText());
//			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println("=======================================");
	}

	@Test(priority = 3)
	public static void minimumPriceOfProduct() {

		List<WebElement> allPrices = driver.findElements(By.xpath(
				"//li[@class='product-base']//descendant::div[@class='product-price']//descendant::span[(@class='product-discountedPrice') or (text() and not(@class))]"));
		List<Integer> allPricesList = new ArrayList<>();
		// ArrayList<Integer> allPricesList = new ArrayList<Integer>();
		for (WebElement price : allPrices) {
			String priceText = price.getText().replace("Rs. ", "");
			int prices = Integer.parseInt(priceText);
			allPricesList.add(prices);
		}
//			for (int i = 0; i < allPrices.size(); i++) {
//				String priceText = allPrices.get(i).getText().replaceAll("Rs. ", "");
//				int price = Integer.parseInt(priceText);
//				allPricesList.add(price);
//			}

		System.out.println("Total Number Of Product Prices:" + allPricesList.size());
		minPrice = Collections.min(allPricesList);
		System.out.println("Minimum Price:" + minPrice);
		System.out.println("=======================================");
	}

	@Test(priority = 4)
	public static void minimumPriceOfProductName() {
		int count = 0;
		try {
			List<WebElement> minimumPriceAllProductName = driver.findElements(By.xpath(
					"//li[@class='product-base']//descendant                                                                                                                                                                                                                 ::div[@class='product-price']//descendant::span[(@class='product-discountedPrice' and text()='"
							+ minPrice + "') or (text()='" + minPrice
							+ "' and not(@class))]//ancestor::div[@class='product-productMetaInfo']/h3[@class='product-brand']"));
			for (WebElement minimumPriceProductName : minimumPriceAllProductName) {
				String productName = minimumPriceProductName.getText();
				System.out.println("Product name minimum price : " + productName);
				count++;
			}
			System.out.println("Totally " + count + " Products are in minimum price : " + minPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("==========================================================");
	}

	@Test(priority = 5)
	public static void subProductNameOfMinimumPrice() {
		int count = 0;
		try {
			List<WebElement> allSubProductName = driver.findElements(By.xpath(
					"//li[@class='product-base']//descendant::div[@class='product-price']//descendant::span[(@class='product-discountedPrice' and text()='"
							+ minPrice + "') or (text()='" + minPrice
							+ "' and not(@class))]//ancestor::div[@class='product-productMetaInfo']/h4[@class='product-product']"));
			for (WebElement allSubProductNames : allSubProductName) {
				String subProductName = allSubProductNames.getText();
				System.out.println("Subproduct name minimum price : " + subProductName);
				count++;
			}
			System.out.println("Totally " + count + " SubProducts are in minimum price : " + minPrice);
		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println("=======================================================");
	}

	@Test (priority = 6)
	public static void productName_For_Given_Sub_ProductName() {
		String subBrandName = "Boys Pack of 5 T-shirt";
		WebElement brandName = driver.findElement(
				By.xpath("//li[@class='product-base']//div[@class='product-productMetaInfo']/h4[contains(text(),'"
						+ subBrandName + "')]//preceding-sibling::h3"));
		String productNameText = brandName.getText();
		System.out.println("Subproductname Product Text is : " + productNameText);
		System.out.println("=======================================================");
	}
}
