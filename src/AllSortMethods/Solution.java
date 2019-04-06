package AllSortMethods;

import java.util.HashMap;
import java.util.Map;

//
//import java.util.ArrayList;
//import java.util.Collections;
//
//public class Solution {
//	public static void main(String[] args) {
//		int [] arr = {9,4,5,6,7,8,66};
//		ArrayList<Integer> res = GetLeastNumbers_Solution(arr, 4);
//		for (int i = 0; i < res.size(); i++) {
//			System.out.println(res.get(i));
//		}
//	}
//    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
//        ArrayList<Integer> res = new ArrayList<>();
//        if(input.length < k || k<=0)
//            return res;
//        //使用堆排序，构建小根堆，交换k次即可
//        int len = input.length;
//        //利用前k个元素创建大根堆
//        for(int i=k/2-1;i>=0;i--)
//            adjustHeap(input, i, k);
//        System.out.println("初始时候创建的大根堆为：");
//        for (int i = 0; i < k; i++) {
//			System.out.print(input[i]);
//		}
//        System.out.println();
//        for(int j=k;j<len;j++){
//            if(input[j] < input[0]){
//                int tmp = input[j];
//                input[j] = input[0];
//                input[0] = tmp;
//                adjustHeap(input, 0, k);
//            }
//        }
//        for(int i=0;i<k;i++){
//            res.add(input[i]);
//        }
//        Collections.sort(res);
//        return res;
//    }
//    /**
//    //表示从第i个节点开始调整使其成为一个堆
//    public static void adjustHeap(int [] arr, int i, int len){
//        if(i>=len/2)
//            return;
//        int min = i;
//        int left = 2*i+1;
//        int right = 2*i+2;
//        //找出左右孩子中比父节点更小的元素的索引min，并和父节点交换
//        if(left <= len && arr[left] < arr[min])
//            min = left;
//        if(right <= len && arr[right] < arr[min])
//            min = right;
//        if(i != min){
//            int tmp = arr[i];
//            arr[i] = arr[min];
//            arr[min] = tmp;
//            //交换完之后递归调整子树，使其接着满足小根堆
//            adjustHeap(arr, min, len);
//        }
//    }*/
//    public static void adjustHeap(int [] arr, int i, int len){
//        if(i>len/2-1)
//            return;
//        int max = i;
//        int left = 2*i+1;
//        int right = 2*i+2;
//        //找出左右孩子中比父节点更小的元素的索引min，并和父节点交换
//        if(left < len && arr[left] > arr[max])
//            max = left;
//        if(right < len && arr[right] > arr[max])
//            max = right;
//        if(i != max){
//            int tmp = arr[i];
//            arr[i] = arr[max];
//            arr[max] = tmp;
//            //交换完之后递归调整子树，使其接着满足大根堆
//            adjustHeap(arr, max, len);
//        }
//    }
//}


public class Solution {
	public static void main(String[] args) {
		int[] arr = {6,-3,-2,7,-15,1,2,2,100};
		int res = FindGreatestSumOfSubArray(arr);
		System.out.println(res);
		
		System.out.println(fractionToDecimal(2, 3));
		
	}
	
	public static String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0)
            return "0";
        if(denominator == 0)
            return "";
        long num = Math.abs((long) numerator),den = Math.abs((long)denominator);
        Map<Long,Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        if((numerator < 0) ^ (denominator < 0))
            sb.append('-');
        long rem = num % den,quo = num / den;
        sb.append(quo);
        if(rem != 0)
            sb.append('.');
        rem *= 10;
        while(rem != 0) {
            Integer idx = map.get(rem);
            if(idx != null) {
                sb.insert(idx,"(").append(')');
                break;
            }
            else {
                map.put(rem,sb.length());
                sb.append(rem / den);
                rem = (rem % den) * 10;
                
            }
        }
            
        return sb.toString();
    }

	
    public static int FindGreatestSumOfSubArray(int[] array) {
        //运用动态规划的思想 F(i) = max{F(i-1)+arr[i], arr[i]}
        int res = array[0];
        int tmp = array[0];
        for(int i=1;i<array.length;i++){
            tmp = Math.max(tmp+array[i], array[i]);
            res = Math.max(res, tmp);
        }
        return res;
    }
}