package com.fashioncart.command;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * command factory is used to create the object for specific class
 */
public class CommandFactory {
	private static final Logger logger = LogManager.getLogger(CommandFactory.class);

	private CommandFactory() {

	}

	public static Map<String, CommandConfig> configMap = null;
	static Properties commandProperties = new Properties();

	/*
	 * loading the properties file into property
	 */
	static {
		try (InputStream is = CommandFactory.class.getClassLoader().getResourceAsStream("resources/commands.properties")) {
			if (is == null) {
				throw new RuntimeException("commands.properties file not found in classpath");
			}
			commandProperties.load(is);

			if (commandProperties != null) {
				configMap = CommandConfig.loadConfiguration(commandProperties);
			}

		} catch (IOException e) {
			logger.error("error in CommandFactory : " + e.getMessage());
			throw new RuntimeException("Failed to load command mappings", e);

		}
	}

	/*
	 * getting the action from the controller here creating the object for particular commandclass and returning the command type of object
	 */
	public static Command getCommand(String action) {
		try {
			if (action == null) {
				return null;
			}
			String commandClassname = configMap.get(action).getCommandClass();
			if (commandClassname == null) {
				return null;
			}
			Class<?> clazz = Class.forName(commandClassname);
			return (Command) clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			logger.error("error in CommandFactory : " + e.getMessage());
			return null;
		}

	}

}
