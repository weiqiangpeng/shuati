package AllSortMethods;

import java.util.ArrayList;

public class HeapTest {
	//输出一个数组中最小的k个数字。
	public static void main(String[] args) {
		int [] arr = {1,9,8,7,54,32,23};
		ArrayList<Integer> res = GetLeastNumbers_Solution(arr, 5);
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}
	}
	
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if(input.length < k || k<=0)
            return res;
        //使用堆排序，构建小根堆，交换k次即可
        int len = input.length;
        for(int i=len/2 -1;i>=0;i--)
            adjustHeap(input, i, len);
        for(int j=len-1;j>=len-k;j--){
            res.add(input[0]);
            input[0] = input[j];
            adjustHeap(input, 0, j-1);
        }
        return res;
    }
    //表示从第i个节点开始调整使其成为一个堆
    public static void adjustHeap(int [] arr, int i, int len){
        if(i>=len/2)
            return;
        int min = i;
        int left = 2*i+1;
        int right = 2*i+2;
        //找出左右孩子中比父节点更小的元素的索引min，并和父节点交换
        if(left <= len && arr[left] < arr[min])
            min = left;
        if(right <= len && arr[right] < arr[min])
            min = right;
        if(i != min){
            int tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
            //交换完之后递归调整子树，使其接着满足小根堆
            adjustHeap(arr, min, len);
        }
    }
}