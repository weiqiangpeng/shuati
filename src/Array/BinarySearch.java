package Array;

public class BinarySearch {
	public static int binarySearch(int[] A, int target){
		if(A.length == 0)
			return -1;
		int start = 0;
		int end = A.length -1 ;
		while(start < end){
			int mid = (end-start)/2+start;
			if(A[mid] > target)
				end = mid-1;
			else if(A[mid] < target)
				start = mid+1;
			else
				return mid;
		}
		return -1;
	}
	
	public static void main(String[] args) {
//		int[] a ={1,2,3,4,5,7,8,9};
//		int res = binarySearch(a, 6);
//		System.out.println(res);
		System.out.println(1&0);
	}
}
