/*
 FILE: MainClass.java
 NAME: Billy Carson
 PURP: Allow a user to search for as many part #'s and prices as desired
       by taking the part # and calling on the InventoryData class
       to conduct the search. Displays part # and price. Shows how many parts
       searched for, how many of those were valid and how many were invalid once user is finished searching.
       Writes valid part#'s, qty, and price to data file ordersProcessed.dat
 */

package stu.carson.finalprogram;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainClass 
{

	public static void main(String[] args) 
	{
		Scanner inputDevice = new Scanner (System.in);
		int partSearch = 1;
		int binResult = 0;
		int qty = 0;
		int valid = 0;
		int invalid = 0;
		int searched = 0;
		double price = 0.0;
		double total = 0.0;
		
		InventoryData inventory = new InventoryData();
		
		inventory.loadArrays();
		inventory.bubbleSort();
		
		System.out.println("Welcome to OOPing parts");
		System.out.println("Enter a part number to search for or 0 to exit");
		partSearch = inputDevice.nextInt();
		
		while (partSearch > 0)
		{
			binResult = inventory.binSearch(partSearch);
			
			try
			{
				PrintWriter output = new PrintWriter (new FileWriter("ordersProcessed.dat", true));
				OrdersProcessed process = new OrdersProcessed(output);
				
				System.out.printf("Your search found part number %d and it's price is $%.2f \n\n", partSearch, inventory.getPrice(binResult));
				
				price = inventory.getPrice(binResult);
				
				System.out.println("how many of this part would you like: ");
				qty = inputDevice.nextInt();
				total = inventory.getTotalPrice(qty, price);
				
				System.out.printf("%s %10s %10s %10s \n", "PART#", "PRICE", "QTY", "COST");
				System.out.printf("%d %10.2f %10d %10.2f\n\n", partSearch, price, qty, total);
				
				process.saveOneRecord(partSearch, qty, total);
				
				valid++;
				output.close();
			}
			catch(Exception ex)
			{
				System.out.printf("Your search did not find part numbe %d\n", partSearch);
				
				invalid++;
			}
			searched++;
			
			System.out.println("Enter a part number to search for or 0 to exit");
			partSearch = inputDevice.nextInt();
			
		}//END while
		
		inputDevice.close();
		
		System.out.printf("\nYou searched for a total of %d parts\n", searched);
		System.out.printf("%d parts were found, and %d were invalid parts\n\n", valid, invalid);
		System.out.println("Your parts search session has ended, thank you.");

	}//END main

}//END MainClass
