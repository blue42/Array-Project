/*
 FILE: OrdersProcessed.java
 NAME: Billy Carson
 PURP: To take input from MainClass and write data to a new
       .dat file named ordersProcessed.dat.
 */

package stu.carson.finalprogram;

import java.io.PrintWriter;

public class OrdersProcessed 
{
	private PrintWriter dataFile;
	
	public OrdersProcessed(PrintWriter output)
	{
		dataFile = output;
	}
	
	public void saveOneRecord(int part, int qty, double price)
	{
		dataFile.printf("%d %d %.2f%n", part, qty, price);
	}
}//END OrdersProcessed class