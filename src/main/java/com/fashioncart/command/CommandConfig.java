package com.fashioncart.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CommandConfig {
	private String commandClass;
	private String successPage;
	private String failurePage;
	public CommandConfig(String commandClass, String successPage, String failurePage) {
		super();
		this.commandClass = commandClass;
		this.successPage = successPage;
		this.failurePage = failurePage;
	}
	public String getCommandClass() {
		return commandClass;
	}
	public String getSuccessPage() {
		return successPage;
	}
	public String getFailurePage() {
		return failurePage;
	}
	
	public static Map<String,CommandConfig>loadConfiguration(Properties commandProperties){
		
		Map<String, CommandConfig> configMap = new HashMap<String, CommandConfig>();
		if(commandProperties !=null) {
			for(String key:commandProperties.stringPropertyNames()) {
				String value=commandProperties.getProperty(key);
				String[] tokens=value.split(",");
				String commandClass=tokens[0];
				String success="";
				String failure="";
				for(int i=0;i<tokens.length;i++) {
					String[] pair=tokens[i].split("=");
					if(pair[0].equals("success")) {
						success=pair[1];
					}else if(pair[0].equals("failure")) {
						failure=pair[1];
					}
				}
				CommandConfig cmdConfig=new CommandConfig(commandClass, success, failure);
				configMap.put(key, cmdConfig);
				
			}
			System.out.println("ConfigMap: "+configMap.get("listProducts").getSuccessPage());
		}
		return configMap;
	}

}
