package dp;

public class MinSumInMatrix {
	//二维数组路径最小和
	//动态规划1
	public static int getMinSum(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] dp = new int[m][n];
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(i == 0) {
					if(j == 0) {
						dp[i][j] = matrix[0][0];
					}
					else {
						dp[i][j] = dp[i][j-1] + matrix[i][j];
					}
				}
				else {
					if(j == 0) {
						dp[i][j] = dp[i-1][j] + matrix[i][j];
					}
					else {
						dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + matrix[i][j];
					}
				}
			}
		}
		return dp[m-1][n-1];
	}
	
	//动态规划2,空间优化
	public static int getMinSumII(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		int[] dp = new int[n];
		for(int i=0;i<m;i++) {
			//因为i是正向循环的，所以只需要保存j对用的dp即可
			for(int j=0;j<n;j++) {
				if(i == 0) {
					if(j == 0) {
						dp[j] = matrix[i][j];
					}
					else {
						dp[j] = dp[j-1] + matrix[i][j];
					}
				}
				else if(j == 0)
					dp[j] = dp[j] + matrix[i][j];
				else
					dp[j] = Math.min(dp[j-1], dp[j]) + matrix[i][j];
			}
		}
		return dp[n-1];
	}
	
	
	
	public static void main(String[] args) {
		int[][] matrix = {{12,2,3}, {2,6,2}, {1,9,8}};
		System.out.println(getMinSum(matrix));
		System.out.println(getMinSumII(matrix));
	}
	
}
