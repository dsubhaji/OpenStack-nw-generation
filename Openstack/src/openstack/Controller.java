package openstack;

import openstack.*;
import java.util.Scanner;

public class Controller {

	/**
	 * @param args
	 */
	static DatabaseAccessOpenstack openstack = new DatabaseAccessOpenstack();
	static IOFormatter io=new IOFormatter();
	static BatchProcess bp= new BatchProcess();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		float startTime = 0;
		float endTime = 0;
		io.inputConString();
		System.out.println("");
		startTime = System.nanoTime();
		
		System.out.println("Connecting to Database...");
		
		boolean isOpenstack= openstack.openConnection(io.getDBN(), io.getMysqlUserName(), io.getMysqlPass());
		if(isOpenstack)
		{
			System.out.println("Connected..");
			System.out.println("");
			
			if(io.getDBN().equalsIgnoreCase("openstack"))
			{
				io.batchInput();
				io.inputData();
			}	
			bp.getAccountList(io.getDirectoryPath());
			openstack.generateDRMN(bp.getAccountList(io.getDirectoryPath()), bp.getReviewList(io.getDirectoryPath()),io.getStartDate(), io.getEndDate());
			io.writeFile(openstack.getFileContent(), io.getDirectoryPath()+"openstack-network.net");
			endTime = System.nanoTime();
			System.out.println("Complete Execution");
			System.out.println("Total Time Elapsed: " + (((endTime - startTime)/1000000000)/60) + " minutes");
		}
		else
		{
			System.out.println("Wrong Connection String/Username/Password!");
					
		}
	}

}


