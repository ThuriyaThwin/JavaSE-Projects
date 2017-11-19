package com.jdc.sort;

public class BubbleSort {

	public static void main(String[] args) {
		
		int [] data = DataProvider.getData();
		int count = 0;
		System.out.println("Before Sorting");
		DataProvider.showData(data);
		
		for (int i = data.length -1; i > 0; i--) {
			
			for (int j = 0; j < i; j++) {
				
				if(data[j] > data[j+1]) {
					int min = data[j + 1];
					int max = data[j];
					
					data[j] = min;
					data[j + 1] = max;
				}
				
				count ++;
			}
		}
		
		System.out.printf("Count is : %d%n", count);
		System.out.println("After Sorting");
		DataProvider.showData(data);
	}
}

