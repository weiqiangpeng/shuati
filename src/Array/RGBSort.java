package Array;

import java.util.ArrayList;

public class RGBSort {
	//荷兰国旗问题，给定一个数组，里面只有3种元素，分别是0,1,2，将其按照0,1,2,的顺序调整
	//方法1：计数排序
	public static void sortColors(int[] A) {
		int i = 0;
		int j = 0;
		int k = 0;
		for(int m=0;m<A.length;m++) {
			if(A[m] == 0)
				i++;
			else if(A[m] == 1)
				j++;
			else 
				k++;
		}
		
		for(int m=0;m<A.length;m++) {
			if(m < i)
				A[m] = 0;
			else if(m < i+j)
				A[m] = 1;
			else
				A[m] = 2;
		}
	}
	
	//方法2：只用扫描一遍
	public static void sortColorsII(int[] A) {
		int i=0;       //i为第一个1的位置，从0到i-1均是0，,i的左侧均为0
		int j = A.length-1;     //j是第一个不是2的位置，j的右侧都是2
		for(int k=0;k<=j;k++) {  //k是当前处理的位置，那么可以知道0到i-1的元素都是0，i到k-1之间都是1，k到j是未处理的部分，j之后的都是2
			if(A[k] == 0) {
				//如果当前位置是0，那么我们需要扩充0的那一段，首先将A[i]和A[k]交换，这样i的位置就为0了，k的位置就为1了，
				swap(A, i, k);
				//交换完之后，此时i需要加1，因为这个时候i上的元素就是0了，为0的那段元素要向右扩展一位了，k自然会在循环中加1，不用管
				i++;
			}
			//如果A[k] == 1 的话，刚好将为1的那一段向右扩展一位，k自然会在循环中加1，也不用管
			//如果A[k] == 2的话，说明需要将j的位置向左扩充一位，因为j的右侧全是2
			else if(A[k] == 2) {
				swap(A, j, k);
				j--;
				k--;    //k--是因为交换后k所在的位置不一定是2了，是之前j所在的位置的元素，还属于为探索区域，在这里减1，之后循环会加1，等于是再判断一遍
			}
		}
	}
	
	public static void swap(int[] A, int m, int n) {
		if(m != n) {
			int tmp = A[m];
			A[m] = A[n];
			A[n] = tmp;
		}
	}
	
	public static void main(String[] args) {
		int[] A = {1,2,0,2,1,2,1,0,0,0,0};
//		sortColorsII(A);
//		for(int a:A)
//			System.out.print(a + " ");
		ArrayList<Integer> res = new ArrayList<>();
	}
}
