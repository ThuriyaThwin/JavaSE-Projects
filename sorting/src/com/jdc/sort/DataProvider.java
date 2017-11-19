package com.jdc.sort;

public class DataProvider {

	public static int [] getData() {
		return new int[] {6,5,3,1,8,7,2,4};
	}
	
	public static void showData(int [] array) {
		
		System.out.print("[");
		
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			
			if(i < array.length - 1)
				System.out.print(", ");
		}
		
		System.out.println("]");
	}
}
