package com.OrangeHRM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHrm {

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
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		WebElement username = driver.findElement(By.xpath("//p[text()='Username : Admin']"));
        String uname = username.getText();
        System.out.println(uname);
        String USERNAME="";
        String[] split = uname.split(" ");
        for(int i=2;i<=split.length-1;i++) {
        	USERNAME=USERNAME+split[i];
        }
         System.out.println(USERNAME);
        WebElement pass = driver.findElement(By.xpath("//p[text()='Password : admin123']"));
        String password = pass.getText();
        System.out.println(password);
        String PASSWORD="";
        String[] split1 = password.split(" ");
        for(int i=2;i<=split1.length-1;i++) {
        	PASSWORD=PASSWORD+split1[i];
        }
        System.out.println(PASSWORD);
       
        WebElement labelname = driver.findElement(By.xpath("//label[text()='Username']"));
        String Username = labelname.getText();
	    if(uname.contains(Username)) {
	    	WebElement user = driver.findElement(By.xpath("//input[@placeholder='Username']"));
	    	user.sendKeys(USERNAME);
	    }
	
	    WebElement labelpass = driver.findElement(By.xpath("//label[text()='Password']"));
	    String Password = labelpass.getText();
	    if(password.contains(Password)) {
	    	WebElement pwd = driver.findElement(By.xpath("//input[@placeholder='Password']"));
	    	pwd.sendKeys(PASSWORD);
	    }
	
	    WebElement login = driver.findElement(By.xpath("//button[@type='submit']"));
	    login.click();
	}
}