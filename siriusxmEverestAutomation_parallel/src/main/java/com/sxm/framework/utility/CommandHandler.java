package com.sxm.framework.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Generic class to handle command line executions
 * 
 * @author subramanyamp
 *
 */
public class CommandHandler {

	public static String executeCommand(String command) {
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}

	public static void main(String[] args) {
		String output = CommandHandler.executeCommand("cmd /c dir");
		System.out.println(output);
	}
}