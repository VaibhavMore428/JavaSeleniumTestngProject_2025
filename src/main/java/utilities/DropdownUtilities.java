package utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownUtilities {

	Select sel;

	public static void selectDropdownValue(WebElement ele, String value) {

		Select sel = new Select(ele);
		sel.selectByVisibleText(value);
	}
}
