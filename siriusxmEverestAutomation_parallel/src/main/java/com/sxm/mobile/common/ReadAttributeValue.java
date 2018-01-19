package com.sxm.mobile.common;

import org.apache.commons.lang3.StringUtils;
import org.testng.Reporter;

public class ReadAttributeValue {

	public static String getAttributes(String keyword, String lookupKey, String xmlFile) {
		String[] kv = null;
		String value = null;
		try {

			// read the entire xml file as a String
			// List<String> xmlString = readLines(xmlFile,
			// Charset.defaultCharset());

			String[] xmlString = StringUtils.split(xmlFile, '\n');

			// iterate through each line
			for (String line : xmlString) {

				// search for the line that contains your keyword
				if (StringUtils.containsIgnoreCase(line, keyword)) {

					// extract the value for the lookup key
					// split the line based on space
					String[] keyValuePairs = line.split("\\s");
					for (String kvPair : keyValuePairs) {
						// split the keyValuePairs based on "="
						kv = kvPair.split("=");

						for (int i = 0; i < kv.length; i++) {
							if (kv[i].equals(lookupKey)) {
								value = kv[++i];
								System.out.println("Visible: "+value.replace("\"", ""));
							}
						}

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value.replace("\"", "");
	}
}
