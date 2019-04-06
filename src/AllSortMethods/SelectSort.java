package AllSortMethods;

public class SelectSort {
	public static void main(String[] args) {
		int[] a = {2,3,4,5,6,7,54,9,10};
		SelectSort(a);
	}
	public static void SelectSort(int [] array){
		for (int i = 0; i < array.length-1; i++) {
			int min = i;
			for (int j = i+1; j < array.length; j++) {
				if(array[min] > array[j])
					min = j;
			}
			if(i != min){
				int tmp = array[i];
				array[i] = array[min];
				array[min] = tmp;
			}
		}
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
}
