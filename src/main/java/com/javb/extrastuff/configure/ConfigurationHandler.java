package com.javb.extrastuff.configure;

import java.io.File;

import com.javb.extrastuff.utility.LogHelper;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {
	public static void init(File configFile) {
		// Create the configuration object from the given configuration file.
		Configuration configuration = new Configuration(configFile);
		
		try {
			// Load the configuration file.
			configuration.load();
			
			// Read configuration file.
			boolean test = configuration.get(Configuration.CATEGORY_GENERAL, "test_config_file", true, "test boolean").getBoolean(true);
			LogHelper.info("test= " + test);
		} catch (Exception e) {
			LogHelper.error("failed to load config");
			// log exception.
		} finally {
			
			// Save the configuration file.
			configuration.save();
		}
	}
}
