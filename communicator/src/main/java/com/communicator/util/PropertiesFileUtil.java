package com.communicator.util;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author shabasha
 *
 */
public class PropertiesFileUtil {

	
	public static List<String> readAllProperties(String fileName) {
		final URL systemResource = ClassLoader.getSystemResource(fileName);
		System.out.println("systemResource path: " + systemResource.getPath());
		List<String> values = new ArrayList<>();
		try (InputStream reader = ClassLoader.getSystemResourceAsStream(fileName)) {

			Properties p = new Properties();
			p.load(reader);
			Set<String> keys = p.stringPropertyNames();
			for (String key : keys) {
				values.add(p.getProperty(key));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return values;

	}
	
	public static List<String> readProperties(String fileName) {
	    List<String> values = new ArrayList<>();
	    try (InputStream reader = ClassLoader.getSystemResourceAsStream(fileName)) {
	        Properties p = new Properties();
	        p.load(reader);
	        values.addAll(p.values().stream().map(Object::toString).toList());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return values;
	}
	
	public static void writeToProperties(String fileName, String key, String value) {
		File file = new File(ClassLoader.getSystemResource(fileName).getPath());
		try (InputStream reader = ClassLoader.getSystemResourceAsStream(fileName);
				FileOutputStream writer = new FileOutputStream(file,true)) {
			Properties p = new Properties();
			p.load(reader);
			p.setProperty(key, value);
			p.store(writer, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteFromProperties(String fileName, String key) {
		File file = new File(ClassLoader.getSystemResource(fileName).getPath());
		try (InputStream reader = ClassLoader.getSystemResourceAsStream(fileName)) {
			Properties p = new Properties();
			p.load(reader);
			p.remove(key);
			FileOutputStream writer = new FileOutputStream(file);
			p.store(writer, null);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
