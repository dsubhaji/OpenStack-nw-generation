package openstack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class IOFormatter {

	private String dbN, mysqlUserName, mysqlPass;
	private String product, startDate, endDate, directoryPath,reviewDirectoryPath;
	private Scanner user_input = new Scanner( System.in );
	
	
	public IOFormatter()
	{
		dbN = "";
		mysqlUserName = "";
		mysqlPass = "";
		product = "";
		startDate = "";
		endDate = "";
		directoryPath = "";
		reviewDirectoryPath="";
	}
	
	public String getDBN()
	{
		return dbN;
	}
	
	public String getMysqlUserName()
	{
		return mysqlUserName;
	}
	
	public String getMysqlPass()
	{
		return mysqlPass;
	}
	
	public String getProduct()
	{
		return product;
	}
	
	public String getStartDate()
	{
		return startDate;
	}
	
	public String getEndDate()
	{
		return endDate;
	}
	
	public String getDirectoryPath()
	{
		return directoryPath;
	}
	
	
	public String getReviewDirectoryPath() {
		return reviewDirectoryPath;
	}

	
	/*
	 * Method Name: inputConString
	 * Input: void
	 * Output: void
	 * Function: 
	 * Takes user input for database name, user name and password and store it to the respective variable.
	 */
	public void inputConString()
	{
		System.out.print("Please Enter Database Name(example- qt_new): ");
		
		dbN = user_input.nextLine();
		
		System.out.print("Please Enter User Name: ");
		mysqlUserName = user_input.nextLine();
		
		System.out.print("Please Enter User Password: ");
		mysqlPass = user_input.nextLine();
	}
	
	
	public void inputData()
	{
		
		DatabaseAccessOpenstack openstack = Controller.openstack;
		System.out.print("Enter Start Date(e.g 2008-04-27):");
		startDate = user_input.next();
		
		System.out.print("Enter End Date(e.g 2009-10-10):");
		endDate = user_input.next();
		
			
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		if(!startDate.trim().equalsIgnoreCase("none"))
		{
			try 
			{
				Date date=sdf.parse(startDate.trim());
				System.out.println(date);
			} 
			catch (ParseException e1) 
			{
				System.out.println("Invalid StartDate ");
				
			}
		}
		if(!endDate.trim().equalsIgnoreCase("none"))
		{
			try 
			{
				Date date1=sdf.parse(endDate.trim());
			}
			catch (ParseException e1) 
			{
				System.out.println("Invalid EndDate ");
			}
		}
		int startdateInvalid=0;
		int enddateInvalid=0;
		
		if(startDate.trim().isEmpty()||startDate.trim().equals("none"))
		{
			startdateInvalid=1;
		}
		if(endDate.trim().isEmpty()||endDate.trim().equals("none"))
		{
			enddateInvalid=1;
		}
		
		if(startdateInvalid==1 ||enddateInvalid==1)
		{
			String[] d=null;
			try
			{
				d = openstack.getMinMax();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			if(startdateInvalid==1 && d!=null)
				startDate=d[0];
			if(enddateInvalid==1 && d!=null)
				endDate=d[1];
		}
	}
	public void  batchInput()
	{
		System.out.print("Enter Directory of person-list.csv");
	    directoryPath = user_input.nextLine();
	    //System.out.println(directoryPath);
	    System.out.println("Enter The Directory of review-list.csv");
	    reviewDirectoryPath=user_input.nextLine();
	    //System.out.println(reviewDirectoryPath);
	   
	}
	/*
	 * Method Name: writeFile
	 * Input: File Name and It's Content
	 * Output: If it succeeds in writing a file, return true, else it will return false
	 * Function: Creates a file.
	 */
	public boolean writeFile(String text, String fileName)
	{
		File file = new File(fileName);
		
		try{
		    FileWriter fileWriter = new FileWriter(file);

		    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		    bufferedWriter.write(text);
		    bufferedWriter.close();
		    
		    return true;
		} catch(IOException e) {
		    return false;
		}
	}
}
		
	