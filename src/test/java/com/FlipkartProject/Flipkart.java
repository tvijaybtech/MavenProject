package com.FlipkartProject;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Flipkart {

	public static void main(String[] args) throws InterruptedException, Throwable {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\tvija\\eclipse-workspace\\Selenium_Nov\\Driver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// launch flipkart

		driver.get("https://www.flipkart.com/");
		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);

		String title = driver.getTitle();
		System.out.println(title);

		WebElement close = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']"));
		close.click();

		// enter search product in search field

		WebElement searchbox = driver.findElement(By.xpath("//input[@title='Search for products, brands and more']"));
		searchbox.sendKeys("iphone");

		WebElement search = driver.findElement(By.xpath("//button[@class='L0Z3Pu']"));
		search.click();

		// pick first product
		WebElement iphone1 = driver.findElement(By.xpath("//div[@class='_1AtVbE col-12-12']//child::a[@class='_1fQZEK']//descendant::div[@class='_3pLy-c row']//descendant::div[@class='col col-7-12']//div[text()=\"APPLE iPhone 12 mini (Black, 64 GB)\"]"));
		//WebElement iphone1 = driver.findElement(By.xpath("//div[@data-id='MOBGHWFH2KZRNRH5']//ancestor::div[@class='MIXNux']//following-sibling::div[@class='_3pLy-c row']//div//div[@class='_4rR01T']"));
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
		int first_iphoneprice = Integer.parseInt(price1);
		System.out.println("Product1 iphone price: " + first_iphoneprice);
		WebElement addtocart1 = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']"));
		addtocart1.click();
		
		String parenttab = "Iphone- Buy Products Online at Best Price in India - All Categories | Flipkart.com";
		for (String s : windowHandles) {
			if (driver.switchTo().window(s).getTitle().equals(parenttab)) {
				break;
			}
		}
		// 5.pick second product
		
		//WebElement iphone2 = driver.findElement(By.xpath("//div[@class='_1AtVbE col-12-12']//child::a[@class='_1fQZEK']//descendant::div[@class='_3pLy-c row']//descendant::div[@class=\"col col-7-12\"]//div[text()='APPLE iPhone 13 (Midnight, 128 GB)']"));
		WebElement iphone2 = driver.findElement(By.xpath("//div[@class=\"_1AtVbE col-12-12\"]//ancestor::div[@class=\"_2kHMtA\"]//ancestor::div[@class=\"_3pLy-c row\"]//ancestor::div[text()='APPLE iPhone 11 (Black, 64 GB)']"));
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
		int second_iphoneprice = Integer.parseInt(price2);
		System.out.println("Product2 iphone price: " + second_iphoneprice);
		WebElement addtocart2 = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']"));
		addtocart2.click();

		int productprice = first_iphoneprice + second_iphoneprice;
		System.out.println("product 1 & product 2 Total Price: " + productprice);

		for (String s1 : windowHandles1) {
			if (driver.switchTo().window(s1).getTitle().equals(parenttab)) {
				break;
			}
		}
		WebElement cart = driver.findElement(By.xpath("//a[@href='/viewcart?exploreMode=true&preference=FLIPKART']"));
		cart.click();

		// get cart prices

		WebElement cartprice = driver.findElement(By.xpath(
				"//div[@class='_1YokD2 _3Mn1Gg col-4-12 _78xt5Y']//descendant::div[@class='_I_XQO']//descendant::div[@class='Ob17DV']//descendant::div[@class='_24KATy']//div[text()='Price (2 items)']//ancestor::div[@class='Ob17DV']//span"));
		System.out.println("Total cart price" + cartprice.getText());
		String cartprices = cartprice.getText().replaceAll("[₹,]", "");
		int total_price = Integer.parseInt(cartprices);
		System.out.println("total cart prices: " + total_price);

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\tvija\\eclipse-workspace\\Maven_Project\\Screenshot\\flipkart.png");
		FileUtils.copyFile(source, destination);

		// check price comaparision

		if (total_price == productprice) {
			System.out.println("The price is equal to cart price");
			driver.quit();
		}
	}
}
