package AllSortMethods;

import java.util.Scanner;

public class HeapSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String[] arr = s.split(" ");
		int[] input = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			input[i] = Integer.valueOf(arr[i]);
		}
		
		//堆排序
		HeapSort(input, input.length);
		System.out.println("-----------------------");
		for (int i = 0; i < input.length; i++) {
		System.out.println(input[i]);
		}
	}
	
	public static void HeapSort(int[] input, int len){
		BuildHeap(input, len);
		//i是无序区的最后一个元素，每次把第一个元素也就是最大的元素与i对应的元素交换
		for (int i = input.length - 1; i >= 0; i--) {
			int temp = input[i];
			input[i] = input[0];
			input[0] = temp;
			  
			AdjustHeap(input, 0, i);
		}
	}
	public static void BuildHeap(int[] input, int length){
		//从最后一个非叶子节点开始调整
		for (int i = length/2 - 1; i >=0; i--) { 
			AdjustHeap(input, i, length);
		}
	}
	//AdjustHeap是堆排序的调整堆函数，参数为：输入数组input，调整节点的位置i，要调整的局部长度length，所以要调整的范围为input的0-length-1
	public static void AdjustHeap(int[] input, int i, int length){
		if(i >= length/2){ //说明此时i是叶子节点，不需要调整，直接返回即可
			return;
		}
		int max = i;
		int leftchild = 2 * i + 1;
		int rightchild = 2 * i + 2;
		//leftchild和rightchild均不能越界
		if(leftchild <= length-1 && input[max] < input[leftchild]){
			max = leftchild;
		}
		if(rightchild <= length-1 && input[max] < input[rightchild]){
			max = rightchild;
		}
		//说明最大值不是i，而是它两个孩子节点之一
		if(i != max){
			int temp = input[i];
			input[i] = input[max];
			input[max] = temp;
			
			//交换了i和max对应的值之后，i对应的值下去了，max对应的值上去了，i会对下面的孩子节点产生影响，所以递归调用调整堆
			//这里只是交换了max和i对应的值，索引位置没有变
			AdjustHeap(input, max, length);
		}
	}

}
