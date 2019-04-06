package AllSortMethods;

public class ShellSort {
	public static void main(String[] args) {
		int [] a = {6,7,8,2,3,4,45,21};
		ShellSort(a);
	}
	//希尔排序是插入排序的一种改进版本，是一种递减增量排序
	public static void ShellSort(int [] A){
		int h = 0;
		//先生成初始增量h
		while(h<A.length)
			h = 3*h + 1;
		while(h>=1){ //增量h会一直减小到1的,在此操作和普通的插入排序是一致的
			for (int i = h; i < A.length; i++) {
				int j = i-h;
				int val = A[i];
				while(j>=0 && A[j] > val){
					A[j+h] = A[j];
					j -= h;
				}
				A[j+h] = val;
			}
			h = (h-1) / 3;
		}
		System.out.println("排序后的结果为：");
		for (int i = 0; i < A.length; i++) {
			System.out.println(A[i]);
		}
	}
}
