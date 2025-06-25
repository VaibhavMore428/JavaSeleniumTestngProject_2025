package utilities;

import java.util.Random;

public class RandomTestDataUtil {
	// Dynamic value generators
	static String generateRandomEmail() {
		return "user" + System.currentTimeMillis() + "@mail.com";
	}

	static String generateRandomName() {
		return "User" + new Random().nextInt(100000);
	}

	static String generateRandomMobile() {
		return "9" + (long) (Math.random() * 1000000000L);
	}

	static String generateRandomPassword() {
		int number = new Random().nextInt(1000);
		return "Pwd@" + String.format("%04d", number);
	}
}
