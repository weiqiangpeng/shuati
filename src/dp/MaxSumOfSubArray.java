package dp;

public class MaxSumOfSubArray {
	//方法一：暴力求解法,时间复杂的为O(n^3)
	public static int maxSubArray(int[] A, int n) {
		//给定一个数组A,要求其在left~right范围内的和最大的子数组，返回最大的和，并返回字数组的起始位置i， j
		int maxSum = 0;
		for(int i=0;i<n;i++) {
			for(int j=i;j<n;j++) {
				int sum = 0;
				for(int k=i;k<=j;k++) {
					sum += A[k];
				}
				if(sum > maxSum) {
					maxSum = sum;
				}
			}
		}
		return maxSum;
	}
	
	//方法二：稍微在方法一的基础上优化一下,不用另外一个变量k来指示了，时间复杂度O(n^2)
	public static int maxSubArrayII(int[] A, int n) {
		int maxSum = 0;
		for(int i=0;i<n;i++) {
			int sum = 0;
			for(int j=i;j<n;j++) {
				sum += A[j];
				if(sum > maxSum) {
					maxSum = sum;
				}
			}
		}
		return maxSum;
	}
	
	//方法3：分治法，时间复杂度O(nlogn)
	//数组A[0,1....n-1],将其分为2部分A[0,1...n/2-1]和A[n/2,....n-1],分别求出这两段子数组各自的最大子段和，
	//则原数组A[0,1...n-1]的最大子段和为下列三种情况的最大值
	//1、原数组A[0,1...n-1]的最大子段和，与前半部分A[0,1...n/2-1]的最大子段和相等
	//2、原数组A[0,1...n-1]的最大子段和，与后半部分A[n/2,....n-1]的最大子段和相等
	//3、原数组A[0,1...n-1]的最大子段和既不是前面半部分最大子段和，也不是后半部分最大子段和，而是跨过了中间两个元素A[n/2-1]和A[n/2]。
	//其中1和2是规模减半的子问题，可以通过递归求得，
	//至于第三种情况，只需要找到以A[n/2-1]结尾的和最大的数组S1,A[i,...n/2-1]，以及以A[n/2]开头的和最大的子数组S2:A[n/2,.4...j],返回S1+S2即可
	public static int maxSubArrayIII(int[] A, int s, int n) {
		if(n == s)
			return A[s];
		int mid = n + (n - s) >> 1;
		int answer = Math.max(maxSubArrayIII(A, s, mid-1), maxSubArrayIII(A, mid, n));
		//下面求第三种情况,may是第三种情况对应的最大值，因为其不确定是否是最终的最大值，所以起名叫做May
		int now = A[mid-1];
		int may = now;
		for(int i=mid-2;i>=s;i--) {
			now += A[i];
			may = Math.max(now, may);
		}
		now = may;
		for(int i=mid;i<n;i++) {
			now += A[i];
			may = Math.max(now, may);
		}
		//最终返回三者的最大值
		return Math.max(may, answer);
	}
	
	//方法4：动态规划,时间复杂度O(n),空间复杂度O(n)
	public static int maxSubArrayIV(int[] A, int n) {
		//设dp[i]是以A[i]结尾的最大子段的和，那么dp[0] = A[0],dp[i] = max(dp[i-1]+a[i], a[i])
		if(n == 1)
			return A[n];
		int[] dp = new int[n];
		dp[0] = A[0];
		for(int i=1;i<n;i++) {
			dp[i] = Math.max(dp[i-1]+A[i], A[i]);
		}
		
		int max = Integer.MIN_VALUE;
		for(int val:dp) {
			if(val > max)
				max = val;
		}
		return max;
	}
	
	//方法5：线性枚举, A[i]+A[i+1]+...+A[j] = sum[j] -sum[i-1]
	public static int maxSubArrayV(int[] A, int n) {
		int sum = A[0];
		int minsum = Math.min(sum, 0);
		int answer = A[0];
		for(int i=1;i<n;i++) {
			sum += A[i];   //sum求的是截止到i下标的数组和
			answer = Math.max(answer, sum-minsum);    //更新answer，用到i为止的和减去到i为止的最小和
			minsum = Math.min(minsum, sum);
		}
		return answer;
	}
	
	public static void main(String[] args) {
		int[] a = {-1,2,-3,4,-3};
		System.out.println(maxSubArray(a, 5));
		System.out.println(maxSubArrayII(a, 5));
		System.out.println(maxSubArrayIII(a,0,4));
		System.out.println(maxSubArrayIV(a,5));
		System.out.println(maxSubArrayV(a,5));
	}
}
