package com.jdc.sort;

public class InsertionSort {

	public static void main(String[] args) {
		
		int [] array = DataProvider.getData();
		System.out.println("Before Sort");
		DataProvider.showData(array);
		
		int count = 0;
		
		for (int i = 0; i < array.length; i++) {
			
			for (int j = i+1; j < array.length && j > 0 && array[j] <= array[j-1]; j--) {
				int min = array[j];
				int max = array[j-1];
				
				array[j] = max;
				array[j-1] = min;
				
				count ++;
			}
			
		}
		
		
		System.out.printf("Count is : %d%n", count);
		
		System.out.println("After Sort");
		DataProvider.showData(array);
		
	}
}
