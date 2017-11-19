package com.jdc.sort;

public class SelectionSort {

	public static void main(String[] args) {
		
		int [] array = DataProvider.getData();
		
		System.out.println("Before Sorting");
		DataProvider.showData(array);
		
		int count = 0;
		
		for (int i = 0; i < array.length; i++) {
			
			int minIndex = i;
			
			for (int j = i + 1; j < array.length; j++) {
				
				if(array[minIndex] > array[j]) {
					minIndex = j;
				}
				
				count ++;
			}
			
			int minValue = array[minIndex];
			int current = array[i];
			array[minIndex] = current;
			array[i] = minValue;
			
		}
		
		System.out.printf("Count is : %d%n", count);
		System.out.println("After Sorting");
		DataProvider.showData(array);
	}
}
