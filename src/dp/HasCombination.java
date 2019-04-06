package dp;

public class HasCombination {
	public static boolean hasCombinationI(int[] arr, int len, int s) {
		if(s == 0)    //参数len是下标
			return true;
		if(len == 0)
			return arr[0] == s;
		if(arr[len] > s)
			return hasCombinationI(arr, len-1, s);
		return hasCombinationI(arr, len-1, s) || hasCombinationI(arr, len-1, s-arr[len]);
	}
	
	public static boolean hasCombinationII(int[] arr, int s) {
		//boolean[i][j]表示长度为i的数组，是否存在和为j的组合
		int len = arr.length;
		boolean[][] flag = new boolean[len][s+1];
		//当s=0的时候，很明显都是true
		for (int i = 0; i < flag.length; i++) {
			flag[i][0] = true;
		}
		//当len=1的时候，说明arr只有一个数字，那么返回值就是arr[0] == i(i是1~s内的变化范围)
		for (int i = 1; i < s+1; i++) {
			flag[0][i] = (arr[0] == i);
		}
		
//		for(boolean[] A:flag) {
//			for(boolean f:A) {
//				System.out.print(f + " ");
//			}
//			System.out.println();
//		}
		//边界条件写完，开始迭代求其他的情况
		for (int i = 1; i < len; i++) {
			for (int j = 1; j < s+1; j++) {
				if(arr[i] > j)
					flag[i][j] = flag[i-1][j];
				else
					flag[i][j] = flag[i-1][j] || flag[i-1][j-arr[i]];
			}
		}
		return flag[len-1][s];
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4};
		System.out.println(hasCombinationI(arr, arr.length-1, 9));
		System.out.println(hasCombinationI(arr, arr.length-1, 5));
		System.out.println(hasCombinationI(arr, arr.length-1, 11));
		System.out.println("-----------------------------");
		System.out.println(hasCombinationII(arr, 9));
		System.out.println(hasCombinationII(arr, 5));
		System.out.println(hasCombinationII(arr, 11));
	}
}
