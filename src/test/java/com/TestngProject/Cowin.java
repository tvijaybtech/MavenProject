package com.TestngProject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

@Test
public class Cowin {

	public static WebDriver driver;
	public static String searchState, searchDistrict;
	public static String choosed_age, choosed_dose, choosed_cost, choosed_vaccine;
	public static String showresults_age, showresults_dose, showresults_cost, showresults_vaccine;

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
		driver.get("https://www.cowin.gov.in/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
    @Test(priority=-2)
	public static void scroll() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement scroll = driver.findElement(By.xpath("//p[text()='Download Your Vaccine ']"));
		js.executeScript("arguments[0].scrollIntoView();", scroll);

	}
    @Test(priority=-1)
	public static void selectState() throws InterruptedException {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement selectState = driver.findElement(By.xpath(
				"//div[@class='searchBox ng-star-inserted']//child::div[@class='pullleft nopaddingcls col-sm-6 col-md-5 col-xs-12']//descendant::div[@class='mat-form-field-infix ng-tns-c58-351']//child::mat-select[@role='combobox']//child::div[@class='mat-select-trigger ng-tns-c69-352']"));
		js.executeScript("arguments[0].click();", selectState);

		searchState = "Tamil Nadu";

		List<WebElement> state = driver.findElements(By.xpath("//mat-option[@role='option']"));

		for (int i = 1; i <= state.size(); i++) {

			WebElement eachState = driver.findElement(By.xpath("//mat-option[@role='option'][" + i + "]"));

			String eachStateText = eachState.getText();

			if (eachStateText.equalsIgnoreCase(searchState)) {
				eachState.click();
				System.out.println("Selected State is :" + eachState.getText());
				break;
			}
		}

//		WebElement state = driver.findElement(By.xpath("//mat-option[@role='option']//span[text()=' Tamil Nadu ']"));
//		js.executeScript("arguments[0].click();", state);
//		System.out.println("Selected State is :" +state.getText());

		Thread.sleep(3000);
	}

    @Test
	public static void selectDisrict() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement selectDistrict = driver.findElement(By.id("mat-select-value-7"));
		js.executeScript("arguments[0].click();", selectDistrict);

		searchDistrict = "Chennai";

		List<WebElement> district = driver.findElements(By.xpath("//mat-option[@role='option']"));

