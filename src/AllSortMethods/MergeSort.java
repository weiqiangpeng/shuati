package AllSortMethods;

public class MergeSort {
	public static void main(String[] args) {
		int [] a = {2,4,8,6,5,90,34,21};
		MergeSort(a, 0, a.length-1);
		System.out.println("结果为：");
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
	
	//递归版本的归并排序
	public static void MergeSort(int [] A, int i, int j){
		if(i == j)
			return;
		int mid = (i+j)/2;
		MergeSort(A, i, mid);
		MergeSort(A, mid+1, j);
		Merge(A, i, mid, j);
	}
	
	public static void Merge(int[] A, int i, int mid, int j){
		int p1 = i;
		int p2 = mid+1;
		int p3 = 0;
		int [] C = new int[j-i+1]; //保存临时结果的数组
		while(p1<=mid && p2<=j){
			if(A[p1] <= A[p2]){
				C[p3++] = A[p1++];
			}else{
				C[p3++] = A[p2++];
			}
		}
		//把剩下的加到临时数组的末尾
		while(p1<=mid)
			C[p3++] = A[p1++];
		while(p2<=j)
			C[p3++] = A[p2++];
		//最后再把结果覆盖回原数组，起始是i
		for (int k = 0; k < C.length; k++) {
			A[i++] = C[k];      //归并合并的时候起始是i，结束是j
		}
	}
}
