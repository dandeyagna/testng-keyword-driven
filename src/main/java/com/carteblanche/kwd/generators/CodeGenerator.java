package com.carteblanche.kwd.generators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CodeGenerator {
	
	private String packageStatement = "com.carteblanche.kwd.generators;";
	private String importStatements;
	private String classStatement;
	private String openingBrace = "{";
	private String closingBrace = "}";
	private String content;

	public void createClass(){
		content = content + packageStatement + "\n";
		content = content + importStatements + "\n";
		
		content = content + classStatement + "public class Hello" + openingBrace +"\n";
		
		
		content = content + closingBrace;
		writeToFile("Hello.java",content);
		
	}
	
	
	public static void writeToFile(String fileName,String content) {
		try {

			File file = new File("fileName");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
