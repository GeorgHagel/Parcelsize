package test.java.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;


public class ParcelSizeGUITest {
	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
		  
		  DesiredCapabilities capability = DesiredCapabilities.phantomjs();
		  
		  capability.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/bin/phantomjs");
		driver = new PhantomJSDriver(capability);
	    //driver = new ChromeDriver();
	    baseUrl = "https://www.katalon.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testPacelSizeGUITestID() throws Exception {
	    driver.get("http://192.168.56.102:8888/ParcelWebserver/");
	    driver.findElement(By.id("cfg-size-length")).click();
	    driver.findElement(By.id("cfg-size-length")).clear();
	    driver.findElement(By.id("cfg-size-length")).sendKeys("20");
	    driver.findElement(By.id("cfg-size-height")).click();
	    driver.findElement(By.id("cfg-size-height")).clear();
	    driver.findElement(By.id("cfg-size-height")).sendKeys("20");
	    driver.findElement(By.id("cfg-size-depth")).click();
	    driver.findElement(By.id("cfg-size-depth")).clear();
	    driver.findElement(By.id("cfg-size-depth")).sendKeys("20");
	    driver.findElement(By.id("cfg-size-button")).click();
	    Thread.sleep(5000);
	    assertEquals("Paketgröße: S", driver.findElement(By.id("cfg-size-res")).getText());
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
}
