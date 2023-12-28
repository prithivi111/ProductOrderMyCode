package com.suraj.service;

import java.sql.SQLException;
import java.util.Scanner;

import com.suraj.operations.DAO;

public class OnlineStore {

	public static void main(String[] args) {
		try {
			System.out.println("Please enter your choice:\n1. Register Product\n2. Search Product By Name\n3. Place Order\n");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1: 
				DAO.registerProduct();
				break;
			
			case 2: 
				DAO.searchProductByName();
				break;
		
			case 3:
				DAO.placeOrder();
				break;
			
			default:
				System.out.println("Invalid Choice");
				break;
			
			}
	
			
			
		}catch(Exception e) {
			System.out.println(e);
		}				
	}	
}
