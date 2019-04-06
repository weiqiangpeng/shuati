package dp;

public class WaKuang {
	//给定一个数组gold，表示每个矿区能挖多少矿出来。给定一个数组peopleNeed,表示每个矿区需要多少人挖，给定总人数peopleNumber
	//求如何分配人手，使挖的矿最多。
	static int[] peopleNeed = {1,2,3,4,5};
	static int[] gold = {5,5,8,5,5};
	static int peopleNumber = 5;
	//自顶向下的递归，不带备忘录的，很多值会重复计算多次	
	public static int WaKuangHelper(int mineNumber, int peopleNumber){
		if(mineNumber == 0){
			if(peopleNumber >= peopleNeed[mineNumber])
				return gold[mineNumber];
			else
				return 0;
		}
		else{
			if(peopleNeed[mineNumber] <= peopleNumber)
				return Math.max(WaKuangHelper(mineNumber-1, peopleNumber), WaKuangHelper(mineNumber-1,peopleNumber-peopleNeed[mineNumber])+gold[mineNumber]);
			else
				return WaKuangHelper(mineNumber-1, peopleNumber);
		}
	}
	
	//带备忘录的递归算法，先定义数组
	public static int WaKuangII(int mineNumber, int peopleNumber){
		if(peopleNumber <= 0)
			return 0;
		int[][] r = new int[gold.length][peopleNumber+1];
		//初始化备忘录
		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < r[i].length; j++) {
				r[i][j] = -1;
			}
		}
		return helper(mineNumber, peopleNumber, r);
	}
	
	public static int helper(int mineNumber, int peopleNumber, int[][] r){
		int val = Integer.MIN_VALUE;
		//如果需要计算的值在备忘录里，就直接返回
		if(r[mineNumber][peopleNumber] != -1)
			val = r[mineNumber][peopleNumber];
		if(mineNumber == 0){
			if(peopleNeed[mineNumber] <= peopleNumber)
				val = gold[mineNumber];
			else
				val = 0;
		}
		else{
			if(peopleNeed[mineNumber] <= peopleNumber){
				val = Math.max(gold[mineNumber]+helper(mineNumber-1,peopleNumber-peopleNeed[mineNumber], r),
						helper(mineNumber-1, peopleNumber, r));				
			}
			else{
				val = helper(mineNumber-1, peopleNumber, r);
			}
		}
		//做备忘录
		r[mineNumber][peopleNumber] = val;
		return val;
	}
	
	public static int wakuangIII(int[] value, int[] peopleNeed, int peopleNumer) {
		//dp[i][j]表示使用j个人时，前i个矿最多能挖多少
		int[][] dp = new int[value.length+1][peopleNumber+1];
		for(int i=0;i<=value.length;i++) {
			dp[i][0] = 0; //很显然，0个人的时候，什么也挖不到
		}
		
		for(int i=0;i<=peopleNumber;i++) {
			dp[0][i] = 0;          //很显然，没有矿可挖，在多人也不行
		}
		for(int i=1;i<=value.length;i++) {
			for(int j=1;j<=peopleNumber;j++) {
				if(j < peopleNeed[i-1])
					dp[i][j] = dp[i-1][j];      //很显然，j个人不够，那只能去前面找了
				else {
					dp[i][j] = Math.max(dp[i-1][j-peopleNeed[i-1]]+value[i-1], dp[i-1][j]);
				}
			}
		}
		return dp[value.length][peopleNumber];
	}
	
	public static void main(String[] args) {
		
		System.out.println(WaKuangHelper(gold.length-1, peopleNumber));
		System.out.println(WaKuangII(gold.length-1, peopleNumber));
		System.out.println(wakuangIII(gold, peopleNeed, peopleNumber));
	}
}
