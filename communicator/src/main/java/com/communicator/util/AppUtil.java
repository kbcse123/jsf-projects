package com.communicator.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppUtil {
	private static ObjectMapper mapper = new ObjectMapper();

	public static String memoryConsumed(long startMem) {
		long totalMemory = Runtime.getRuntime().totalMemory();
		long afterUsedMem = totalMemory - Runtime.getRuntime().freeMemory();
		long bytes = afterUsedMem - startMem;
		long kilobyte = 1024;
		long megabyte = kilobyte * 1024;
		long gigabyte = megabyte * 1024;
		long terabyte = gigabyte * 1024;

		if ((bytes >= 0) && (bytes < kilobyte)) {
			return bytes + " B";

		} else if ((bytes >= kilobyte) && (bytes < megabyte)) {
			return (bytes / kilobyte) + " KB";

		} else if ((bytes >= megabyte) && (bytes < gigabyte)) {
			return (bytes / megabyte) + " MB";

		} else if ((bytes >= gigabyte) && (bytes < terabyte)) {
			return (bytes / gigabyte) + " GB";

		} else if (bytes >= terabyte) {
			return (bytes / terabyte) + " TB";

		} else {
			return bytes + " Bytes";
		}
	}

	public static long startMemory() {
		return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}

	public static String beautify(String json) throws IOException {
		return mapper.readTree(json).toPrettyString();
	}

	public static String toJsonString(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T toObject(String jsonString, Class<T> claz) {

		try {
			return mapper.readValue(jsonString, claz);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
