package com.AmazonProject;

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
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonProject {

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
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String selectValue = "babY";

		WebElement dropDown = driver.findElement(By.id("searchDropdownBox"));

		Select s = new Select(dropDown);

		List<WebElement> dropdownOptions = s.getOptions();

		for (int i = 0; i < dropdownOptions.size(); i++) {

			WebElement eachoption = dropdownOptions.get(i); // index- webelement

			String eachText = eachoption.getText();

			if (eachText.equalsIgnoreCase(selectValue)) {
				s.selectByVisibleText(eachText);
			}
		}

		String searchProduct = "Soft Toys";

		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys(searchProduct);

		Thread.sleep(2000);

		List<WebElement> relevant = driver
				.findElements(By.xpath("//div[@class='autocomplete-results-container']/child::div"));

		for (int i = 1; i <= relevant.size(); i++) {

			WebElement eachProduct = driver.findElement(By.xpath(
					"//div[@class='autocomplete-results-container']/child::div[" + i + "]/div/div[@role='button']"));

			String eachProductText = eachProduct.getText();

			if (eachProductText.equalsIgnoreCase(searchProduct)) {
				eachProduct.click();
				break;
			}

		}
		Thread.sleep(3000);
		String parentTab = driver.getWindowHandle();
		String Currenttitle = driver.getTitle();
		System.out.println(Currenttitle);
		
		//WebElement firstproduct = driver.findElement(By.xpath("//div[@data-cel-widget='search_result_1']//following-sibling::div[@data-cel-widget='search_result_2']//descendant::div[@class='a-section a-spacing-small puis-padding-left-small puis-padding-right-small']"));
		WebElement firstproduct = driver.findElement(By.xpath("//span[text()='RESULTS']/ancestor::div[4]/following-sibling::div[1]//h2"));
		String firstProductTitle = firstproduct.getText();
		System.out.println(firstProductTitle);
		//firstproduct.click();
		
		WebElement firstProductOffer = driver.findElement(By.xpath("//span[text()='RESULTS']/ancestor::div[4]/following-sibling::div[1]//span[@class='a-price']"));
		String firstProductOfferPrice = firstProductOffer.getText();
		System.out.println("First Product Offer Price" +firstProductOfferPrice);
		
		firstproduct.click();
		
		Thread.sleep(3000);
		Set<String> allTabs = driver.getWindowHandles();
		for (String totalTab : allTabs) {

			if (!(totalTab.equals(parentTab))) {
				driver.switchTo().window(totalTab);
			}
		}
		System.out.println(driver.getTitle());

		WebElement addtocart = driver.findElement(By.id("add-to-cart-button"));
		addtocart.click();
		System.out.println("Add To Carted");

		WebElement cart = driver.findElement(By.xpath("//a[@href='https://www.amazon.in/gp/cart/view.html?ref_=nav_cart']"));
		cart.click();
		System.out.println("Click Cart");

		WebElement proceedtobuy = driver.findElement(By.xpath("//div[@class='a-section sc-buy-box-inner-box']//descendant::span[@id='sc-buy-box-ptc-button']"));
		proceedtobuy.click();
		System.out.println("Click Proceed To Buy");

		Thread.sleep(3000);
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\tvija\\eclipse-workspace\\Maven_Project\\Screenshot\\amazon.png");
		FileUtils.copyFile(source, destination);
		System.out.println("Capture Screenshot");
		driver.quit();
	}
}
