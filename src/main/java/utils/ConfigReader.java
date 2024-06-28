package utils;

import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ConfigReader {
	Properties properties;

	// constructor which accepts the relative path of the property file apart from
	// base project path
	public ConfigReader() {
		String basePath = GeneralUtilities.GetProjectPath();

		String completeFilePath = basePath + "\\src\\test\\resources\\config.properties";

		File file = new File(completeFilePath);

		FileInputStream configFileReader = null;

		try {
			TestLogger.info("Reading property file from path " + completeFilePath);
			configFileReader = new FileInputStream(file);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		properties = new Properties();

		try {

			properties.load(configFileReader);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// returning instance of property file.
	public Properties GetProperty() {
		return properties;
	}

	// Reading property file based on key
	public String ReadProperty(String key) {
		TestLogger.info("Reading value from property file for key " + key);
		return properties.getProperty(key);
	}

	

}
