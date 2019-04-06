package dp;

public class minStrDistance {
	//求两个字符串S和T的最小编辑距离
	//问题如下：给定两个字符串S和T,求把S变为T所需要的最少操作次数，具体的操作包括：
	//1、在任意一个位置添加一个字符。2、在任意一个位置减少一个字符。3、在任意一个位置修改一个字符。
	//转化为字符对齐问题
	//动态规划的方法，dp[i][j]表示S的前i个位置和T的前j个位置对齐的最少得分，
	//则，dp[i][j] = min(dp[i-1][j-1]+same(i,j), dp[i-1][j]+1, dp[i][j-1]+1)
	//其中，dp[i-1][j-1]+same(i,j)对应S第i个字符和T的第j个字符对齐。
	//dp[i-1][j]+1对应S的第i个字符和-对齐，即删掉S中第i个字符、
	//dp[i][j-1]+1对应T的第j个字符和-对齐，即在S中加入该字符。
	
	//换个思路，dp[i][j]是从什么情况下再经过一步操作到达该状态？有三种情况：dp[i-1][j]、dp[i][j-1]、dp[i-1][j-1]
	public static int minDistance(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		int[][] dp = new int[m+1][n+1];
		for(int i=0;i<=m;i++) {
			for(int j=0;j<=n;j++) {
				if(i == 0) {
					dp[i][j] = j;
				}
				else if(j == 0) {
					dp[i][j] = i;
				}
				else {
					dp[i][j] = Math.min(dp[i-1][j]+1, 
							Math.min(dp[i][j-1]+1, dp[i-1][j-1]+(s1.charAt(i-1)==s2.charAt(j-1)?0:1)));
				}
			}
		}
		
		//输出dp看看
		for(int[] A:dp) {
			for(int a:A) {
				System.out.print(a + " ");
			}
			System.out.println();
		}
		
		return dp[m][n];
	}
	
	public static void main(String[] args) {
		String s1 = "coee";
		String s2 = "coffee";
		System.out.println(minDistance(s1,s2));
	}
}
