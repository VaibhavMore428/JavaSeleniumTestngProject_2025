package utilities;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;

public class BrowsercCapabilities {
	public static void chromeBrowerCapabilities() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-save-password-bubble");

		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
	}
}

