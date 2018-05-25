package com.msl.data.arangodb.promo.util;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdFileGenerator {

	private static final Logger logger = LoggerFactory.getLogger(IdFileGenerator.class.getName());
	public static final String PATH = "c:/temp/ids.txt";

	public static void main(String[] args) {
		int numIDs = new Integer(args[0]);
		generate(numIDs);
	}
	
	public static void generate(int numIDs) {
		// Get the file reference
		Path path = Paths.get(PATH);
		log("Generando fichero de " + numIDs + " IDs en la ruta:" + path.toAbsolutePath());
		// Use try-with-resource to get auto-closeable writer instance
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			for (int i = 0; i < numIDs; i++) {
				writer.write(UUID.randomUUID().toString() + "\r\n");
			}
		} catch (Exception e) {
			logError("Error generando fichero de IDs", e);
		}
		log("Fichero generado correctamente.");
	}
	
	private static void log(String msg) {
		logger.info(msg);
		System.out.println(msg);
	}
	
	private static void logError(String msg, Exception e) {
		logger.error(msg, e);
		System.out.println(msg);
		e.printStackTrace();
	}
}
