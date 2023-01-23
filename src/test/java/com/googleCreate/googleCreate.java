package com.googleCreate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import googleCreate.baseClass;

public class googleCreate extends baseClass {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\tvija\\eclipse-workspace\\maven1\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		urllaunch(driver,
				"https://accounts.google.com/signup/v2/webcreateaccount?biz=false&cc=IN&dsh=S755855521%3A1671542529834769&flowEntry=SignUp&flowName=GlifWebSignIn&ifkv=AeAAQh5aHiNhWqQO3gxJC9nj_mSr6j94mhyXAG2OaV761iW4CNum6Y1kd5pnHiYTxTWKBzYXa34P3Q");

		WebElement fname = driver.findElement(By.xpath("(//input[@type='text'])[1]"));
		inputElement(fname, "Vijay");

		WebElement lname = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
		inputElement(lname, "Kumar");

		WebElement cb = driver.findElement(By.xpath("//input[@class='VfPpkd-muHVFf-bMcfAe']"));
		clickonElement(cb);
		Thread.sleep(3000);

		WebElement signin = driver.findElement(By.xpath("(//span[@jsname='V67aGc'])[3]"));
		clickonElement(signin);
	}
}
