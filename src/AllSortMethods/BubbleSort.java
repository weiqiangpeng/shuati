package AllSortMethods;

public class BubbleSort {
	public static void main(String[] args) {
		int [] a = {9, 11,23,1,3,4,6,90};
		BubbleSort(a);
	}
	public static void BubbleSort(int [] array){
		//冒泡排序
		for (int i = 0; i < array.length-1; i++) {
			for (int j = 0; j < array.length-1-i; j++) {
				if(array[j] > array[j+1]){
					int tmp = array[j];
					array[j] = array[j+1];
					array[j+1] = tmp;
				}
				
			}
		}
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
}
