package com.TestngProject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

@Test
public class Flipkart {

	public static WebDriver driver;
	public static String parenttab;
	public static int first_iphoneprice;
	public static int second_iphoneprice;
	public static int total_price;
	public static int choosed_twoproduct_totalprice;

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
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@BeforeClass

	public static void getTitle() {

		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);

		String title = driver.getTitle();
		System.out.println(title);

		WebElement close = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']"));
		close.click();
	}

	@Test(priority=-1)
	public static void searchProduct() {

		// enter search product in search field

		WebElement searchbox = driver.findElement(By.xpath("//input[@title='Search for products, brands and more']"));
		searchbox.sendKeys("iphone");

		WebElement search = driver.findElement(By.xpath("//button[@class='L0Z3Pu']"));
		search.click();
	}

	@Test(dependsOnMethods="searchProduct")
	public static void clickFirstProduct() {

		// pick first product
		WebElement iphone1 = driver.findElement(By.xpath(
				"//div[@class='_1AtVbE col-12-12']//child::a[@class='_1fQZEK']//descendant::div[@class='_3pLy-c row']//descendant::div[@class='col col-7-12']//div[text()=\"APPLE iPhone 12 mini (Black, 64 GB)\"]"));
		// WebElement iphone1 =
		// driver.findElement(By.xpath("//div[@data-id='MOBGHWFH2KZRNRH5']//ancestor::div[@class='MIXNux']//following-sibling::div[@class='_3pLy-c
		// row']//div//div[@class='_4rR01T']"));
		iphone1.click();

		Set<String> windowHandles = driver.getWindowHandles();

		for (String s : windowHandles) {
			String titles = driver.switchTo().window(s).getTitle();
			System.out.println(titles);
		}
		WebElement iphone1Price = driver.findElement(By.xpath(
				"//div[@class='_1AtVbE col-12-12']//child::div[@class='aMaAEs']//descendant::div[@class='_3I9_wc _2p6lqe']"));
		System.out.println("Product1 iphone price:" + iphone1Price.getText());
		String price1 = iphone1Price.getText().replaceAll("[₹,]", "");
		first_iphoneprice = Integer.parseInt(price1);
		System.out.println("Product1 iphone price: " + first_iphoneprice);
		WebElement addtocart1 = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']"));
		addtocart1.click();

		parenttab = "Iphone- Buy Products Online at Best Price in India - All Categories | Flipkart.com";
		for (String s : windowHandles) {
			if (driver.switchTo().window(s).getTitle().equals(parenttab)) {
				break;
			}
		}
	}
	
	@Test(dependsOnMethods="clickFirstProduct")
	public static void clickSecondProduct() {

		// 5.pick second product

		// WebElement iphone2 = driver.findElement(By.xpath("//div[@class='_1AtVbE
		// col-12-12']//child::a[@class='_1fQZEK']//descendant::div[@class='_3pLy-c
		// row']//descendant::div[@class=\"col col-7-12\"]//div[text()='APPLE iPhone 13
		// (Midnight, 128 GB)']"));
		WebElement iphone2 = driver.findElement(By.xpath(
				"//div[@class=\"_1AtVbE col-12-12\"]//ancestor::div[@class=\"_2kHMtA\"]//ancestor::div[@class=\"_3pLy-c row\"]//ancestor::div[text()='APPLE iPhone 11 (Black, 64 GB)']"));
		iphone2.click();

		Set<String> windowHandles1 = driver.getWindowHandles();
		for (String s1 : windowHandles1) {
			String titles = driver.switchTo().window(s1).getTitle();
			System.out.println(titles);
		}
		WebElement iphone2Price = driver.findElement(By.xpath(
				"//div[@class='_1AtVbE col-12-12']//child::div[@class='aMaAEs']//descendant::div[@class='_3I9_wc _2p6lqe']"));
		System.out.println("Product2 iphone price:" + iphone2Price.getText());
		String price2 = iphone2Price.getText().replaceAll("[₹,]", "");
		second_iphoneprice = Integer.parseInt(price2);
		System.out.println("Product2 iphone price: " + second_iphoneprice);
		WebElement addtocart2 = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']"));
		addtocart2.click();

		for (String s1 : windowHandles1) {
			if (driver.switchTo().window(s1).getTitle().equals(parenttab)) {
				break;
			}
		}
	}
    
	@Test
	public static void goCart() {

		WebElement cart = driver.findElement(By.xpath("//a[@href='/viewcart?exploreMode=true&preference=FLIPKART']"));
		cart.click();
	}
	
	@Test(priority=2)
	public static void selectedProductTotalPrice() {
		
		choosed_twoproduct_totalprice = first_iphoneprice + second_iphoneprice;
		System.out.println("product 1 & product 2 Total Price: " + choosed_twoproduct_totalprice);
	}

	@Test(priority=1)
	public static void getCartPrice() {

		// get cart prices
		WebElement cartprice = driver.findElement(By.xpath(
				"//div[@class='_1YokD2 _3Mn1Gg col-4-12 _78xt5Y']//descendant::div[@class='_I_XQO']//descendant::div[@class='Ob17DV']//descendant::div[@class='_24KATy']//div[text()='Price (2 items)']//ancestor::div[@class='Ob17DV']//span"));
		System.out.println("Total cart price" + cartprice.getText());
		String cartprices = cartprice.getText().replaceAll("[₹,]", "");
		total_price = Integer.parseInt(cartprices);
		System.out.println("total cart prices: " + total_price);
	}
	
	@Test(priority=3)
	public static void priceComparision() {

		// check price comaparision

		if (total_price == choosed_twoproduct_totalprice) {
			System.out.println("The price is equal to cart price");
		}
	}

	@Test(dependsOnMethods="priceComparision")
	public static void takeScreenshot() throws IOException {

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\tvija\\eclipse-workspace\\Maven_Project\\Screenshot\\flipkart.png");
		FileUtils.copyFile(source, destination);
	}
	
	@AfterSuite
	public static void quit() {
		driver.quit();
	}
}
