package interviewcake;

public class BinarySearchPractice {
	
	public static void main(String [] args) {
		int [] arr = {4,7,10,13,14,18,20};
		int foundIdx = -1;
		
		int target =  4;
		//find 13, 7, 20
		
		foundIdx = BinarySearch(arr, target);
		System.out.println(String.format("Element %s found at index %s", target , foundIdx));
	} 
	
	public static int BinarySearch(int [] arr, int target) {
		/*
		 * 1. Make sure array is sorted. 
		 * 2. Well what IS binary search? 
		 * 3. Calc Mid, Verify Mid, Set Range. CVS
		 * */
		
		int start = 0;
		int end =arr.length-1;
		int mid = 0;
		
		while(start<end) {
			mid = start + ((end-start)/2);
			
			if(arr[mid] == target) {
				return mid; //do we break or return mid here?
			}
			
			if(arr[mid] > target) {
				end = mid-1;
			}else if(arr[mid] < target) {
				start = mid +1;
			}
			
		}
		
		return start;
	}
	
	
	//test cases
}
