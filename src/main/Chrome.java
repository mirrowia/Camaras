package main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome {
	
	//Creating a driver object referencing WebDriver interface
    private WebDriver driver;
 
    
    //Getters and Setters
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	//Contructor
	public  Chrome() {
		 
		//Setting the webdriver.chrome.driver property to its executable's location
		String os = System.getProperty("os.name");

		System.out.println("Systema operativo detectado: " + System.getProperty("os.name"));
		
		
		if (os.equalsIgnoreCase("Windows")) {
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
		
		}else if(os.equalsIgnoreCase("Linux")) {
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver");
		
		}
				
		ChromeOptions options = new ChromeOptions(); 
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
		
		System.out.println(System.getProperty("user.dir") + "/driver/chromedriver.exe");
		
		//Instantiating driver object
		driver = new ChromeDriver(options); 

	}

}
