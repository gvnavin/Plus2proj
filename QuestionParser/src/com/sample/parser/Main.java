package com.sample.parser;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;


public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String everything;
		  FileInputStream inputStream = new FileInputStream("C:/Users/gnavin/NavinPersonalDocuments/plus_two/json_10.txt");
		    try {
		         everything = IOUtils.toString(inputStream);
		    } finally {
		        inputStream.close();
		    }
		    System.out.println(everything);
		    Gson gson = new Gson();
		    Question[] questions = gson.fromJson(everything, Question[].class);
		    System.out.println(questions[0].getmQuestion());

	}

}
