package dp;
import java.util.Scanner;
public class WangyiHeChangTuan {
	//第一行，输入一个数字，表示会有n个数字
	//第二行输入n个数字，
	//第三行，输入两个数字，k和d,表示从n个数字中选择k个出来，且任意两个之间间隔不超过d，使得选出的k个数字乘积最大
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n+1];
		for(int i=1;i<=n;i++) {
			arr[i] = sc.nextInt();
		}
		int k = sc.nextInt();
		int d = sc.nextInt();
		//max_[i][j]:表示选取j个人，且最后以编号i结尾的，最大乘积
		//min_[i][j]:表示选取j个人，且最后一编号i结尾的，最小乘积
		int[][] max_ = new int[n+1][k+1];
		int[][] min_ = new int[n+1][k+1];
		//初始值，很好理解，只选择一个人的话，最后一个编号是谁，那就是谁了。
		for(int one=1;one<=n;one++) {
			max_[one][1] = arr[one];
			min_[one][1] = arr[one];
		}
		//接着就是自下而上将动态规划的表填出来
		for(int i=2;i<=k;i++) {
			//one记录着最后一个人的编号，在i值给定的条件下，one必须不能小于i,这种情况在前i-1个都选了的时候，最后一个才是i
			for(int one=i;one<=n;one++) {
				int tmpMax = Integer.MIN_VALUE;
				int tmpMin = Integer.MAX_VALUE;
				//倒数第一个数组为one，那么倒数第二个数字left首先不能超过one-1.接着要大于one-d,同时也要大于i-1
				for(int left=Math.max(one-d, i-1);left<=one-1;left++) {
					if(tmpMax < Math.max(max_[left][i-1]*arr[one], min_[left][i-1]*arr[one]))
						tmpMax =  Math.max(max_[left][i-1]*arr[one], min_[left][i-1]*arr[one]);
					if(tmpMin >  Math.min(min_[left][i-1]*arr[one], max_[left][i-1]*arr[one]))
						tmpMin = Math.min(min_[left][i-1]*arr[one], max_[left][i-1]*arr[one]);
				}
				max_[one][i] = tmpMax;
				min_[one][i] = tmpMin;
			}
		}
		
		int res = Integer.MIN_VALUE;
		for(int i=1;i<=n;i++) {
			if(res < max_[i][k])
				res = max_[i][k];
		}
		System.out.println(res);
	}
	
}
