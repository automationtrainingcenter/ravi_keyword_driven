package framework.keyworddriven;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Keywords {

	WebDriver driver;
	Actions actions;

	// openBrowser
	public void openBrowser(String locType, String locValue, String data) {
		if (data.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", GenericHelper.getFilePath("drivers", "chromedriver.exe"));
			driver = new ChromeDriver();
		} else if (data.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", GenericHelper.getFilePath("drivers", "geckodriver.exe"));
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// create Actions class object
		actions = new Actions(driver);
	}

	// navigate
	public void navigate(String locType, String locValue, String data) {
		driver.get(data);
	}

//	type
	public void type(String locType, String locValue, String data) {
		WebElement ele = driver.findElement(LocatorHelper.locateElement(locType, locValue));
		ele.clear();
		ele.sendKeys(data);
	}

//	click
	public void click(String locType, String locValue, String data) {
		driver.findElement(LocatorHelper.locateElement(locType, locValue)).click();
	}

	// wait
	public void wait(String locType, String locValue, String data) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	moveToElement
	public void moveToElement(String locType, String locValue, String data) {
		WebElement ele = driver.findElement(LocatorHelper.locateElement(locType, locValue));
		actions.moveToElement(ele).build().perform();
	}

//	moveToEleAndClick
	public void moveToEleAndClick(String locType, String locValue, String data) {
		WebElement ele = driver.findElement(LocatorHelper.locateElement(locType, locValue));
		actions.moveToElement(ele).click().build().perform();
	}

//	switchToFrame
	public void switchToFrame(String locType, String locValue, String data) {
		WebElement frame = driver.findElement(LocatorHelper.locateElement(locType, locValue));
		driver.switchTo().frame(frame);
	}

//	select
	public void select(String locType, String locValue, String data) {
		Select select = new Select(driver.findElement(LocatorHelper.locateElement(locType, locValue)));
		select.selectByVisibleText(data);

	}

//	switchToDefaultContent
	public void switchToDefaultContent(String locType, String locValue, String data) {
		driver.switchTo().defaultContent();
	}

//	closeBrowser
	public void closeBrowser(String locType, String locValue, String data) {
		driver.close();
	}

	// switchToWindow
	// based on the child window number we are switching
	public void switchToWindow(String locType, String locValue, String data) {
		List<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(Integer.parseInt(data)));
	}

	// acceptAlert
	public void acceptAlert(String locType, String locValue, String data) {
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
	}

}
