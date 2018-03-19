/**
 * 
 */
package com.openthinks.bookmarks.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.openthinks.libs.utilities.logger.ProcessLogger;

/**
 * @author dailey.yet@outlook.com
 *
 */
public final class Configs {
	public static final String PATH_CONFIG_FILE = "bmc.cfg.path";
	public static final String PATH_STORE_DB = "db.nosql.path";
	////////////////////////////////////////////////////////////////////////////////
	public static final String DEFAULT_VAL_CONFIG_FILE_NAME = "bmc.cfg";
	public static final String DEFAULT_VAL_STORE_DB_NAME = "bmd.odb";

	private static final File DEFAULT_DIR_CONFIG_FILE = new File(System.getProperty("user.dir"));

	private static final PropertiesConfig DEFAULT_CONFIG = new PropertiesConfig() {
		private static final long serialVersionUID = 7717765134591866186L;
		{
			put(PATH_STORE_DB, new File(DEFAULT_DIR_CONFIG_FILE, DEFAULT_VAL_STORE_DB_NAME).getAbsolutePath());

			/////////////////////////////////////////////////////
			// add other default configure item here
			// =============================^
			// =============================^
			// =============================^
			// =============================^
			// =============================^

		}
	};

	private static PropertiesConfig runtimeConfig = DEFAULT_CONFIG;
	////////////////////////////////////////////////////////////////////////////////
	public static final String DATA_ROOT_NAME_KEY = "DATA_ROOT";
	public static final String MAIN_BOOKMARK_KEY = "MAIN_BOOKMARK";

	static {
		File file = getConfigFile();
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(file));
			runtimeConfig = new PropertiesConfig(props);
		} catch (Exception e) {
			runtimeConfig = DEFAULT_CONFIG;
			ProcessLogger.warn("Use default configure file");
			try {
				runtimeConfig.store(new FileOutputStream(file), "This is a default configuration!!!");
			} catch (IOException e1) {
				ProcessLogger.error(e1);
			}
		}
	}

	public static Config getInstance() {
		return runtimeConfig;
	}

	public static String getTemplateAsString(String resource) {
		BufferedReader br = new BufferedReader(new InputStreamReader(Configs.class.getResourceAsStream(resource)));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public interface Config {
		String get(String key);
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	////////////////////////////////////////////////////////////////////////////////
	private static File getConfigFile() {
		String configureFilePath = System.getProperty(PATH_CONFIG_FILE);
		if (configureFilePath == null) {
			configureFilePath = System.getenv(PATH_CONFIG_FILE);
		}
		if (configureFilePath == null) {
			File file = new File(DEFAULT_DIR_CONFIG_FILE, DEFAULT_VAL_CONFIG_FILE_NAME);
			configureFilePath = file.getAbsolutePath();
		}
		File file = new File(configureFilePath);
		return file;
	}

	static class PropertiesConfig extends Properties implements Config {
		private static final long serialVersionUID = 7859766497251255940L;

		public PropertiesConfig() {
			super();
		}

		public PropertiesConfig(Properties defaults) {
			super(defaults);
		}

		@Override
		public String get(String key) {
			return getProperty(key);
		}

	}

}
