/*
 FILE: InventoryData.java
 NAME: Billy Carson
 PURP: To take input from MainClass and perform a binary search of 
 	   requested part # and return appropriate info.
       Uses a bubble sort method to sort loaded data in ascending order.
 */

package stu.carson.finalprogram;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class InventoryData 
{
	private int[] partNum = new int [600];
	private double[] partPrice = new double [600];
	private int partCt = 0;
	
	InventoryData(){};
	
	public void loadArrays()
	{
		partCt = 0;
		
		try
		{
			String filename = "masterInventory.dat";
			Scanner infile = new Scanner(new FileInputStream(filename));
			
			while (infile.hasNext())
			{
				partNum[partCt] = infile.nextInt();
				partPrice[partCt] = infile.nextDouble();
				++partCt;
			}
			infile.close();
		}//END try block
		catch(IOException ex)
		{
			partCt = -1;
			ex.printStackTrace();
		}
	}//END loadArrays method
	
	public double getPrice (int ind)
	{
		double price = 0;
		
		price = partPrice[ind];
		return price;
	}
	
	public double getTotalPrice (int qty, double price)
	{
		double total = 0;
		
		total = qty * price;
		return total;
	}
	
	public void bubbleSort()
	{
		int last = partCt - 1;
		
		for (; last > 0; last--)
		{
			int ind = 0;
			int swap = 0;
			
			for (; ind < last; ++ind)
			{
				if (partNum[ind] > partNum[ind+1])
				{
					int temp = 0;
					double tempPrice = 0;
					
					temp = partNum[ind];
					tempPrice = partPrice[ind];
					partNum[ind] = partNum[ind+1];
					partPrice[ind] = partPrice[ind+1];
					partNum[ind+1] = temp;
					partPrice[ind+1] = tempPrice;
					
					swap = 1;
				}//END if
			}//END inner loop
			if (swap == 0)
			{
				last = 0;
			}
		}//END out loop
	}//END bubbleSort method
	
	public int binSearch(int target)
	{
		int first = 0;
		int last = partCt -1;
		int found = 0;
		int mid = 0;
		
		while (first <= last && found == 0)
		{
			mid = (first + last) / 2;
			
			if (partNum[mid] == target)
			{
				found = 1;
			}
			if (partNum[mid] < target)
			{
				first = mid + 1;
			}
			else
			{
				last = mid - 1;
			}
		}//END while
		
		if (found == 0)
		{
			mid = -1;
		}
		
		return mid;
	}//END binSearch method

}//END InventoryData class
