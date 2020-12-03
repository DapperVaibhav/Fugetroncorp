package MobileTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Appium {
public static void main(String[] args) {
		
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Galaxy M01");
		caps.setCapability("udid", "R9ZN905E9DK"); 
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "10.0");
		caps.setCapability("appPackage", "com.google.android.gm");
		caps.setCapability("appActivity", "com.google.android.gm.ui.MailActivityGmail");
		caps.setCapability("noReset", "true");
		
	
		try {
AppiumDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
driver.findElement(By.id("com.google.android.gm:id/welcome_tour_got_it")).click();
driver.findElement(By.id("com.google.android.gm:id/setup_addresses_add_another")).click();
driver.findElements(By.id("com.google.android.gm:id/account_setup_item")).get(0).click();

	
		
		} 
		
		catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
	}

}
