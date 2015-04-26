package openstack;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;


public class BatchProcess {
	
	DatabaseAccessOpenstack openstack = Controller.openstack;
	public String getAccountList(String s) throws Exception{
	
		String dirName = s;
		String accountList=null;
		CSVReader reader = new CSVReader(new FileReader(dirName+"person-list.csv"), ',', '\"', 1);
		String [] nextLine;
		
		while ((nextLine = reader.readNext()) != null) 
		{
	       
			if(nextLine[0].isEmpty()||nextLine[0].trim().isEmpty()||nextLine[0]==null||nextLine[0].trim().equals("none"))
			{
			
				return null;
			}
			else if(accountList==null)
			{
			
				accountList=nextLine[0].trim()+",";
				//System.out.println("accountList="+accountList);					
			}
			else 
			{
				
				accountList=accountList+nextLine[0].trim()+",";
				//System.out.println("accountList="+accountList);
			}
				
		}
	   //System.out.println(accountList);
		if(accountList!=null)	
			return accountList.substring(0, accountList.length()-1);
		else
			return accountList;
	}
	
	public String getReviewList(String s) throws Exception{
		
		String dirName = s;
		String reviewList=null;
		CSVReader reader = new CSVReader(new FileReader(dirName+"review-list.csv"), ',', '\"', 1);
		String [] nextLine;
		
		while ((nextLine = reader.readNext()) != null) 
		{
	       
			if(nextLine[0].isEmpty()||nextLine[0].trim().isEmpty()||nextLine[0]==null||nextLine[0].trim().equals("none"))
			{
			
				return null;
			}
			else if(reviewList==null)
			{
			
				reviewList="'"+nextLine[0].trim()+"'"+",";
				//System.out.println("reviewList="+reviewList);					
			}
			else 
			{
				
				reviewList=reviewList+"'"+nextLine[0].trim()+"'"+",";
				//System.out.println("reviewList="+reviewList);
			}
				
 		}
    	//System.out.println(reviewList);
		if(reviewList!=null)	
			return reviewList.substring(0, reviewList.length()-1);
		else
			return reviewList;
	}
}