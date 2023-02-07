package main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import settings.SettingsLoader;

public class Main {
	
	static Chrome chrome;
	
	static String sector;

	public static void main(String[] args) {
		
		SettingsLoader st = new SettingsLoader();
		
		String ip = st.getIp();
		String user = st.getUser();
		String pw = st.getPw();
		String[] camNames = st.getCameras();
		
		chrome = new Chrome();
		
		WebElement we;
		
		chrome.getDriver().manage().window().maximize();
		
		chrome.getDriver().get(ip);

		we = findElement("details-button");
		we.click();
		
		we = findElement("proceed-link");
		we.click();
		
		we = findElement("loginUsername-inputEl");
		we.sendKeys(user);
		
		we = findElement("loginPassword-inputEl");
		we.sendKeys(pw);
		
		we = findElement("loginButton-btnIconEl");
		we.click();
		
		we = findElementByPath("//span[contains (text(), 'EN VIVO' )]");
		we.click();
		
		for (String string : camNames) {
			
			mainChannel(string);
			
		}

		chrome.getDriver().manage().window().fullscreen();
		
		we = findElement("button-1222-btnIconEl");
		
		sleep(1000);
		
		we.click();
		
		System.out.println("TUKI *Ruido de click*");
		
	}

	private static void mainChannel(String camName) {
		
		WebElement we = findElementByPath("//span[contains (text(), '" + camName + "' )]");
		
		String childId = we.getAttribute("id");
		
		String parentId = childId.replace("-btnInnerEl", "");
		
		we = findElement(parentId);
			
		Actions builder = new Actions(chrome.getDriver());
		
		//int H = we.getRect().height;
		int W = we.getRect().width;
		
		//System.out.println(chrome.getDriver().findElement(By.id(parentId)).getSize());

		//System.out.println("H = " + H);
		//System.out.println("W = " + W);
		
		builder.moveToElement(we, W/2, 0).click().perform();
		
		List <WebElement> elementList = findElementsByPath("//span[contains (text(), 'Corriente' )]");
		//System.out.println(elementList.size());
		
		elementList.get(elementList.size()-1).click();
		
	}
	
	private static WebElement findElement (String element) {
		
		WebElement we = null;
		
		if(elementExist(element, 15)) {
			
			System.out.println("Elemento encontrado: " + element);
			
			we = chrome.getDriver().findElement(By.id(element));
			
		} else {
			
			we = null;
			
		}
		
		return we;
		
	}
	
	private static WebElement findElementByPath (String element) {
		
		WebElement we = null;
		
		if(elementExistXpath(element, 15)) {
			
			we = chrome.getDriver().findElement(By.xpath(element));
			
		} else {
			
			we = null;
			
		}
		
		return we;
		
	}
	
	private static List <WebElement> findElementsByPath (String element) {
		
		List <WebElement> we = null;
		
		if(elementsExistXpath(element, 15)) {
			
			we = chrome.getDriver().findElements(By.xpath(element));
			
		} else {
			
			we = null;
			
		}
		
		return we;
		
	}
	
 	private static boolean elementExist(String element, int time) {
		
		boolean exist = false;
		
		boolean run = true;
		
		int i = 0;
		
		String error = null;

		while (run && (time *2) >= i ) {
			
			try {
				
				chrome.getDriver().findElement(By.id(element));

				exist = true;
				
				run = false;
				
				error = null;
				
			} catch (NoSuchElementException e) {
				
				i++;
				
				sleep(500);
				
				error = e.toString();
				
				exist = false;
				
			} catch (Exception ex) {
				
				i = 20;
				
				error = ex.toString();
				
				exist = false;
				
				run = false;
			}
			
		}
		
		if (error != null) {
			
			System.out.println(error);
			
		}
		
		return exist;
		
	}
	
 	private static boolean elementExistXpath(String element, int time) {
		
		boolean exist = false;
		boolean run = true;
		int i = 0;
		String error = null;
		
		while (run && (time *2) >= i ) {
			
			try {
				
				chrome.getDriver().findElement(By.xpath(element));
				
				exist = true;
				
				error = null;
				
				run = false;

			} catch (NoSuchElementException e) {
				
				i++;
				
				sleep(500);
				
				error = e.toString();
				
				exist = false;
				
			} catch (ElementNotInteractableException enie) {
				
				i++;
				
				sleep(500);
				
				error = enie.toString();
				
				exist = false;
				
			} catch (Exception ex) {
				
				i = 20;
				
				error = ex.toString();
				
				exist = false;

			}
			
		}
		
		if (error != null) {
			System.out.println(error);
		}
		
		return exist;
		
	}
	
 	private static boolean elementsExistXpath(String element, int time) {
		
		boolean exist = false;
		boolean run = true;
		int i = 0;
		String error = null;
		
		while (run && (time *2) >= i ) {
			
			try {
				
				chrome.getDriver().findElements(By.xpath(element));
				
				exist = true;
				
				run = false;
				
				//System.out.println("1");
				
			} catch (NoSuchElementException e) {
				
				i++;
				
				sleep(500);
				
				error = e.toString();
				
				exist = false;
				
				System.out.println("2");
				
			} catch (Exception ex) {
				
				i = 20;
				
				error = ex.toString();
				
				exist = false;
				
				System.out.println("3");
			}
			
		}
		
		if (error != null) {
			System.out.println(error);
		}
		
		return exist;
		
	}
	
	public static void sleep (int miliseconds) {
		try {
			
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
