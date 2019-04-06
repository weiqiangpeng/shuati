package ShuaTi;

import java.util.ArrayList;

public class InversePairs {
	static int count = 0;
	static ArrayList<int[]> res = new ArrayList<>();
	public static int InversePairs(int[] array){
		if (array.length == 0)
			return 0;
//		int count = 0;
		mergesort(array, 0, array.length-1);
		return count;
	}
	
	public static void mergesort(int[] A, int left, int right){
		if(left >= right)
			return;
		int mid = (right - left) /2 + left; 
		mergesort(A, left, mid);
		mergesort(A, mid+1, right);
		merge(A,left, mid, right);
	}
	
	public static void merge(int[] A, int left, int mid, int right){
		int p0 = left;
		int p1 = mid+1;
		int p3 = 0;
		int [] C = new int[right-left+1];
		while(p0<=mid && p1<=right){
			if(A[p0] <= A[p1]){
				C[p3++] = A[p0++];
			}else{
				//如果在合并的过程中发现A[p0]>A[p1]，此时就构成了逆序对，且第一个子数组是升序，所以从p0之后的所有数字都大于A[p1]
				//在此计算逆序对的个数和逆序对的数字
				count += mid-p0+1;
				for (int i = p0; i <= mid; i++) {
					res.add(new int[] {A[i], A[p1]});
				}
				C[p3++] = A[p1++];
			}
		}
		while(p0<=mid)
			C[p3++] = A[p0++];
		while(p1<=right)
			C[p3++] = A[p1++];
		
		for (int i = 0; i < C.length; i++) {
			A[left++] = C[i];
			
		}
	}
	
	public static void main(String[] args) {
		int[] A = {1,2,3,4,5,6,7,0};
		int count = InversePairs(A);
		for (int i = 0; i < A.length; i++) {
			System.out.println(A[i]);
		}
		System.out.println("逆序对的个数为：" + count);
		System.out.println("逆序对结果为：");
		for (int i = 0; i < res.size(); i++) {
			for (int j = 0; j < res.get(i).length; j++) {
				System.out.print(res.get(i)[j]);
				System.out.print("  ");
			}
			System.out.println();
		}
	}
}
