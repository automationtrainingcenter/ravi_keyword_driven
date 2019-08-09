package framework.keyworddriven;

import org.openqa.selenium.By;

public class LocatorHelper {
	static By by = null;
	public static By locateElement(String locType, String locValue) {
		locType = locType.toLowerCase();
		switch (locType) {
		case "id":
			by = By.id(locValue);
			break;
		case "name":
			by = By.name(locValue);
			break;
		case "xpath":
			by = by.xpath(locValue);
			break;
		case "css":
			by = By.cssSelector(locValue);
			break;
		case "linktext":
			by = By.linkText(locValue);
			break;
		case "partiallinktext":
			by = By.partialLinkText(locValue);
			break;
		case "tagname":
			by = By.tagName(locValue);
			break;
		case "classname":
			by = By.className(locValue);
			break;
		default:
			System.out.println("invalid locator type");
			break;
		}
		return by;
	}

}
