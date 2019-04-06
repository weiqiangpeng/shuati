package dp;

import java.util.Arrays;

//   !!!!!!!!!!!!!!!!!未完成！！！！！！！！！！！！！！！
public class GetMaxCoins {
	//	一个N*M的矩阵上每个点都有一定数量的糖果。给你一个起点坐标X1、Y1和终点坐标X2、Y2，从起点到终点最多走K步，
	//	每一步可以上下左右移动，但不能沿着对角线移动，每到达矩阵的一点可以拿走该点的糖果。请输出可以拿到的糖果的最大值。
	//方法1：动态规划
	public static int getMaxCoins(int[][] graph, int x1, int y1, int x2, int y2, int k) {
		if(graph.length == 0)
			return 0;
		int m = graph.length;
		int n = graph[0].length;
		//dp[k][i][j]表示的是在第k次迭代的时候，（i，j）点所能收集到的最大糖果数目
		int[][][] dp = new int[k+1][m][n];
		for(int[][] A:dp) {
			for(int[] B:A) {
				Arrays.fill(B, 0);
			}
		}
		
		//无论哪一轮迭代，起始点位置所能收集到的最多糖果数目就等于该处的糖果值
		for (int i = 0; i < dp.length; i++) {
			dp[i][x1][y1] = graph[x1][y1];
		}
		
		for(int i=1;i<=k;i++) {
			//开始k轮迭代，每一轮表示扩充一步			
			for(int j=0;j<m;j++) {
				for(int r=0;r<n;r++) {
					if(dp[i-1][j][r] != 0) {
						if(j-1>=0 && dp[i-1][j][r] + graph[j-1][r] > dp[i-1][j-1][r]) {
							dp[i][j-1][r] = dp[i-1][j][r] + graph[j-1][r];
							graph[j-1][r] = 0;
						}
						if(j+1<m && dp[i-1][j][r] + graph[j+1][r] > dp[i-1][j+1][r]) {
							dp[i][j+1][r] = dp[i-1][j][r] + graph[j+1][r];
							graph[j+1][r] = 0;
						}
						if(r-1>=0 && dp[i-1][j][r] + graph[j][r-1] > dp[i-1][j][r-1]) {
							dp[i][j][r-1] = dp[i-1][j][r] + graph[j][r-1];
							graph[j][r-1] = 0;
						}
						if(r+1<n && dp[i-1][j][r] + graph[j][r+1] > dp[i-1][j][r+1]) {
							dp[i][j][r+1] = dp[i-1][j][r] + graph[j][r+1];
							graph[j][r+1] = 0;
						}
						
						dp[i][j][r] = 0;
					}
				}
			}
		}
		
		for(int[][] A:dp) {
			for(int[] B:A) {
				for(int r:B) {
					System.out.print(r + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		
		return dp[k][x2][y2]==Integer.MIN_VALUE?-1:dp[k][x2][y2];
	}
	
	public static void main(String[] args) {
		int[][] graph = {{1, 0, 2}, {0,1,3}, {9,2,0}, {0,0,1}};
		int res = getMaxCoins(graph, 0, 0, 3, 2, 5);
		System.out.println(res);
	}
	
}
