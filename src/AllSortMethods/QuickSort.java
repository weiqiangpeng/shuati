package AllSortMethods;

import java.util.Stack;

public class QuickSort {
	//快速排序递归和非递归版本
	public static void main(String[] args) {
		int [] a = {22,5,3,2,1,7,8,99,0,4};
		QuickSort(a, 0, a.length-1);
		System.out.println("递归排序后的结果为：");
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		
		QuickSortNoDiGui(a, 0, a.length-1);
		System.out.println("非递归排序后的结果为：");
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
	public static void QuickSort(int [] A, int left, int right){
		if(left >= right)
			return;
		int index = partition(A, left, right);
		QuickSort(A, left, index-1);
		QuickSort(A, index+1, right);	
	}
//	public static int partition(int[] A, int left, int right){
//		int val = A[left];
//		int p1 = left+1;
//		int p2 = right;
//		do{
//			while(A[p1] < val)
//				p1++;
//			while(A[p2] > val)
//				p2--;
//			swap(A, p1, p2);
//		}while(p1<p2);
//		swap(A, p1, p2);
//		swap(A, left, p2);
//		return p2;
//	}
	
	public static int partition(int[] A, int left, int right){
		int val = A[left];
		int p1 = left;
		int p2 = right;
		while(p1<p2){
			while(p1<p2 && A[p2] > val)
				p2--;
			if(p1<p2)
				A[p1] = A[p2];
			while(p1<p2 && A[p1] < val)
				p1++;
			if(p1<p2)
				A[p2] = A[p1]; 
		}
		A[p1] = val;
		return p1;
		
	}
	public static void swap(int[] A, int i, int j){
		if(i!=j){
			int tmp = A[i];
			A[i] = A[j];
			A[j] = tmp;
		}
	}
	
	public static void QuickSortNoDiGui(int[] A, int left, int right) {
		if(left>=right)
			return;
		Stack<Integer> st = new Stack<>();
		st.push(right);
		st.push(left);
		while(!st.isEmpty()) {
			int l = st.pop();
			int r = st.pop();
			if(l<r) {
				int k = partition(A, l, r);
				if(l<=k-1){
					st.push(k-1);
					st.push(l);
				}
				if(r>=k+1) {
					st.push(r);
					st.push(k+1);
				}
			}
			
		}
	}
	

	
}
