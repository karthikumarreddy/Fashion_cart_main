package com.fashioncart.command;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class CommandFactory {
	private CommandFactory() {

	}

	public static Map<String, CommandConfig> configMap = null;
	static Properties commandProperties = new Properties();
	static {
		try (InputStream is = CommandFactory.class
				.getClassLoader()
				.getResourceAsStream("resources/commands.properties")) {
			if (is == null) {
				throw new RuntimeException("commands.properties file not found in classpath");
			}
			commandProperties.load(is);

			if (commandProperties != null) {
				configMap = CommandConfig.loadConfiguration(commandProperties);

			}
			System.out.println("Loaded commands: " + configMap.keySet());
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("Failed to load command mappings", e);

		}

	}

	public static Command getCommand(String action) {
		try {
			if (action == null) {
				return null;
			}
			String commandClassname=configMap.get(action).getCommandClass();
			if(commandClassname==null) {
				return null;
			}
			Class<?> clazz=Class.forName(commandClassname);
			return (Command)clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		

	}

}
