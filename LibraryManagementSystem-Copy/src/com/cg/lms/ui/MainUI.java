package com.cg.lms.ui;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.cg.lms.exception.LMSException;
import com.cg.lms.model.User;
import com.cg.lms.service.IUserService;
import com.cg.lms.service.UserServiceImpl;

public class MainUI {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PropertyConfigurator.configure("res/log4j.properties");
		
		int choice = -1;
	
		int loginAttempts=0;
		IUserService userService=new UserServiceImpl();
		
		while(choice!=2 && loginAttempts<=3){
			System.out.print("[1]Login [2]Quit >");
			choice = scan.nextInt();
			
			if(choice==1){
				System.out.print("UserName? ");
				String userName = scan.next();
				System.out.print("Password? ");
				String password = scan.next();
				loginAttempts++;
				try {
					String role = userService.getRole(userName, password) ;
					System.out.println(role);
					if("admin".equals(role)){
						AdminConsole ac = new AdminConsole(userName);
						ac.start();
					}else{
						StudentConsole sc = new StudentConsole(userName);
						sc.start();
					}					
				} catch (LMSException e) {
					System.err.println(e.getMessage());
				}				
			}
		}
		scan.close();
		System.out.println("Program Terminated");
	}
}
