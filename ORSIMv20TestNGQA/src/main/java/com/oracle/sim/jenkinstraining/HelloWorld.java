package com.oracle.sim.jenkinstraining;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorld {

	public static void main(String[] args) {
	System.out.println("Hello World");
	
	System.setProperty("webdriver.chrome.driver","C:\\workspace\\jenkinstraining\\drivers\\chromedriver77.exe"); 
	WebDriver driver=new ChromeDriver();  
	driver.get("https://www.seleniumhq.org/");

	}

}