		for (int i = 1; i <= district.size(); i++) {

			WebElement eachDistrict = driver.findElement(By.xpath("//mat-option[@role='option'][" + i + "]"));

			String eachDistrictText = eachDistrict.getText();

			if (eachDistrictText.equalsIgnoreCase(searchDistrict)) {
				eachDistrict.click();
				System.out.println("Selected District is :" + eachDistrict.getText());
				break;
			}
		}

//		WebElement district = driver.findElement(By.xpath("//mat-option//span[text()=' Chennai ']"));
//		js.executeScript("arguments[0].click();", district);
//		System.out.println("Selected District is :" +district.getText());
	}
   
    @Test(priority=1)
	public static void search() throws InterruptedException {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	WebElement search = driver.findElement(
				By.xpath("//button[@class='searchBtn pin-search-btn district-search accessibility-plugin-ac']"));
		js.executeScript("arguments[0].click();", search);

		Thread.sleep(3000);
	}
    
    @Test(dependsOnMethods="search")
	public static void choosedOptions() throws InterruptedException {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement age = driver.findElement(By.xpath("//label[text()='18+']"));
		js.executeScript("arguments[0].click();", age);
		choosed_age = age.getText();
		System.out.println("choosed age : " + choosed_age);

		Thread.sleep(3000);

		WebElement dose = driver.findElement(By.xpath("//label[text()='Dose 2']"));
		js.executeScript("arguments[0].click();", dose);
		choosed_dose = dose.getText();
		System.out.println("choosed dose : " + choosed_dose);

		Thread.sleep(3000);

		WebElement cost = driver.findElement(By.xpath("//label[text()='Paid']"));
		js.executeScript("arguments[0].click();", cost);
		choosed_cost = cost.getText();
		System.out.println("choosed cost : " + choosed_cost);

		Thread.sleep(3000);

		WebElement vaccine = driver.findElement(By.xpath("//label[text()='Covishield']"));
		js.executeScript("arguments[0].click();", vaccine);
		choosed_vaccine = vaccine.getText();
		System.out.println("choosed vaccine : " + choosed_vaccine);

	}
    @Test(dependsOnMethods="choosedOptions")
	public static void searchResultCentre() {

		WebElement searchresults = driver.findElement(By.xpath(
				"//div[@class='search-with-walkin']//descendant::span[@class='center-count accessibility-plugin-ac']"));
		System.out.println("Searched Results For Centre:" + searchresults.getText());
	}
    
    @Test(priority=2)
	public static void showingResults() {

		List<WebElement> showingresult = driver
				.findElements(By.xpath("//div[@class='show-result']//descendant::li[@class='ng-star-inserted']"));
		System.out.println("Showing Results For :");
		for (WebElement s : showingresult) {
			String showingresults = s.getText();
			System.out.println(showingresults);
		}
	}
    
    @Test(dependsOnMethods="showingResults")
	public static void showingResultsOptions() {

		WebElement agesearch = driver.findElement(By
				.xpath("//div[@class='show-result']//descendant::li[@class='ng-star-inserted']//span[text()='18+ ']"));
		showresults_age = agesearch.getText();
		System.out.println(showresults_age);

		WebElement costsearch = driver.findElement(By
				.xpath("//div[@class='show-result']//descendant::li[@class='ng-star-inserted']//span[text()='Paid']"));
		showresults_cost = costsearch.getText();
		System.out.println(showresults_cost);

		WebElement dosesearch = driver.findElement(
				By.xpath("//div[@class='show-result']//descendant::li[@class='ng-star-inserted']//span[text()='2']"));
		showresults_dose = dosesearch.getText();
		System.out.println(showresults_dose);

		WebElement vaccinesearch = driver.findElement(By.xpath(
				"//div[@class='show-result']//descendant::li[@class='ng-star-inserted']//span[text()='COVISHIELD']"));
		showresults_vaccine = vaccinesearch.getText();
		System.out.println(showresults_vaccine);
	}
    
    @Test(priority=3)
	public static void checkShowingResults() {

		if (choosed_age.equalsIgnoreCase(showresults_age) && choosed_cost.equalsIgnoreCase(showresults_cost)
				&& choosed_vaccine.equalsIgnoreCase(showresults_vaccine)) {
			if (showresults_dose.contains(showresults_dose)) {
				System.out.println("selected value & searched results value is equal");
			}
		}
	}
    @Test(priority=4)
	public static void searchedCentre() {

		List<WebElement> totalproduct = driver.findElements(By.xpath(
				"//div[@class='mat-main-field center-main-field']//descendant::div[@class='row']//div[@class='col-sm-12 col-md-12 col-lg-12 cvc-list-item ng-star-inserted']//div[@class='main-slider-wrap col col-lg-3 col-md-3 col-sm-3 col-xs-12']"));
		System.out.println(totalproduct.size());
		for (WebElement searchedCentre : totalproduct) {
			String totalcentre = searchedCentre.getText();
			System.out.println(totalcentre);
		}
	}
    @Test(priority=5)
	public void checkSearchedCentreValues() {

		List<WebElement> totalproduct = driver.findElements(By.xpath(
				"//div[@class='mat-main-field center-main-field']//descendant::div[@class='row']//div[@class='col-sm-12 col-md-12 col-lg-12 cvc-list-item ng-star-inserted']//div[@class='main-slider-wrap col col-lg-3 col-md-3 col-sm-3 col-xs-12']"));
		System.out.println(totalproduct.size());
		for (WebElement searchedCentre : totalproduct) {
			String totalcentre = searchedCentre.getText();
			System.out.println(totalcentre);
		}

		List<WebElement> check = driver
				.findElements(By.xpath("//div[@class='col-sm-12 col-md-12 col-lg-12 cvc-list-item ng-star-inserted']"));

		for (int i = 1; i <= check.size(); i++) {

			WebElement centre = driver.findElement(By
					.xpath("//div[@class='col-sm-12 col-md-12 col-lg-12 cvc-list-item ng-star-inserted'][" + i + "]"));

			String eachCentre = centre.getText();

			String choosed_Age = "18";
			String choosed_Dose = "#2";
			String choosed_Cost = "PAID";
			String choosed_Vaccine = "COVISHIELD";

			if (eachCentre.contains(searchState) && eachCentre.contains(searchDistrict)
					&& eachCentre.contains(choosed_Age) && eachCentre.contains(choosed_Dose)
					&& eachCentre.contains(choosed_Cost) && eachCentre.contains(choosed_Vaccine)) {

				System.out.println("selected value & searched results value is equal :" +i+ "centre");

			} else {
				System.out.println("selected value & searched results value is not equal");
			}
		}
	}
    
    @Test(dependsOnMethods="checkSearchedCentreValues")
	public static void takeScreenshot() throws IOException {

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\tvija\\eclipse-workspace\\Maven_Project\\Screenshot\\cowin.png");
		FileUtils.copyFile(source, destination);

	}
    @AfterSuite
	public static void quit() {
		driver.quit();
	}
}
