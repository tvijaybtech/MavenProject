package googleCreate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class baseClass {
	public static WebDriver driver;
	public static void urllaunch(WebDriver driver, String value) {
		driver.get(value);
	}

	public static void inputElement(WebElement element, String value) {
		element.sendKeys(value);

	}

	public static void clickonElement(WebElement element) {
		element.click();
	}
	public static void windowMax() {
		

	}

}
