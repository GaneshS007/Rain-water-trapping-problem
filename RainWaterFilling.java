//package com.externship.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RainWaterFilling
{
	
	public static int[] highestAndSecondHighesht(List<Integer> list)
	{
		int[] arr = new int[3];
		int HighestPosition, LeftSecondHighestPosition=-1, RightSecondHighestPosition=-1;
		if (list == null || list.size() == 0)
			return null;
		else
		{
				HighestPosition = list.indexOf(Collections.max(list));
				if (HighestPosition == 0)
				{
					List<Integer> cloneSubList = new ArrayList<>(list.subList(HighestPosition+1, list.size()));
					Collections.reverse(cloneSubList);
					RightSecondHighestPosition = list.size()-1-cloneSubList.indexOf(Collections.max(cloneSubList));
				}
				
				else if (HighestPosition == list.size()-1)
					LeftSecondHighestPosition = list.indexOf(Collections.max(list.subList(0, HighestPosition)));
				
				else
				{
					LeftSecondHighestPosition = list.indexOf(Collections.max(list.subList(0, HighestPosition)));
					List<Integer> cloneSubList = new ArrayList<>(list.subList(HighestPosition+1, list.size()));
					Collections.reverse(cloneSubList);
					RightSecondHighestPosition = list.size()-1-cloneSubList.indexOf(Collections.max(cloneSubList));
				}
			}
		arr[0] = HighestPosition;
		arr[1] = LeftSecondHighestPosition;
		arr[2] = RightSecondHighestPosition;
		
		return arr;
	}
	
	public static int leftSecondHighest(List<Integer> list, int hposition)
	{
		if (hposition == 0)
			return -1;
		else
		{
			int SecondHighestPosition = list.indexOf(Collections.max(list.subList(0, hposition)));
			return SecondHighestPosition;
		}
	}
	
	public static int rightSecondHighest(List<Integer> list, int hposition)
	{
		if(hposition == list.size()-1)
			return -1;
		else
		{
			List<Integer> list1 = list.subList(hposition+1, list.size());
			int SecondHighestPosition = list1.indexOf(Collections.max(list1))+hposition+1;
			return SecondHighestPosition;
		}
	}
	
	public static int unitOfWater(int left, int right, List<Integer> list)
	{
		int lvalue = list.get(left);
		int rvalue = list.get(right);
		
		int width = Math.abs(Math.abs(right - left) - 1);
		int highestHeight, lowestHeight;
		if (rvalue > lvalue)
		{
			highestHeight = rvalue;
			lowestHeight = lvalue;
		}
		else
		{
			highestHeight = lvalue;
			lowestHeight = rvalue;
		}
		
		int bigArea = highestHeight*width;
		int smallArea = lowestHeight*width;
		
		int airArea = bigArea - smallArea;
		
		int intermidateArea = bigArea - airArea;
		
		for(int i=left+1; i<right; i++)
			intermidateArea -= list.get(i);
		
		return intermidateArea;
	}
	
	public static int UnitOfWaterUpdaterLeft(int LeftSecondHighestPosition, List<Integer> list)
	{
		int i = LeftSecondHighestPosition;
		int j = leftSecondHighest(list, i);
		int unitOfWater = 0;
		while(j != -1)
		{
			unitOfWater += unitOfWater(i, j, list);
			i = j;
			j = leftSecondHighest(list, i);
		}
		return unitOfWater;
	}
	
	public static int UnitOfWaterUpdaterRight(int RightSecondHighestPosition, List<Integer> list)
	{
		int p = RightSecondHighestPosition;
		int q = rightSecondHighest(list, p);
		int unitOfWater = 0;
		while(q != -1)
		{
			unitOfWater += unitOfWater(p, q, list);
			p = q;
			q = rightSecondHighest(list, p);
		}
		return unitOfWater;
	}
	
	public static void main(String[] args) 
	{
		int unitOfWater = 0;
		System.out.print("Enter the number of blocks: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		List<Integer> list = new ArrayList<Integer>();
		System.out.println("Enter the heights of the block: ");
		for(int i=0; i<n; i++)
			list.add(sc.nextInt());
		int arr[] = highestAndSecondHighesht(list);
		
		if (arr[1] == -1)
		{
			unitOfWater += unitOfWater(arr[0], arr[2], list);
			unitOfWater += UnitOfWaterUpdaterRight(arr[2], list);
		}
		
		else if (arr[2] == -1)
		{
			unitOfWater += unitOfWater(arr[1], arr[0], list);
			unitOfWater += UnitOfWaterUpdaterLeft(arr[1], list);
		}
		
		else
		{
			unitOfWater += unitOfWater(arr[1], arr[0], list);
			unitOfWater += unitOfWater(arr[0], arr[2], list);
			
			unitOfWater += UnitOfWaterUpdaterLeft(arr[1], list);
			
			unitOfWater += UnitOfWaterUpdaterRight(arr[2], list);
		}
	
		System.out.println("Number of units of water trapped between the blocks: "+unitOfWater);
		sc.close();
	}

}
