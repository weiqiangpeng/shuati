package dp;

public class QieGeProblems {
	//切割问题，给一根钢条，长度为n，不同长度的钢条有不同的价值，且给定一个长度和价值的数组p，p[i]表示长度
	//为i的钢条具有多少价值。问如何切割使得价值最大？？？
//	方法一：直接递归,自顶向下
	public static int getMaxValue(int n, int[] p){
		if(n == 0)
			return 0;
		int q = Integer.MIN_VALUE;
		for(int i=1;i<=n;i++)
			q = Math.max(q, p[i]+getMaxValue(n-i, p));
		return q;
	}
	//方法二：自底向上，逐个求解,用r数组保存每个位置的最佳值
	public static int getMaxValueII(int n, int p[]){
		if(n == 0)
			return 0;
		int[] r = new int[n+1];
		r[0] = 0;
		for(int i=1;i<=n;i++){
			int q = Integer.MIN_VALUE;
			for(int j=1;j<=i;j++)
				q = Math.max(q, p[j]+r[i-j]);//这里不用递归调用，直接拿保存在r中的结果即可
			r[i] = q;
		}
		return r[n];
	}
	
	//带备忘录的递归算法,还是自顶向下,多了一个数组，当数组中值算好的话就可以直接返回
	public static int getMaxValueIII(int n, int p[]){
		if(n==0)
			return 0;
		int[] r = new int[n+1];
		for (int i = 0; i < r.length; i++) {
			r[i] = Integer.MIN_VALUE;
		}
		return helper(n,p,r);
	}
	public static int helper(int n,int[] p, int[] r){
		if(r[n] >= 0)
			return r[n];
		if(n == 0){
			return 0;
		}
		int q = Integer.MIN_VALUE;
		for(int i=1;i<=n;i++)
			q = Math.max(p[i]+helper(n-i,p,r), q);
		r[n] = q;
		return q;
	}
	
	public static void main(String[] args) {
		int[] p = {0,1,5,8,9,10,17,17,20,24,30};
		System.out.println(getMaxValue(10,p));
		System.out.println(getMaxValueII(10,p));
		System.out.println(getMaxValueIII(10,p));
	}
}
