package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	private static Properties prop = new Properties();
		static {
			try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("proertiesFiles/config.properties")){
					if(input ==null) {
						throw new RuntimeException("Sorry, unable to find config.properties");
					}
					prop.load(input); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static String getProperty(String key) {
			return prop.getProperty(key);
		}
}
