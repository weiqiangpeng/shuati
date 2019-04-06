package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class JumpStep {
//	小易来到了一条石板路前，每块石板上从1挨着编号为：1、2、3.......
//	这条石板路要根据特殊的规则才能前进：对于小易当前所在的编号为K的 石板，小易单次只能往前跳K的一个约数(不含1和K)步，
//	即跳到K+X(X为K的一个非1和本身的约数)的位置。 小易当前处在编号为N的石板，他想跳到编号恰好为M的石板去，小易想知道最少需要跳跃几次可以到达。
//	例如：
//	N = 4，M = 24：
//	4->6->8->12->18->24
//	于是小易最少需要跳跃5次，就可以从4号石板跳到24号石板
	
	public static ArrayList<Integer> getYueShu(int a){
		ArrayList<Integer> res = new ArrayList<>();
		for(int i=2;i<=a/2;i++) {
			if(a%i==0)
				res.add(i);
		}
		System.out.println(a+"的约数为：");
		for(int val:res)
			System.out.print(val+" ");
		System.out.println();
		return res;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		//dp[i]表示当前在i位置时，从n跳到m的最少跳跃次数，初始化为Integer.MAX_VALUE
		int[] dp = new int[m+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		//但n点到自己肯定是跳到0步的
		dp[n] = 0;
		//然后从n开始依次遍历到m，将该dp表填起来
		for(int k=n;k<=m;k++) {
			ArrayList<Integer> yueshu = getYueShu(k);
			for(int i=0;i<yueshu.size();i++) {
				if(k+yueshu.get(i)<=m && dp[k+yueshu.get(i)]>dp[k]+1)
					dp[k+yueshu.get(i)] = dp[k]+1;
			}
		}
		System.out.println(dp[m] == Integer.MAX_VALUE?-1:dp[m]);
	}
}
