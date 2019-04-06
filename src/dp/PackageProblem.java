package dp;

public class PackageProblem {
	//0-1背包问题，类似挖矿问题。  
	//有一堆物品，分别有他们的价值数组和体积数组，还有一个背包，体积有限，如何装价值最多的物品
	public static int packageProblemI(int[] value, int[] volume, int totalVolume) {
		//value:价值数组，volume:体积数组， totalVolume:背包总体积
		int number = value.length;
		//dp[i][j]表示前i个物品，在体积j下的最大价值
		int[][] dp = new int[number+1][totalVolume+1];
		for(int i=0;i<number;i++) {
			dp[i][0] = 0;            //很显然的，体积为0的时候，最大价值肯定也为0
		}
		for(int j=0;j<totalVolume;j++) {
			dp[0][j] = 0;                  //很显然，第0件物品，给再大体积也不行，
		}
		for(int i=1;i<number+1;i++) {
			for(int j=1;j<totalVolume+1;j++) {
				if(j<volume[i-1])          //此时的体积放不下，也就是j不够，那么最大价值肯定是在dp[i-1][j]里出现
					dp[i][j] = dp[i-1][j];
				else {
					//否则我们有2种选择，1：选择第i-1件物品放进去、2：不选择第i-1件物品
					dp[i][j] = Math.max(dp[i-1][j-volume[i-1]]+value[i-1], dp[i-1][j]);
				}
			}
		}
		
		//输出dp看下
		for(int[] A:dp) {
			for(int a:A)
				System.out.print(a+" ");
			System.out.println();
		}
		return dp[number][totalVolume];
	}
	
	public static void main(String[] args) {
		int[] value = {12, 10, 20, 15};
		int[] volume = {2, 1, 3, 2};
		int totalVolume = 5;
		System.out.println(packageProblemI(value, volume, totalVolume));
	}
}
