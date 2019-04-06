package AllSortMethods;

public class InsertSort {
	public static void main(String[] args) {
		int [] a = {21,4,5,6,7,9,1,2,3};
		InsertSort(a);
	}
	public static void InsertSort(int[] A){
		//有序区与无序区
		for (int i = 1; i < A.length; i++) {
			int j = i-1;
			int val = A[i];
			while(j >=0 && A[j] > val){
				A[j+1] = A[j];
				j -- ;
			}
			//此时j的位置是A[i]从后往前找的第一个比其小的元素，将A[i]的元素插入到其右侧即可
			A[j+1] = val;
		}
		System.out.println("排序后的结果为：");
		for (int i = 0; i < A.length; i++) {
			System.out.println(A[i]);
		}
	}
}
