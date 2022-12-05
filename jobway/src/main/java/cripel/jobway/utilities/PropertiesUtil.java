package cripel.jobway.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Properties;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * The Utilities class to manage the property and path
 */
public class PropertiesUtil {

	private static final String USERPROFILE = "user.home";

	private PropertiesUtil(){}
	
	/**
	 * Load properties in path
	 *
	 * @param path
	 */
	public static Properties loadProperties(String path) {
		Properties prop=new Properties();
		try(FileReader reader = new FileReader(path)) {
			prop.load(reader);
			return prop;
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
			return (Properties) Collections.emptyMap();
		}
	}

	/**
	 * Create a directory in windows Appdata
	 *
	 * @param dirName name of the directory
	 */
	public static void createAppDataFolder(String dirName) {
		String userHome = System.getProperty(USERPROFILE);
		userHome += File.separator + dirName;
		File file = new File(userHome);

		if (file.mkdir()) {
			System.out.println("Folder created");
		} else {
			System.out.println("Folder exist");
		}

	}

	/**
	 * Create a file in windows appdata
	 *
	 * @param dirName  the directory name
	 * @param fileName the file name
	 */
	public static void createFile(String dirName, String fileName) {
		String userHome = System.getProperty(USERPROFILE);
		createAppDataFolder(dirName);
		File file = new File(userHome + File.separator + dirName + File.separator + fileName);

		try {
			if (file.createNewFile()) {
				System.out.println("Le fichier a été créé");
			} else {
				System.out.println("Le fichier existe déjà");
			}
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();

		}
	}

	/**
	 * Check if a file exists
	 *
	 * @param filename the file name
	 * @return boolean false if file does not exist
	 */
	public static boolean fileIsCreated(String filename) {
		boolean result = false;
		File file = new File(filename);
		if (file.isFile() && file.exists()) {
			result = true;
		}
		
		return result;
	}

	/**
	 * Create a property file with database information
	 *
	 * @param dbName     the database datasource name
	 * @param dbUser     the database username
	 * @param dbPassword the database password
	 */
	public static void saveDb(Properties prop,String dbName, String dbUser, String dbPassword) {
		
			String path = System.getProperty(USERPROFILE);
			path += File.separator + "JobWay" + File.separator + "config.properties";
		try(FileOutputStream output = new FileOutputStream(path)) {
			prop.setProperty("DBURL", dbName);
			prop.setProperty("DBUSER", dbUser);
			prop.setProperty("DBPASSWORD", dbPassword);
			prop.store(output, "Database info");
		} catch (IOException e) {

			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
		}
	}

	/**
	 * Create a default config if no config.properties was created
	 */
	public static Properties defaultConfig() {
			Properties prop=new Properties();
			createFile("JobWay", "config.properties");
			String path = System.getProperty(USERPROFILE);
			path += File.separator + "JobWay" + File.separator + "config.properties";
			try(OutputStream output = new FileOutputStream(path)) {
			prop.put("DBURL", "jdbc:mysql://localhost:3306/jobway");
			prop.put("DBUSER", "root");
			prop.put("DBPASSWORD", "root");
			prop.store(output, "Database info");
		} catch (IOException e) {

			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
			prop=(Properties) Collections.emptyMap();
		}
			return prop;
	}

	public static Properties loginDefaultConfig() {
		Properties prop=new Properties();
		createFile("JobWay", "login.properties");
		String path = System.getProperty(USERPROFILE);
		path += File.separator + "JobWay" + File.separator + "login.properties";
		try(OutputStream output = new FileOutputStream(path)) {
			prop.put("REMEMBERME", "false");
			prop.put("USERNAME", "");
			prop.store(output,"Login info");
		} catch (IOException e) {

			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
			prop=(Properties) Collections.emptyMap();
		}
		return prop;
	}
}
