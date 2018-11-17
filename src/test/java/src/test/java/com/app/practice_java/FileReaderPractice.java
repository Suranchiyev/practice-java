package src.test.java.com.app.practice_java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReaderPractice {
	/*
	 * 1. Put data from the file List<String[]> format. Write method that will load
	 * data. Method accepts one argument as String as path to the file.
	 */

	public static void main(String[] args) {
			String filePath = "C://Users//Tamara//workspace//practice-java//TestData//PRICING_CASE_PricingResults.csv";
			String loanId = "4008524447";
			getDataByID(filePath,loanId);

	}

	public static void getDataByID (String path, String loanID) {
		
		try {
			List <String[]> pricingResults=new ArrayList<>();
			BufferedReader reader = new BufferedReader(new FileReader(path));

			String line;
			while((line = reader.readLine()) != null) {
		     	String [] ar= line.split("\\|");
		     	pricingResults.add(ar);
     		}
			
			
			for (String[] row : pricingResults) {
				
			//System.out.println(row[0]);
			
			if (row[0].equals(loanID)) {
				
				System.out.println(Arrays.toString(row));
				
				
				
			}
			}

		} catch (Exception e) {
			
		}
		
		
		//save file with new name in new location

	}
	
	
		//print row with id

		
		//pricingResults
	}
	
